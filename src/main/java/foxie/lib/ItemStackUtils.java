package foxie.lib;

import net.minecraft.item.ItemStack;

public class ItemStackUtils {
    public static boolean canMergeStacks(ItemStack a, ItemStack b) {
        if (a == null || b == null)
            return true;

        if (a.isItemEqual(b) && a.getMaxStackSize() >= a.getCount() + b.getCount())
            return true;

        return false;
    }

    public static ItemStack mergeStacks(ItemStack a, ItemStack b) {
        if (a == null && b != null)
            return b.copy();
        else if (b == null && a != null)
            return a.copy();
        else if (a == null && b == null)
            return null;

        a.setCount(a.getCount() + b.getCount());
        return a;
    }
}
