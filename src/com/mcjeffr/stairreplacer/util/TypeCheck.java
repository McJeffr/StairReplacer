package com.mcjeffr.stairreplacer.util;

/**
 *
 * @author McJeffr
 */
public class TypeCheck {
    
    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isMetaData(String s) {
        try {
            byte parsedString = Byte.parseByte(s);
            return !(parsedString < 0 || parsedString > 16);
        } catch (Exception e) {
            return false;
        }
    }
    
}
