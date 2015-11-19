package foxie.lib;

import com.sun.istack.internal.NotNull;

import java.util.HashMap;
import java.util.Map;

public abstract class FoxyItems {
   private static Map<String, FoxyItem> itemRegistry = new HashMap<String, FoxyItem>();

   public static void add(@NotNull FoxyItem item) {
      itemRegistry.put(item.getUnlocalizedName().substring(5), item);
      Registrator.registerItem(item);
   }

   public static FoxyItem get(@NotNull String name) {
      return itemRegistry.get(name);
   }

   public static void preinit() {
   }

   public static void init() {
   }

   public static void postinit() {
   }
}
