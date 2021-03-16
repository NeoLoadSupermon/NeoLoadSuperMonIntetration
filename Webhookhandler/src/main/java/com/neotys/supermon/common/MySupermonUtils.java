package com.neotys.supermon.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MySupermonUtils {
    public static long convertDate(String date) throws ParseException {

        if(date!=null) {
            DateFormat m_ISO8601Local = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
            //DateFormat m_ISO8601Local = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Date result = m_ISO8601Local.parse(date);
            return result.getTime();
        }
        else
            return 0;
    }
}
