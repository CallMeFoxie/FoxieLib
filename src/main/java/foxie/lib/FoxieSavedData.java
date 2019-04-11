package foxie.lib;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.storage.WorldSavedData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FoxieSavedData extends WorldSavedData {

   private static HashMap<UUID, HashMap<String, NBTTagCompound>> savedData;

   private static FoxieSavedData instance;

   public FoxieSavedData(String foo) {
      this();
   }

   public FoxieSavedData() {
      super(FoxieLib.MODID);
      savedData = new HashMap<UUID, HashMap<String, NBTTagCompound>>();
      instance = this;
   }

   public static UUID getUUID(EntityPlayer player) {
      return player.getUniqueID();
   }

   public static FoxieSavedData instance() {
      return instance;
   }

   @Override
   public void readFromNBT(NBTTagCompound tag) {
      NBTTagList list = tag.getTagList("PlayerData", 10);
      for (int i = 0; i < list.tagCount(); i++) {
         NBTTagCompound playerData = list.getCompoundTagAt(i);

         UUID uuid = new UUID(playerData.getLong("UUIDMost"), playerData.getLong("UUIDLeast"));
         NBTTagList listData = playerData.getTagList("data", 10);
         HashMap<String, NBTTagCompound> modsData = new HashMap<String, NBTTagCompound>();
         for (int j = 0; j < listData.tagCount(); j++) {
            NBTTagCompound modData = listData.getCompoundTagAt(j);

            String modid = modData.getString("modid");
            NBTTagCompound actualData = (NBTTagCompound) modData.getTag("data");

            modsData.put(modid, actualData);
         }
         savedData.put(uuid, modsData);
      }
   }

   @Override
   public NBTTagCompound writeToNBT(NBTTagCompound tag) {
      NBTTagList list = new NBTTagList();

      for (Map.Entry<UUID, HashMap<String, NBTTagCompound>> entry : savedData.entrySet()) {
         NBTTagCompound compound = new NBTTagCompound();

         compound.setLong("UUIDMost", entry.getKey().getMostSignificantBits());
         compound.setLong("UUIDLeast", entry.getKey().getLeastSignificantBits());

         NBTTagList list1 = new NBTTagList();
         for (Map.Entry<String, NBTTagCompound> modData : entry.getValue().entrySet()) {
            NBTTagCompound newData = new NBTTagCompound();
            newData.setString("modid", modData.getKey());
            newData.setTag("data", modData.getValue());

            list1.appendTag(newData);
         }
         compound.setTag("data", list1);

         list.appendTag(compound);
      }

      tag.setTag("PlayerData", list);

      return tag;
   }

   // TODO cache to avoid the need for new() every so often
   public void getPlayerData(EntityPlayer player, IPlayerData prototype) {
      if (savedData.containsKey(player.getUniqueID())) {
         if (savedData.get(player.getUniqueID()).containsKey(prototype.getMODID()))
            prototype.readFromNBT(savedData.get(player.getUniqueID()).get(prototype.getMODID()));
      }
   }

   public void setPlayerData(EntityPlayer player, IPlayerData prototype) {
      if (!savedData.containsKey(player.getUniqueID())) {
         savedData.put(player.getUniqueID(), new HashMap<String, NBTTagCompound>());
      }

      NBTTagCompound data = new NBTTagCompound();
      prototype.writeToNBT(data);

      savedData.get(player.getUniqueID()).put(prototype.getMODID(), data);

      markDirty();
   }
}
