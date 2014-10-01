import java.io.*;
import java.util.*;

public class Fraction {

    /**
     * Numerator
     */
    static  public int num=1;
   /**
    * Denominator
    */
   static  public int denom=1;

    /**
     * Convert fraction into a double
     * @param s string of fraction
     * @return double value of the fraction
     */
    public static double fracToDouble(String s){

        //split fraction into the 3 different parts

        StringTokenizer f = new StringTokenizer(s,"~");
            if(s.indexOf('~')>0){
                //return the value of the fraction

                //if whole number,numerator and denomniator return value
                if(s.lastIndexOf('~')!=s.indexOf('~')){
                    return Double.valueOf(f.nextToken())+(Double.valueOf(f.nextToken())/Double.valueOf(f.nextToken()));
                }
                 //if only numerator and denomniator return value
                else return (Double.valueOf(f.nextToken())/Double.valueOf(f.nextToken()));
            }

        return Double.valueOf(s);

    }

    /**
     * initialize fraction with double value
     * @param n decimal
     */
    public Fraction(double n) {

        if(n==0){
            num=0;
            denom=0;
            return;
        }

        int i;
        //original number
        double orig=n;
        //store denominators
        int denominators[] = new int[20];
        //min difference is n
        double minDiff = Math.abs(n);
        int minNum = 1;
        int minDenom=1;

        //computer all possible denominators
        for (i = 0; i < 20; i++) {
            denominators[i] = (int) n;
            n = 1.0 / (n - denominators[i]);
        }

        for (i = 0; i < 19; i++) {

            //init numerator and denomniator

            int numerator = 1;
            int denominator = 1;
            int temp = 0;

            // Do the computation
            int current = i;
            while (current >= 0) {
                denominator = numerator;
                numerator = (numerator * denominators[current]) + temp;
                temp = denominator;
                current--;
            }


            //computer value of fraction
             double value = (double)numerator/denominator;
            
             //if difference is smaller than previous fraction store it
            if(Math.abs(value-orig)<minDiff){
                minDiff=Math.abs(value-orig);
                minNum= numerator;
                minDenom=denominator;
            }

        }
        num = minNum;
        denom=minDenom;
    }


    /**
     * get numerator
     * @return numerator of fraction
     */
    public int getNumerator(){
        //return numberator
        return num;

    }

    /**
     * get denominator
     * @return denomniator of fraction
     */
    public int getDenominator(){
        //return denominator
        return num;

    }
    
}
