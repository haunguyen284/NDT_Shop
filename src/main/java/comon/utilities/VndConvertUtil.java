/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comon.utilities;

/**
 *
 * @author Admin
 */
public class VndConvertUtil {

    public static Float vndToFloat(String str) {
        Float f = Float.parseFloat(str.replaceAll(",", "").replaceAll("[^\\d.]", ""));
        return f;
    }
    
    public static String floatToVnd(Float f){
        return String.format("%,.0f VND", f);
    }
}
