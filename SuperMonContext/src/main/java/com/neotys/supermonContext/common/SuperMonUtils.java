package com.neotys.supermonContext.common;

import javax.swing.*;
import java.net.URL;

public  class SuperMonUtils {
    private static final ImageIcon SUPERMON_ICON;

    static {

        final URL iconURL = SuperMonUtils.class.getResource("supermon.png");
        if (iconURL != null) {
            SUPERMON_ICON = new ImageIcon(iconURL);
        } else {
            SUPERMON_ICON = null;
        }
    }

    public SuperMonUtils() {
    }

    public static ImageIcon getSupermonIcon() {
        return SUPERMON_ICON;
    }

}
