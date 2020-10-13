package com.sky.hiwise.youli.util;

public class FieldFormat {

    public static Object getValue(Object value,Class type){
        if (value != null){
            if (type.isAssignableFrom(String[].class))
                return toStringArray(value);
            if (type.isAssignableFrom(Integer[].class))
                return toIntegerArray(value);
            else if (type.isAssignableFrom(Integer.class) || type.isAssignableFrom(int.class) )
                return toInteger(value);
            if (type.isAssignableFrom(Double.class) || type.isAssignableFrom(double.class))
                return toDouble(value);
            else if (type.isAssignableFrom(Boolean.class) || type.isAssignableFrom(boolean.class))
                return toBoolean(value);
            else if (type.isAssignableFrom(String.class))
                return toString(value);
        }
        return null;
    }

    private static String[] toStringArray(Object value){
        return value.toString().split(",");
    }

    private static Integer[] toIntegerArray(Object value){
        String[] stringArray = toStringArray(value);
        Integer[] intArray = new Integer[stringArray.length];
        for (int i = 0; i < stringArray.length; i++){
            intArray[i] = Integer.parseInt(stringArray[i]);
        }
        return intArray;
    }

    private static Integer toInteger(Object value){
        return Integer.parseInt(value.toString());
    }

    private static Double toDouble(Object value){
        return Double.parseDouble(value.toString());
    }

    private static String toString(Object value){
        return value.toString();
    }

    private static Boolean toBoolean(Object value){
        return Boolean.parseBoolean(value.toString());
    }
}
