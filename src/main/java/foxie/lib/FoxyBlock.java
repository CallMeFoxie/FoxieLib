package foxie.lib;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class FoxyBlock extends Block {

   private IFoxieMod modHandler;

   protected FoxyBlock(Material material) {
      super(material);
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
