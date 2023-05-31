package com.mastercard.gpse.processing.pages.navigation;

import java.lang.annotation.*;

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ ElementType.TYPE })
    @Documented
    public @interface Navigation {


        public String tabTitle();

        public String[] menuItemsTree() default {};

    }

