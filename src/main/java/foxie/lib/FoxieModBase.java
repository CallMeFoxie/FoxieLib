package foxie.lib;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public abstract class FoxieModBase implements IFoxieMod {
   public  Config config;
   private String MODID;

   public FoxieModBase() {
      MODID = Things.getCurrentModId();
   }

   @Override
   public Config getConfig() {
      return config;
   }

   @Override
   public String getModId() {
      return MODID;
   }

   public void preinit(FMLPreInitializationEvent event) {
      config = new Config(event.getSuggestedConfigurationFile().getAbsolutePath());
   }

   public void init(FMLInitializationEvent event) {

   }

   public void postinit(FMLPostInitializationEvent event) {

   }
}
