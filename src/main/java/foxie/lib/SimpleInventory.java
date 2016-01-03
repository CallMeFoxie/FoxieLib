package foxie.lib;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IChatComponent;

/**
 * Simple inventory implementation for use in Tile Entities, anywhere really
 */
public class SimpleInventory implements IInventory {
   protected final ItemStack[] inventory;
   protected final String      inventoryName;
   protected final int         inventoryLimit;
   protected       Container   owner;

   /**
    * Simple inventory to re-use in TEs/Items
    *
    * @param slots          number of slots
    * @param inventoryName  name of the inventory
    * @param inventoryLimit max stack size (normal item's stack size applies)
    * @param container      upstream container
    */
   public SimpleInventory(int slots, String inventoryName, int inventoryLimit, Container container) {
      this.inventoryName = inventoryName;
      this.inventoryLimit = inventoryLimit;
      inventory = new ItemStack[slots];
      this.owner = container;
   }

   /**
    * Simple inventory to re-use in TEs/Items
    *
    * @param slots          number of slots
    * @param inventoryName  name of the inventory
    * @param inventoryLimit max stack size (normal item's stack size applies)
    */
   public SimpleInventory(int slots, String inventoryName, int inventoryLimit) {
      this.inventoryName = inventoryName;
      this.inventoryLimit = inventoryLimit;
      inventory = new ItemStack[slots];
   }

   /**
    * Simple inventory to re-use in TEs/Items
    *
    * @param slots number of slots
    */
   public SimpleInventory(int slots) {
      this.inventoryName = "";
      this.inventoryLimit = 64;
      this.inventory = new ItemStack[slots];
   }

   public Container getContainer() {
      return this.owner;
   }

   public void setContainer(Container container) {
      this.owner = container;
   }

   @Override
   public int getSizeInventory() {
      return inventory.length;
   }

   @Override
   public ItemStack getStackInSlot(int slot) {
      if (slot >= inventory.length)
         return null;

      return inventory[slot];
   }

   @Override
   public ItemStack decrStackSize(int slot, int amount) {
      ItemStack stack = null;

      if (inventory[slot] != null) {
         if (inventory[slot].stackSize <= amount) {
            stack = inventory[slot];
            inventory[slot] = null;
         } else {
            stack = inventory[slot].splitStack(amount);
            if (inventory[slot].stackSize == 0) {
               inventory[slot] = null;
            }
         }
      }

      return stack;
   }

   @Override
   public ItemStack removeStackFromSlot(int index) {
      ItemStack stack = getStackInSlot(index);
      setInventorySlotContents(index, null);
      return stack;
   }

   @Override
   public void setInventorySlotContents(int slot, ItemStack item) {
      inventory[slot] = item;
   }

   @Override
   public int getInventoryStackLimit() {
      return inventoryLimit;
   }

   @Override
   public void markDirty() {

   }

   @Override
   public boolean isUseableByPlayer(EntityPlayer player) {
      return true;
   }

   @Override
   public void openInventory(EntityPlayer player) {

   }

   @Override
   public void closeInventory(EntityPlayer player) {

   }

   @Override
   public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
      return true;
   }

   @Override
   public int getField(int id) {
      return 0;
   }

   @Override
   public void setField(int id, int value) {

   }

   @Override
   public int getFieldCount() {
      return 0;
   }

   @Override
   public void clear() {

   }

   public void load(NBTTagCompound nbt) {
      NBTTagList list = nbt.getTagList("Inventory", 10);
      for (int i = 0; i < list.tagCount(); i++) {
         NBTTagCompound tag = list.getCompoundTagAt(i);
         byte slot = tag.getByte("Slot");
         if (slot >= 0 && slot < inventory.length) {
            if (tag.getBoolean("Empty"))
               inventory[slot] = null;
            else
               inventory[slot] = ItemStack.loadItemStackFromNBT(tag);
         }
      }
   }

   public void save(NBTTagCompound nbt) {
      NBTTagList itemList = new NBTTagList();
      for (int i = 0; i < inventory.length; i++) {
         ItemStack stack = inventory[i];
         if (stack != null) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setByte("Slot", (byte) i);
            stack.writeToNBT(tag);
            itemList.appendTag(tag);
         } else {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setByte("Slot", (byte) i);
            tag.setBoolean("Empty", true);
            itemList.appendTag(tag);
         }
      }
      nbt.setTag("Inventory", itemList);
   }

   @Override
   public String getName() {
      return null;
   }

   @Override
   public boolean hasCustomName() {
      return false;
   }

   @Override
   public IChatComponent getDisplayName() {
      return null;
   }
}
