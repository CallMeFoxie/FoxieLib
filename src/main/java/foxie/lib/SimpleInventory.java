package foxie.lib;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;

/**
 * Simple inventory implementation for use in Tile Entities, anywhere really
 */
public class SimpleInventory implements IInventory {
   private NonNullList<ItemStack> inventory;

   private int size;
   protected final String inventoryName;
   protected final int inventoryLimit;
   protected Container owner;

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
      this.size = slots;
      inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
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
      this.size = slots;
      inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
   }

   /**
    * Simple inventory to re-use in TEs/Items
    *
    * @param slots number of slots
    */
   public SimpleInventory(int slots) {
      this.inventoryName = "";
      this.inventoryLimit = 64;
      this.size = slots;
      inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
   }

   public Container getContainer() {
      return this.owner;
   }

   public void setContainer(Container container) {
      this.owner = container;
   }

   @Override
   public int getSizeInventory() {
      return this.size;
   }

   @Override
   public boolean isEmpty() {
      for (ItemStack stack : inventory) {
         if (stack != null)
            return false;
      }
      return true;
   }

   @Override
   public ItemStack getStackInSlot(int slot) {
      if (slot >= this.size)
         return null;

      return inventory.get(slot);
   }

   @Override
   public ItemStack decrStackSize(int slot, int amount) {
      ItemStack stack = null;

      if (!inventory.get(slot).isEmpty()) {
         if (inventory.get(slot).getCount() <= amount) {
            stack = inventory.get(slot);
            inventory.set(slot, ItemStack.EMPTY);
         } else {
            stack = inventory.get(slot).splitStack(amount);
            if (inventory.get(slot).getCount() == 0) {
               inventory.set(slot, ItemStack.EMPTY);
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
      if (item == null) // backwards compatible eh?
         item = ItemStack.EMPTY;

      inventory.set(slot, item);
   }

   @Override
   public int getInventoryStackLimit() {
      return inventoryLimit;
   }

   @Override
   public void markDirty() {

   }

   @Override
   public boolean isUsableByPlayer(EntityPlayer player) {
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
      inventory = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
      ItemStackHelper.loadAllItems(nbt.getCompoundTag("Inventory"), inventory);
   }

   public void save(NBTTagCompound nbt) {
      nbt.setTag("Inventory", ItemStackHelper.saveAllItems(new NBTTagCompound(), inventory));
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
   public ITextComponent getDisplayName() {
      return null;
   }
}
