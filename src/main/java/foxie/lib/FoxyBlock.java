package foxie.lib;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public abstract class FoxyBlock extends Block {

   protected FoxyBlock(Material material) {
      super(material);
   }

   public void preinit() {
   }

   public void init() {
   }

   public void postinit() {
   }

   @Override
   @SideOnly(Side.CLIENT)
   public void registerBlockIcons(IIconRegister registrar) {
      super.registerBlockIcons(registrar);
   }

   @SideOnly(Side.CLIENT)
   public IIcon registerIcon(IIconRegister register, String name) {
      return register.registerIcon(Things.getCurrentModId() + ":" + getUnlocalizedName().substring(5) + "_" + name);
   }
}
