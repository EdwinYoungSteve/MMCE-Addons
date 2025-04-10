package github.alecsio.mmceaddons.common.crafting.component.base;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This annotation is used to yeet some boilerplate code from the component definitions. See {@link github.alecsio.mmceaddons.common.crafting.component.base.BaseComponent}
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresMod {
    String value();
}
