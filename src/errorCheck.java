/**
@param  c a parameter to the method.
@return d what the method is returning
@throws NullPointerException when this is thrown.
 */
//Import Statements Listed Alphabetically
import java.io.*;           //used for any type of input or output
import java.util.*;         //useful utilities like Scanner

public class errorCheck {

    /*** MAIN METHOD **
     * @param s
     * @return error
     */

    static public String checkError(String s){


        //counter for bracket stack
        int x=0;
        //tokenize strings
        StringTokenizer st = new StringTokenizer(s," ");
        String token="";

        //loop throguh strings
        while(st.hasMoreTokens()){
            token = st.nextToken();
            //if left bracket add to stack
            if(token.equals("(")){
                x++;
            }
            //if right bracket subtract from stack
            if(token.equals(")")){
                if(x>0)x--;
                //if stack less than 0 brackets errors
                else {
                    return "Syntax Error: Brackets";
                }
            }
        }
        //if stack is not empty brackets error
        if(x!=0)return "Syntax Error: Brackets";

        


        return "";
    }

    /**
     * Remove extra spaces from expression
     * @param s expression
     * @return expression with no extra spaces
     */
    static public String removeSpaces(String s){

        String out="";
        int i;
        for(i=0;i<s.length();i++){

              if(s.charAt(i)==' '){
                  out+=String.valueOf(' ');
                  while(s.charAt(i)==' '){
                      i++;
                      if(i>=s.length())break;
                  }
                  i--;
              }
            else out += String.valueOf(s.charAt(i));
        }
        return out;
    }

    /**
     * Check if string is a number
     * @param s string
     * @return if string is a number
     */
    static public boolean isNum(String s){

        boolean x= true;
        int i;
        if (s.length()==1&&s.charAt(0)=='-')return false;
        for(i=0;i<s.length();i++){
            if(s.charAt(i)!='!'&&(s.charAt(i)<'0'||s.charAt(i)>'9')&&(s.charAt(i)<'A'||s.charAt(i)>'F')&&s.charAt(i)!='.'&&s.charAt(i)!='~'&&s.charAt(i)!='-')x=false;
        }
        return x;

    }


    /**
     * Formating expression to be evaluated
     * @param s expression
     * @return formatted expression
     */
    static public String formatExpression(String s){

       
    //tokenize string into strings
      StringTokenizer st = new StringTokenizer(s," ");

      String expression = "";


       while(st.hasMoreTokens()){
            //get token
           String token = st.nextToken();
           //System.out.println(token);
           //if token has a fraction change to decimal
           if(token.indexOf('~')>=0){
               expression+=Fraction.fracToDouble(token)+" ";
           }

           //if token has root convery to format of ^
             else if (token.indexOf('√')>=0){
                //take 1/x root ^
                expression+=token.substring(token.indexOf('√')+1)+" ^ "+(1.0/Double.valueOf(token.substring(0,token.indexOf('√'))));

             }
           //if token has pi change to decimal value

           else if(token.equals("π")){
               expression+= String.valueOf(Math.PI)+ " ";
           }
           //if token has ! change to fact ( )
            else if (token.charAt(0)=='!'){
                expression+="fact ( "+token.substring(1)+" ) ";
            }
           //if token is tan,sin,cos,acos,asin,atan,log,ln add brackets to be formatte
            else if (token.equals("tan") || token.equals("sin") || token.equals("cos")
                   ||
                    token.equals("acos")||token.equals("asin")||token.equals("atan")||
                    token.equals("log")||token.equals("ln")){
                expression+=token;
                String n=st.nextToken();

                //else if already brackets
                if(n.equals("(")){
                    expression+=" ( ";
                }
                //if pi change to value of pi
                else if(n.equals("π")){
                    expression+= " ( "+Math.PI+" ) ";
                }
                //else add brackets
                   else expression+= " ( "+n+" ) ";
            }
          //add spaces
           else expression +=token + " ";
        }
        //remove spaces
        removeSpaces(expression);

        //check for no oeprator, make into multiplaication

        //first tokenizer and parse token and next token
      StringTokenizer st2 = new StringTokenizer(expression," ");
      st = new StringTokenizer(expression," ");
      String expression2=expression;
      if(st2.hasMoreTokens()){
          //add to second expression
         expression2=st2.nextToken()+" ";
         while(st2.hasMoreTokens()){
          String t = st.nextToken(),t2=st2.nextToken();

          //if end bracket and next bracket is ( make to multiplciation

          if(t.equals(")")||isNum(t)){
                    if(t2.equals("(")||isNum(t2)||t2.equals("sin")||t2.equals("sin")
                            ||t2.equals("cos")||t2.equals("tan")||t2.equals("asin")
                            ||t2.equals("acos")||t2.equals("atan")||t2.equals("log")
                            ||t2.equals("ln")){

                        expression2+=" * ";

                    }
              }
          //add second term and add space
              expression2+=t2+" ";

          }

        }

        //remove spaces
        return removeSpaces(expression2);

    }

    /**
     * Testing function for error check
     * @param args no arguments
     */
    public static void main(String[] args) {


        System.out.println(formatExpression("25 +"));
        //System.out.println(Evaluate.eval(formatExpression("sin ( 1 + sin 4 )")));
        
	
    }
}



