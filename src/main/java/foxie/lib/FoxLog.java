package foxie.lib;


import net.minecraftforge.fml.common.FMLLog;

public class FoxLog {
   public static void warning(String msg) {
      FMLLog.warning("[" + Things.getCurrentModId() + "] " + msg);
   }

   public static void error(String msg) {
      FMLLog.severe("[" + Things.getCurrentModId() + "] " + msg);
   }

   public static void info(String msg) {
      FMLLog.severe("[" + Things.getCurrentModId() + "] " + msg);
   }

   public static void notAFoxie() {
      error("    Fake Foxie detected! This is not a proper Foxie!");
      error("<-- just tried working with a mod that uses FoxieLib but does not implement IFoxieMod!");
      error("    report this to the author of given mod, please. Thanks!");
   }
}
