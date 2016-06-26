package foxie.lib.client.slot;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class SlotFilterInstanceof extends SlotCapability {

   Class[] classType;

   public SlotFilterInstanceof(TileEntity entity, int par2, int par3, int par4, Class... type) {
      super(entity, par2, par3, par4);
      this.classType = type;
   }

   @Override
   public boolean isItemValid(ItemStack item) {
      for (Class c : classType)
         if (c.isInstance(item.getItem()))
            return true;

      return false;
   }
}
