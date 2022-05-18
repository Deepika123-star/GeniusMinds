package com.genius.minds;

public class common {
    public static String multiply(String first, String second){

        double result =strToDouble(first) * strToDouble(second);
        return doubletoStr(result);
    }
    public static Double strToDouble(String value){
            double result = Double.parseDouble(value);

        return result;
    }
    public static String doubletoStr(double value){

        String result =String.valueOf(value);
        return result;
    }

}
