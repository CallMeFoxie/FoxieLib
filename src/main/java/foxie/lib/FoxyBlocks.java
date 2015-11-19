package foxie.lib;

import com.sun.istack.internal.NotNull;

import java.util.HashMap;
import java.util.Map;

public class FoxyBlocks {
   private static Map<String, FoxyBlock> blockRegistry = new HashMap<String, FoxyBlock>();

   public static void add(@NotNull FoxyBlock block) {
      blockRegistry.put(block.getUnlocalizedName().substring(5), block);
      Registrator.registerBlock(block);
   }

   public static FoxyBlock get(@NotNull String name) {
      return blockRegistry.get(name);
   }

   public static void preinit() {
   }

   public static void init() {
   }

   public static void postinit() {
   }
}
