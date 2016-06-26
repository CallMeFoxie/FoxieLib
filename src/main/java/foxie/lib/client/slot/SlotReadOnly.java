package foxie.lib.client.slot;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class SlotReadOnly extends SlotCapability {

   public SlotReadOnly(TileEntity entity, int par2, int par3, int par4) {
      super(entity, par2, par3, par4);
   }

   @Override
   public boolean isItemValid(ItemStack item) {
      return false;
   }
}
