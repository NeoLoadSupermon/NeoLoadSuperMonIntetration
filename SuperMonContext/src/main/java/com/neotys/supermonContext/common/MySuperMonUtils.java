package com.neotys.supermonContext.common;

import javax.swing.*;
import java.net.URL;

public  class MySuperMonUtils {
    private static final ImageIcon MYSUPERMON_ICON;

    static {

        final URL iconURL = MySuperMonUtils.class.getResource("supermon.png");
        if (iconURL != null) {
            MYSUPERMON_ICON = new ImageIcon(iconURL);
        } else {
            MYSUPERMON_ICON = null;
        }
    }

    public MySuperMonUtils() {
    }

    public static ImageIcon getMySupermonIcon() {
        return MYSUPERMON_ICON;
    }

}
