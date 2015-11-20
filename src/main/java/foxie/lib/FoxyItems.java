package foxie.lib;

import java.util.HashMap;
import java.util.Map;

public abstract class FoxyItems {
   private static Map<String, FoxyItem> itemRegistry = new HashMap<String, FoxyItem>();

   public static void add(FoxyItem item) {
      itemRegistry.put(item.getUnlocalizedName().substring(5), item);
      Registrator.registerItem(item);
   }

   public static FoxyItem get(String name) {
      return itemRegistry.get(name);
   }

   public static void preinit() {
   }

   public static void init() {
   }

   public static void postinit() {
   }
}
