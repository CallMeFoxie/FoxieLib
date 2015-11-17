package foxie.lib;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;

public class Things {
   public static String getCurrentModId() {
      return getCurrentModContainer().getModId();
   }

   public static ModContainer getCurrentModContainer() {
      return Loader.instance().activeModContainer();
   }

   public static IFoxieMod getCurrentMod() {
      ModContainer container = getCurrentModContainer();
      if (container.getMod() instanceof IFoxieMod)
         return (IFoxieMod) container.getMod();

      return null;
   }
}
