package foxie.lib;

import net.minecraft.nbt.NBTTagCompound;

public abstract class PlayerData {

   public abstract void readFromNBT(NBTTagCompound compound);

   public abstract void writeToNBT(NBTTagCompound compound);

   public abstract String getMODID();
}
