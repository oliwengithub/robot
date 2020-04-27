package com.oliwen.util;

import java.math.BigDecimal;

public class MathUtils {
	public static Double jia(Double value1,Double value2){
		BigDecimal b1 = new BigDecimal(value1.toString());
		BigDecimal b2 = new BigDecimal(value2.toString());
		return b1.add(b2).doubleValue();
	}
	public static Double suiji(Double min,Double max,int dex){
		Double d = jia(cheng(Math.random(),jian(max,min)),min);
		String s = fmtDouble(d,dex);
		return Double.parseDouble(s);
	}
	public static Double jia(Double value1,Double value2,Double value3){
		BigDecimal b1 = new BigDecimal(value1.toString());
		BigDecimal b2 = new BigDecimal(value2.toString());
		BigDecimal b3 = new BigDecimal(value3.toString());
		return  b1.add(b2).add(b3).doubleValue();
	}
	public static String fmtDouble(Double value,Integer dex){
		String str = new BigDecimal(value).setScale(dex+1,BigDecimal.ROUND_HALF_DOWN).toPlainString();
		return str.substring(0,str.length()-1);
	}
	public static String fmtDouble(String value,Integer dex){
		String str = new BigDecimal(value).setScale(dex+1,BigDecimal.ROUND_HALF_DOWN).toPlainString();
		return str.substring(0,str.length()-1);
	}
	public static String fmtDouble(Object value,Integer dex){
		String str = new BigDecimal(value.toString()).setScale(dex+1,BigDecimal.ROUND_HALF_DOWN).toPlainString();
		return str.substring(0,str.length()-1);
	}
	public static Double jian(Double value1,Double value2){
		BigDecimal b1 = new BigDecimal(value1.toString());
		BigDecimal b2 = new BigDecimal(value2.toString());
		return b1.subtract(b2).doubleValue();
	}
	public static Double jian(Double value1,Double value2,Double value3){
		BigDecimal b1 = new BigDecimal(value1.toString());
		BigDecimal b2 = new BigDecimal(value2.toString());
		BigDecimal b3 = new BigDecimal(value3.toString());
		return b1.subtract(b2).subtract(b3).doubleValue();
	}
	public static double cheng(Double value1,Double value2){
		BigDecimal b1 = new BigDecimal(value1.toString());
		BigDecimal b2 = new BigDecimal(value2.toString());
		return b1.multiply(b2).doubleValue();
	}
	public static double cheng(Double value1,Double value2,Double value3){
		BigDecimal b1 = new BigDecimal(value1.toString());
		BigDecimal b2 = new BigDecimal(value2.toString());
		BigDecimal b3 = new BigDecimal(value3.toString());
		return b1.multiply(b2).multiply(b3).doubleValue();
	}
	public static double chu(Double value1,Double value2,int dex) throws IllegalAccessException{
		BigDecimal b1 = new BigDecimal(String.valueOf(value1));
		BigDecimal b2 = new BigDecimal(String.valueOf(value2));
		return b1.divide(b2,dex,BigDecimal.ROUND_HALF_DOWN).doubleValue();
	}
	public static void main(String[] args){
		System.out.println(MathUtils.fmtDouble(1.4/58435,8));
	}
	public static Double getRandom(Double min, Double max,int dex){
		BigDecimal db = new BigDecimal(Math.random() * (max - min) + min);
		String s = MathUtils.fmtDouble(db.doubleValue(), dex);
		return Double.valueOf(s);
	}
}
