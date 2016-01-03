package foxie.lib;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;
import java.lang.reflect.Field;

public class Config {
   Configuration cfg;

   public Config(String file) {
      this(new File(file));
   }

   public Config(File file) {
      cfg = new Configuration(file);
   }

   public static File getConfigFile(String path, String name) {
      if (!(new File(path).exists()))
         (new File(path)).mkdir();

      return new File(path + File.separator + Things.getCurrentModId() + File.separator + name + ".cfg");
   }

   public void process(Class o, Field field, Configurable cfgOption) {
      field.setAccessible(true);

      try {
         String category = cfgOption.category(), key = cfgOption.key(), def = cfgOption.def();
         if (key.equals(""))
            key = field.getName();
         if (def.equals("")) {
            def = field.get(o).toString();
         }

         if (category.equals("")) {
            category = o.getName().substring(o.getName().lastIndexOf(".") + 1);
         }

         Property property = cfg.get(category, key, def);
         property.comment = cfgOption.comment();

         // lots of copypaste, sadly Java's annotations are annoying...
         if (field.getType() == int.class) {
            if (!cfgOption.min().equals(""))
               property.setMinValue(Integer.parseInt(cfgOption.min()));
            if (!cfgOption.max().equals(""))
               property.setMaxValue(Integer.parseInt(cfgOption.max()));

            field.setInt(o, property.getInt());

         } else if (field.getType() == float.class) {
            if (!cfgOption.min().equals(""))
               property.setMinValue(Float.parseFloat(cfgOption.min()));
            if (!cfgOption.max().equals(""))
               property.setMaxValue(Float.parseFloat(cfgOption.max()));

            field.setFloat(o, (float) property.getDouble());

         } else if (field.getType() == double.class) {
            if (!cfgOption.min().equals(""))
               property.setMinValue(Double.parseDouble(cfgOption.min()));
            if (!cfgOption.max().equals(""))
               property.setMaxValue(Double.parseDouble(cfgOption.max()));

            field.setDouble(o, property.getDouble());

         } else if (field.getType() == boolean.class) {
            field.setBoolean(o, property.getBoolean());
         }
         // TODO getType == int array
         // TODO getType == string array
      } catch (IllegalAccessException e) { // please...
      }

      if (cfg.hasChanged())
         cfg.save();
   }

   public void preinit(FMLPreInitializationEvent event) {
   }

   public void init(FMLInitializationEvent event) {
   }

   public void postinit(FMLPostInitializationEvent event) {
   }

}
