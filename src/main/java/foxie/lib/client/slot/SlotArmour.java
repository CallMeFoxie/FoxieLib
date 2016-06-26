package foxie.lib.client.slot;


import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class SlotArmour extends Slot {

   public SlotArmour(IInventory inventoryIn, int index, int xPosition, int yPosition) {
      super(inventoryIn, index, xPosition, yPosition);
   }

   @Override
   public boolean isItemValid(ItemStack stack) {
      return stack == null || stack.getItem() instanceof ItemArmor;
   }
}
