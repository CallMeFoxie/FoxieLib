package foxie.lib;


import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Registrator {
   public static void registerOreDict(Item item, String oreName) {
      OreDictionary.registerOre(oreName, new ItemStack(item));
   }

   public static void registerOreGen(IWorldGenerator generator, int modGenerationWeight) {
      GameRegistry.registerWorldGenerator(generator, modGenerationWeight);
      checkConfigurable(generator.getClass());
   }

   public static void checkConfigurable(Class c) {
      IFoxieMod mod = Things.getCurrentMod();
      if (mod == null) {
         FoxLog.notAFoxie();
         return;
      }

      for (Field field : c.getDeclaredFields()) {
         if (field.isAnnotationPresent(Configurable.class)) {
            Configurable configurable = field.getAnnotation(Configurable.class);
            mod.getConfig().process(c, field, configurable);
         }
      }
   }


   public static void checkForEvents(Object o) {
      Class clazz = o.getClass();
      boolean needsSubscribing = false;
      for (Class myClass = clazz; myClass != null && myClass.getCanonicalName().startsWith("foxie."); myClass = myClass.getSuperclass()) {
         // added check for class name to speed it up
         Method[] methods = myClass.getDeclaredMethods();
         for (Method method : methods) {
            if (method.isAnnotationPresent(SubscribeEvent.class)) {
               needsSubscribing = true;
               break;
            }
         }
      }

      if (needsSubscribing) {
         MinecraftForge.EVENT_BUS.register(o);
      }

      if (o instanceof Class) {
         checkConfigurable((Class) o);
      }
   }
}

