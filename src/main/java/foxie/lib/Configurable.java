package foxie.lib;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Configurable {
   String category() default "";

   String key() default "";

   String comment() default "";

   String min() default "";

   String max() default "";

   String def() default "";

}
