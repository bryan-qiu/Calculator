/**
Program: Class for Base Conversion
Input: n/a
Processing: Converts from decimal to different bases and back.
Output: n/a

@param  c a parameter to the method.
@return d what the method is returning
@throws NullPointerException when this is thrown.
 */

//Import Statements Listed Alphabetically
import java.io.*;           //used for any type of input or output
import java.util.*;         //useful utilities like Scanner
//import hsa.Console;
//import hsa.*;
import java.text.*;

public class Conversion {

    /**
     * Takes in a char and returns an int type of it.
     * @param s char to be changed
     * @return a number that is the char's equivalent
     */
    public static int toInt(char s){
        if (s == '0') return 0;
        else if (s == '1') return 1;
        else if (s == '2') return 2;
        else if (s == '3') return 3;
        else if (s == '4') return 4;
        else if (s == '5') return 5;
        else if (s == '6') return 6;
        else if (s == '7') return 7;
        else if (s == '8') return 8;
        else if (s == '9') return 9;
        //for hexidecimal
        else if (s == 'A') return 10;
        else if (s == 'B') return 11;
        else if (s == 'C') return 12;
        else if (s == 'D') return 13;
        else if (s == 'E') return 14;
        else return 15;
    }

    /**
     * Takes in an int and returns a string type of it.
     * @param i
     * @return a string that is the int's equivalent
     */
    public static String toStringg(long i){
        if (i == 0) return "0";
        else if (i == 1) return "1";
        else if (i == 2) return "2";
        else if (i == 3) return "3";
        else if (i == 4) return "4";
        else if (i == 5) return "5";
        else if (i == 6) return "6";
        else if (i == 7) return "7";
        else if (i == 8) return "8";
        else if (i == 9) return "9";
        //for hexidecimal
        else if (i == 10) return "A";
        else if (i == 11) return "B";
        else if (i == 12) return "C";
        else if (i == 13) return "D";
        else if (i == 14) return "E";
        else return "F";
    }

    /**
     * Converts a number in base "base" to decimal (base 10)
     * @param base is the base the number is in
     * @param s is the number to be converted
     * @return a decimal conversion of the number
     */
    public static String toDec(int base, String s){
        long l = 0,m = 1;
        int j = 0,idx = 0;
        double ll = 0,m2;
        DecimalFormat form = new DecimalFormat ("#.##########");
        String ss = ""; //String to be returned
        String ng = "";
        if (s.equals("0"))
            return "0";
        if (s.charAt(0) == '-'){
            ng = "-";
            s = s.substring(1, s.length());
        }
        idx = s.length()-1;
        //Decimal places
        for (j = s.length()-1; j >= 0; j--){
            if (s.charAt(j) == '.'){
                idx = j-1;
                break;
            }
        }
        //Loops from the back of the number
        for (j = idx; j >= 0; j--){
            l += m*toInt(s.charAt(j)); //Adds to the answer
            m *= base; //Increase the multiplication factor by the base
        }
        if (l == 0)
            ss = "0";
        //l is the int type of the conversion
        //Changes to type string
        while (l > 0){
            ss = toStringg(l%10) + ss;
            l /= 10;
        }
        //If there are no decimals
        if (idx == s.length()-1)
            return ng+ss;
        else{
            ss += "."; //Adds decimal point
            s = s.substring(idx+2, s.length()); //String for decimals
            m2 = (double)1/(double)base; //Initial multiplier
            ll = 0;
            //For each decimal place
            for (j = 0; j < s.length(); j++){
                ll += toInt(s.charAt(j))*m2;
                m2 /= base;
            }
            s = form.format(ll);
            s = s.substring(2,s.length());
            ss += s;
            return ng+ss;
        }

    }

    /**
     * Converts a decimal (base 10) number to base "base"
     * @param base is the base the number is to be converted to
     * @param s is the number to be converted
     * @return a decimal conversion of the number
     */
    public static String toBase(int base, String s){
        long l = 0,m = 1;
        double ll = 0,m2 = 1;
        int idx = 0,j,ii = 0,count = 0,count2 = 0;
        DecimalFormat form = new DecimalFormat ("#.##########");
        String ss = "";
        String ng = "";
        if (s.charAt(0) == '-'){
            ng = "-";
            s = s.substring(1, s.length());
        }
        idx = s.length()-1;
        //Decimal places
        for (j = s.length()-1; j >= 0; j--){
            if (s.charAt(j) == '.'){
                idx = j-1;
                break;
            }
        }
        //Whole numbers
        for (j = idx; j >= 0; j--){
            l += m*toInt(s.charAt(j));
            m *= 10;
        }
        if (l == 0)
            ss = "0";
        while (l > 0){
            ss = toStringg(l%base) + ss;
            l /= base;
        }
        //If there are no decimals
        if (idx == s.length()-1)
            return ng+ss;

        else{
            ss += "."; //Adds decimal point
            s = s.substring(idx+2, s.length()); //String for decimals
            ll = 0;
            m2 = (double)1/(double)10;
            //Convert string to int
            for (j = 0; j < s.length(); j++){
                ll += m2*toInt(s.charAt(j));
                m2 /= 10;
            }
            m2 = (double)1/(double)base;
            count = 0;
            count2 = 0;
            s = "";
            //Converts to base
            while ((ll >= 0) && (count < 10)){
                ll -= m2;
                if (ll < 0){
                    ll += m2;
                    m2 /= base;
                    count++;
                    s += toStringg(count2);
                    count2 = 0;
                }
                else
                    count2++;
            }
            //Removes 0's at the end
            for (j = s.length()-1; j >= 0; j--){
                if (s.charAt(j) != '0'){
                    idx = j;
                    break;
                }
            }
            s = s.substring(0, idx+1);
            return ng+ss+s;
        }
    }

    /**
     * Logical operator - and
     * @param s is the first number
     * @param s2 is the second number
     * @return the and of the two numbers
     */
    public static String and(String s, String s2){
        String ss = "";
        int j,len1,len2;
        len1 = s.length();
        len2 = s2.length();
        //Adds 0s to the front of each binary number
        if (len1 > len2){
            for (j = 0; j < len1-len2; j++)
                s2 = "0" + s2;
        }
        else{
            for (j = 0; j < len2-len1; j++)
                s = "0" + s;
        }
        //Performs and operator
        for (j = 0; j < s.length(); j++){
            if ((s.charAt(j) == '0') || (s2.charAt(j) == '0'))
                ss += "0";
            else
                ss += "1";
        }
        return toDec(2,ss);
    }

    /**
     * Logical operator - or
     * @param s is the first number
     * @param s2 is the second number
     * @return the or of the two numbers
     */
    public static String or(String s, String s2){
        String ss = "";
        int j,len1,len2;
        len1 = s.length();
        len2 = s2.length();
        //Adds 0s to the front of each binary number
        if (len1 > len2){
            for (j = 0; j < len1-len2; j++)
                s2 = "0" + s2;
        }
        else{
            for (j = 0; j < len2-len1; j++)
                s = "0" + s;
        }
        //Performs or operator
        for (j = 0; j < s.length(); j++){
            if ((s.charAt(j) == '1') || (s2.charAt(j) == '1'))
                ss += "1";
            else
                ss += "0";
        }
        return toDec(2,ss);
    }

    /**
     * Logical operator - xor
     * @param s is the first number
     * @param s2 is the second number
     * @return the xor of the two numbers
     */
    public static String xor(String s, String s2){
        String ss = "";
        int j,len1,len2;
        len1 = s.length();
        len2 = s2.length();
        //Adds 0s to the front of each binary number
        if (len1 > len2){
            for (j = 0; j < len1-len2; j++)
                s2 = "0" + s2;
        }
        else{
            for (j = 0; j < len2-len1; j++)
                s = "0" + s;
        }
        //Performs xor operator
        for (j = 0; j < s.length(); j++){
            if ((s.charAt(j) != s2.charAt(j)))
                ss += "1";
            else
                ss += "0";
        }
        return toDec(2,ss);
    }

    /*** MAIN METHOD **
     * @param args
     */

    public static void main(String[] args) {

        System.out.println("Starting...");
	   //Console c = new Console();
        System.out.println (Conversion.toBase(16, Conversion.toDec(10,"3021")));
    }

}