package foxie.lib;


import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

/**
 * Support class to make 1.8 update easier :P
 */
public class BlockPos extends net.minecraft.util.BlockPos {
   private int x, y, z;
   private EnumFacing direction;

   public BlockPos(int x, int y, int z) {
      super(x, y, z);
      this.x = x;
      this.y = y;
      this.z = z;
      this.direction = null;
   }

   public BlockPos(int x, int y, int z, EnumFacing direction) {
      this(x, y, z);
      this.direction = direction;
   }

   public static BlockPos readFromNBT(NBTTagCompound compound) {
      if (compound == null)
         return null;

      return new BlockPos(compound.getInteger("x"), compound.getInteger("y"), compound.getInteger("z"));
   }

   public int getX() {
      return x;
   }

   public void setX(int x) {
      this.x = x;
   }

   public int getY() {
      return y;
   }

   public void setY(int y) {
      this.y = y;
   }

   public int getZ() {
      return z;
   }

   public void setZ(int z) {
      this.z = z;
   }

   public EnumFacing getDirection() {
      return direction;
   }

   public void setDirection(EnumFacing direction) {
      this.direction = direction;
   }

   public BlockPos getLeft(int blocks) {
      return getRight(-blocks);
   }

   public BlockPos getRight(int blocks) {
      if (direction == EnumFacing.EAST)
         z += blocks;

      if (direction == EnumFacing.WEST)
         z -= blocks;

      if (direction == EnumFacing.NORTH)
         x += blocks;

      if (direction == EnumFacing.SOUTH)
         x -= blocks;

      return this;
   }

   public BlockPos getFarther(int blocks) {
      return getCloser(-blocks);
   }

   public BlockPos getCloser(int blocks) {
      if (direction == EnumFacing.EAST)
         x -= blocks;

      if (direction == EnumFacing.WEST)
         x += blocks;

      if (direction == EnumFacing.NORTH)
         z += blocks;

      if (direction == EnumFacing.SOUTH)
         z -= blocks;

      return this;
   }

   public BlockPos getBelow(int blocks) {
      y--;
      return this;
   }

   public BlockPos getAbove(int blocks) {
      y++;
      return this;
   }

   public BlockPos getLeft() {
      return getLeft(1);
   }

   public BlockPos getRight() {
      return getRight(1);
   }

   public BlockPos getFarther() {
      return getFarther(1);
   }

   public BlockPos getCloser() {
      return getCloser(1);
   }

   public BlockPos getBelow() {
      return getBelow(1);
   }

   public BlockPos getAbove() {
      return getAbove(1);
   }

   public Block getBlock(World world) {
      return world.getBlockState(this).getBlock();
   }

   public TileEntity getTE(World world) {
      return world.getTileEntity(this);
   }

   public IBlockState getBlockState(World world) {
      return world.getBlockState(this);
   }

   public void writeToNBT(NBTTagCompound compound) {
      compound.setInteger("x", getX());
      compound.setInteger("y", getY());
      compound.setInteger("z", getZ());
   }

}
