package foxie.lib;

import net.minecraft.item.Item;

public class FoxyItem extends Item {
   private IFoxieMod modHandler;

   public FoxyItem() {
      modHandler = Things.getCurrentMod();
   }

   public IFoxieMod getModHandler() {
      return modHandler;
   }

   public void preinit() {
   }

   public void init() {
   }

   public void postinit() {
   }
}
