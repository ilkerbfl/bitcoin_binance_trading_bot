package com.bitcoin.Utility;

import java.math.BigDecimal;

/**
 * Created by İlker ÇATAK on 1/17/18.
 */
public class Utility {

    public static BigDecimal convertStringToBigDecimalRoundAsStringLength(String price){
        String[] split = price.split("\\.");
        String splittedPartAfterDot=split[1];
        return new BigDecimal(Double.valueOf(price)).setScale((split[1].length()-2),BigDecimal.ROUND_HALF_EVEN);
    }
    public static String scaleString(String price,int scale){
        String[] split = price.split("\\.");
        String splittedPartAfterDot=split[1];
        String substring = split[1].substring(0, scale);
        String result="0."+substring;
        return result;
    }
}
