package foxie.lib;

import net.minecraft.nbt.NBTTagCompound;

public interface IPlayerData {

   void readFromNBT(NBTTagCompound compound);

   void writeToNBT(NBTTagCompound compound);

   String getMODID();
}
