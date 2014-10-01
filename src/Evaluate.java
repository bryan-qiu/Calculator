/**
Input: Arithmetic expression with spaces in between
Processing: Evaluates the expression
Output: Value of the expression

@param  c a parameter to the method.
@return d what the method is returning
@throws NullPointerException when this is thrown.
 */
//Import Statements Listed Alphabetically
import java.io.*;           //used for any type of input or output
import java.util.*;         //useful utilities like Scanner
//import hsa.Console;
//import hsa.*;
import java.text.*; //used for decimal format
import java.math.*; //for math funtions

public class Evaluate {

    static int base = 10;

    //copied from http://www.cs.princeton.edu/introcs/91float/Gamma.java.html
    /**
     * Gamma function used for calculating factorials with decimals
     * @param x is the operand of the factorial
     * @return the factorial of x
     */
    public static double logGamma(double x) {
        double tmp = (x - 0.5) * Math.log(x + 4.5) - (x + 4.5);
        double ser = 1.0 + 76.18009173    / (x + 0)   - 86.50532033    / (x + 1)
                           + 24.01409822    / (x + 2)   -  1.231739516   / (x + 3)
                           +  0.00120858003 / (x + 4)   -  0.00000536382 / (x + 5);
          return tmp + Math.log(ser * Math.sqrt(2 * Math.PI));
    }
    static double gamma (double x) { return Math.exp(logGamma(x)); }

    /**
     * Factorial of a number
     * @param x is the operand of the factorial
     * @return the factorial of x
     */
    public static double fact(double x){
        if (x == 0)
            return 1;
        else if(x == 1)
            return 1;
        else
            return fact(x-1)*x;
    }

    /**
     * Checks the expression for unary operators and evaluates them
     * @param ex is the original expression
     * @return an expression without unary operators since they've been calculated
     */
    public static String checkUnary(String ex){
        int j,k,c = 0;
        String ss = "";
        double tmp = 0;
        DecimalFormat form = new DecimalFormat ("#.##########");
        for (j = 0; j < ex.length()-4; j++){
            //sin
            if (ex.substring(j, j+3).equals("sin")){ //Checks to see if sin is an operator
                c = 1;
                //Loops to find the operand of sin
                for (k = j+6; k < ex.length(); k++){
                    if (ex.charAt(k) == '(')
                        c++;
                    else if (ex.charAt(k) == ')')
                        c--;
                    if (c == 0)
                        break;
                }
                //Recursive call to evaluate
                ex = ex.substring(0,j) + form.format(Math.sin(Math.toRadians(Double.parseDouble(eval(ex.substring(j+6,k)))))) + ex.substring(k+1,ex.length());
                j = -1;
                continue;
            }
            //cos
            if (ex.substring(j, j+3).equals("cos")){
                c = 1;
                for (k = j+6; k < ex.length(); k++){
                    if (ex.charAt(k) == '(')
                        c++;
                    else if (ex.charAt(k) == ')')
                        c--;
                    if (c == 0)
                        break;
                }
                ex = ex.substring(0,j) + form.format(Math.cos(Math.toRadians(Double.parseDouble(eval(ex.substring(j+6,k)))))) + ex.substring(k+1,ex.length());
                j = -1;
                continue;
            }
            //tan
            if (ex.substring(j, j+3).equals("tan")){
                c = 1;
                for (k = j+6; k < ex.length(); k++){
                    if (ex.charAt(k) == '(')
                        c++;
                    else if (ex.charAt(k) == ')')
                        c--;
                    if (c == 0)
                        break;
                }
                ex = ex.substring(0,j) + form.format(Math.tan(Math.toRadians(Double.parseDouble(eval(ex.substring(j+6,k)))))) + ex.substring(k+1,ex.length());
                j = -1;
                continue;
            }

            //asin
            if (ex.substring(j, j+4).equals("asin")){
                c = 1;
                for (k = j+6; k < ex.length(); k++){
                    if (ex.charAt(k) == '(')
                        c++;
                    else if (ex.charAt(k) == ')')
                        c--;
                    if (c == 0)
                        break;
                }
                ex = ex.substring(0,j) + form.format(Math.toDegrees(Math.asin(Double.parseDouble(eval(ex.substring(j+7,k)))))) + ex.substring(k+1,ex.length());
                j = -1;
                continue;
            }

            //acos
            if (ex.substring(j, j+4).equals("acos")){
                c = 1;
                for (k = j+6; k < ex.length(); k++){
                    if (ex.charAt(k) == '(')
                        c++;
                    else if (ex.charAt(k) == ')')
                        c--;
                    if (c == 0)
                        break;
                }
                ex = ex.substring(0,j) + form.format(Math.toDegrees(Math.acos(Double.parseDouble(eval(ex.substring(j+7,k)))))) + ex.substring(k+1,ex.length());
                j = -1;
                continue;
            }
            //atan
            if (ex.substring(j, j+4).equals("atan")){
                c = 1;
                for (k = j+6; k < ex.length(); k++){
                    if (ex.charAt(k) == '(')
                        c++;
                    else if (ex.charAt(k) == ')')
                        c--;
                    if (c == 0)
                        break;
                }
                ex = ex.substring(0,j) + form.format(Math.toDegrees(Math.atan(Double.parseDouble(eval(ex.substring(j+7,k)))))) + ex.substring(k+1,ex.length());
                j = -1;
                continue;
            }
            //neg
            if (ex.substring(j, j+3).equals("neg")){
                c = 1;
                for (k = j+6; k < ex.length(); k++){
                    if (ex.charAt(k) == '(')
                        c++;
                    else if (ex.charAt(k) == ')')
                        c--;
                    if (c == 0)
                        break;
                }
                ex = ex.substring(0,j) + form.format(0-Double.parseDouble(eval(ex.substring(j+6,k)))) + ex.substring(k+1,ex.length());
                j = -1;
                continue;
            }

            //log
            if (ex.substring(j, j+3).equals("log")){
                c = 1;
                for (k = j+6; k < ex.length(); k++){
                    if (ex.charAt(k) == '(')
                        c++;
                    else if (ex.charAt(k) == ')')
                        c--;
                    if (c == 0)
                        break;
                }
                ex = ex.substring(0,j) + form.format(Math.log10(Double.parseDouble(eval(ex.substring(j+6,k))))) + ex.substring(k+1,ex.length());
                j = -1;
                continue;
            }

            //ln
            if (ex.substring(j, j+2).equals("ln")){
                c = 1;
                for (k = j+6; k < ex.length(); k++){
                    if (ex.charAt(k) == '(')
                        c++;
                    else if (ex.charAt(k) == ')')
                        c--;
                    if (c == 0)
                        break;
                }
                ex = ex.substring(0,j) + form.format(Math.log(Double.parseDouble(eval(ex.substring(j+5,k))))) + ex.substring(k+1,ex.length());
                j = -1;
                continue;
            }
            //fact
            if (ex.substring(j, j+4).equals("fact")){
                c = 1;
                for (k = j+6; k < ex.length(); k++){
                    if (ex.charAt(k) == '(')
                        c++;
                    else if (ex.charAt(k) == ')')
                        c--;
                    if (c == 0)
                        break;
                }
                tmp = Double.parseDouble(eval(ex.substring(j+7,k)));
                if (tmp % 1 != 0)
                    ex = ex.substring(0,j) + form.format(gamma(tmp+1)) + ex.substring(k+1,ex.length());
                else
                    ex = ex.substring(0,j) + form.format(fact(tmp)) + ex.substring(k+1,ex.length());
                j = -1;
                continue;
            }
        }
        return ex;
    }

    /**
     * Changes an infix expression to a postfix one, using Shunting Yard Algorithm
     * @param ex is the infix expression
     * @return the postfix expression
     */
    public static String toPostfix (String ex){
        //Defines required variables
        Stack output = new Stack();
        Stack operator = new Stack();
        String tok = new String();
        StringTokenizer st = new StringTokenizer (ex," ");
        String result = "";
        //While there are operators/operands left in the expression, keep looping
        while (st.hasMoreTokens()){
            tok = st.nextToken();
            //Multiplication, Division, Modulo, Choose, Permutation, And, Xor
            //Pop from operator and push to output, then push token to operator
            if ((tok.equals("*")) || (tok.equals("/")) || (tok.equals("%")) || (tok.equals("ncr")) || (tok.equals("npr")) || (tok.equals("and")) || (tok.equals("xor"))){
                if (!operator.empty()){
                    if ((operator.peek().equals("*")) || (operator.peek().equals("/")) || (operator.peek().equals("^")) || (operator.peek().equals("%")) || (operator.peek().equals("and")) || (operator.peek().equals("xor")))
                        output.push(operator.pop());
                }
                operator.push(tok);
            }
            //Addition, Subtraction, Or
            //Pop from operator and push to output, then push token to operator
            else if ((tok.equals("+")) || (tok.equals("-")) || (tok.equals("or"))){
                if (!operator.empty()){
                    if ((operator.peek().equals("+")) || (operator.peek().equals("-")) || (operator.peek().equals("*")) || (operator.peek().equals("/")) || (operator.peek().equals("^")) || (operator.peek().equals("%")) || (operator.peek().equals("ncr")) || (operator.peek().equals("npr")) || (operator.peek().equals("and")) || (operator.peek().equals("xor")) || (operator.peek().equals("or")))
                        output.push(operator.pop());
                }
                operator.push(tok);
            }
            //Exponents
            else if (tok.equals("^"))
                operator.push(tok);
            //Brackets
            else if (tok.equals("("))
                operator.push(tok);
            //Pops until the corresponding bracket is reached
            else if (tok.equals(")")){
                int idx = operator.search("(");
                for (int j = 0; j < idx-1; j++)
                    output.push(operator.pop());
                operator.pop();
            }
            //If the token is an operand, push it to output
            else
                output.push(tok);
        }
        //Pop everything off if there's operands remaining from operator and push to output
        while (!(operator.empty()))
            output.push(operator.pop());
        //Pop everything from output
        while (!(output.empty()))
            result = output.pop() + " " + result;
        return result;
    }

    /**
     * Evaluates a postfix expression
     * @param ex is a postfix expression
     * @return the value of the expression
     */
    public static String evalPostfix (String ex){
        Stack operator = new Stack();
        String tok = new String();
        double value,tmp,v;
        int a,b;
        StringTokenizer st = new StringTokenizer (ex," ");
        while (st.hasMoreTokens()){
            tok = st.nextToken();
            //Multiplication
            if (tok.equals("*")){
                value = Double.parseDouble((String)operator.pop());
                value *= Double.parseDouble((String)operator.pop());
                operator.push(String.valueOf(value));
            }
            //Division
            else if (tok.equals("/")){
                value = Double.parseDouble((String)operator.pop());
                value = Double.parseDouble((String)operator.pop()) / value;
                operator.push(String.valueOf(value));
            }
            //Addition
            else if (tok.equals("+")){
                value = Double.parseDouble((String)operator.pop());
                value += Double.parseDouble((String)operator.pop());
                operator.push(String.valueOf(value));
            }
            //Subraction
            else if (tok.equals("-")){
                value = Double.parseDouble((String)operator.pop());
                value = Double.parseDouble((String)operator.pop()) - value;
                operator.push(String.valueOf(value));
            }
            //Exponent
            else if (tok.equals("^")){
                tmp = Double.parseDouble((String)operator.pop());
                value = Double.parseDouble((String)operator.pop());
                operator.push(String.valueOf(Math.pow(value,tmp)));
            }
            //Modulo
            else if (tok.equals("%")){
                a = Integer.parseInt(String.valueOf(operator.pop()));
                b = Integer.parseInt(String.valueOf(operator.pop()));
                operator.push(String.valueOf(b%a));
            }
            //Choose
            else if (tok.equals("ncr")){
                tmp = Double.parseDouble((String)operator.pop());
                value = Double.parseDouble((String)operator.pop());
                operator.push(String.valueOf(fact(value)/(fact(value-tmp)*fact(tmp))));
            }
            //Permutation
            else if (tok.equals("npr")){
                tmp = Double.parseDouble((String)operator.pop());
                value = Double.parseDouble((String)operator.pop());
                operator.push(String.valueOf(fact(value)/fact(value-tmp)));
            }
            //And
            else if (tok.equals("and")){
                a = Integer.parseInt(String.valueOf(operator.pop()));
                b = Integer.parseInt(String.valueOf(operator.pop()));
                operator.push(String.valueOf(Conversion.and(Conversion.toBase(2,String.valueOf(b)),Conversion.toBase(2,String.valueOf(a)))));
            }
            //Or
            else if (tok.equals("or")){
                a = Integer.parseInt(String.valueOf(operator.pop()));
                b = Integer.parseInt(String.valueOf(operator.pop()));
                operator.push(String.valueOf(Conversion.or(Conversion.toBase(2,String.valueOf(b)),Conversion.toBase(2,String.valueOf(a)))));
            }
            //Xor
            else if (tok.equals("xor")){
                a = Integer.parseInt(String.valueOf(operator.pop()));
                b = Integer.parseInt(String.valueOf(operator.pop()));
                operator.push(String.valueOf(Conversion.xor(Conversion.toBase(2,String.valueOf(b)),Conversion.toBase(2,String.valueOf(a)))));
            }
            //Operand
            else
                operator.push(tok);
        }
        //Rounds the decimal
        DecimalFormat form = new DecimalFormat("#.##########");
        return form.format((Double.parseDouble(String.valueOf(operator.pop()))));
    }

    /**
     * Checks through the expression to change numbers in a different base to decimal
     * @param ex is the expression to be checked
     * @return the expression with all decimal numbers
     */
    public static String checkBase(String ex){
        String tok = "", s = "";
        StringTokenizer st = new StringTokenizer (ex);
        while (st.hasMoreTokens()){
            tok = st.nextToken();
            //If the token is an operand, convert it
            if (!(tok.equals("+")) && !(tok.equals("-")) && !(tok.equals("*")) && !(tok.equals("/")) && !(tok.equals("^")) && !(tok.equals("%")) &&
                !(tok.equals("ncr")) && !(tok.equals("npr")) && !(tok.equals("sin")) && !(tok.equals("cos")) && !(tok.equals("tan")) && !(tok.equals("asin")) &&
                !(tok.equals("acos")) && !(tok.equals("atan")) && !(tok.equals("neg")) && !(tok.equals("fact")) && !(tok.equals("ln")) && !(tok.equals("log")) &&
                !(tok.equals("(")) && !(tok.equals(")")) && !(tok.equals("and")) && !(tok.equals("or")) && !(tok.equals("xor")))
                s += Conversion.toDec(base, tok) + " ";
            //Keep the operators the way it is
            else
                s += tok + " ";
        }
        return s;
    }

    /**
     * Main method for evaluating the expression
     * @param ex is the expression to be evaluated
     * @return the evaluated expression
     */
    public static String eval(String ex){
        ex = checkBase(ex);
        ex = checkUnary(ex);
        ex = toPostfix(ex);
        ex = evalPostfix(ex);
        ex = Conversion.toBase(base, ex);
        return ex;
    }

    /*** MAIN METHOD **
     *
     * @param args
     */

    public static void main(String[] args) {
        //FIRST WRITE YOUR PSEUDOCODE USING COMMENTS, THEN FILL IN WITH CODE
        System.out.println("Starting...");
        base=10;
        System.out.println (eval("3 / 5 * 2"));
    }
}