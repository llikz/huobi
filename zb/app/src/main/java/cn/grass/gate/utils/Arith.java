package cn.grass.gate.utils;

import java.math.BigDecimal;

/**
 * Created by weedys on 16/8/26.
 */
public class Arith {
    public static float mul(float time1,int time2){

        BigDecimal b=new BigDecimal(time1).multiply(new BigDecimal(time2));
       float bb= b.setScale(10,BigDecimal.ROUND_HALF_UP).floatValue();
        return bb;
    }
}
