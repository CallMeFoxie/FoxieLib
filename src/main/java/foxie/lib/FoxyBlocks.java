package foxie.lib;

import java.util.HashMap;
import java.util.Map;

public class FoxyBlocks {
   private static Map<String, FoxyBlock> blockRegistry = new HashMap<String, FoxyBlock>();

   public static void add(FoxyBlock block) {
      blockRegistry.put(block.getUnlocalizedName().substring(5), block);
      Registrator.registerBlock(block);
   }

   public static FoxyBlock get(String name) {
      return blockRegistry.get(name);
   }

   public static void preinit() {
   }

   public static void init() {
   }

   public static void postinit() {
   }
}
