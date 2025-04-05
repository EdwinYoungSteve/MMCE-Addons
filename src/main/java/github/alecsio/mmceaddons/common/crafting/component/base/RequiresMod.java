package github.alecsio.mmceaddons.common.crafting.component.base;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresMod {
    String value();
}
