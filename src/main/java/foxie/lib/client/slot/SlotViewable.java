package foxie.lib.client.slot;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;

public class SlotViewable extends SlotCapability {
   public SlotViewable(TileEntity entity, int index, int xPosition, int yPosition) {
      super(entity, index, xPosition, yPosition);
   }

   @Override
   public void putStack(@Nullable ItemStack stack) {
   }

   @Override
   public ItemStack decrStackSize(int amount) {
      return null;
   }

   @Override
   public boolean isItemValid(@Nullable ItemStack stack) {
      return false;
   }
}
