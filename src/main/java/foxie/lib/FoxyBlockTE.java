package foxie.lib;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class FoxyBlockTE extends FoxyBlock implements ITileEntityProvider {
   protected FoxyBlockTE(Material material) {
      super(material);
   }

   @Override
   public abstract TileEntity createNewTileEntity(World world, int meta);
}
