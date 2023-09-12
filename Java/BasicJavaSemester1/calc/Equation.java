package calc;

import calc.util.SheetException;
import calc.util.CellName;

public class Equation implements Evaluable {
    private String operand1;
    private String operand2;
    private char operator;

    public Equation(String str){
        CellName cn = new CellName();
        for(int i = 0;i < str.length();i++){
            if(Integer.parseInt(String.valueOf(str.charAt(i))) < 0 || str.charAt(i) != '+' || str.charAt(i) != '-' || str.charAt(i) != '*' || str.charAt(i) != '/'){
                throw new IllegalArgumentException("ERROR");
            }
            else if(str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == '*' || str.charAt(i) == '/'){
                this.operator = str.charAt(i);
            }
        }
        String[] arrOfStr = str.split(String.valueOf(operator),str.length());
        if(!cn.isCellNameValid(arrOfStr[0]) || !cn.isCellNameValid(arrOfStr[1])){
            throw new IllegalArgumentException("ERROR");
        }
        else{
            this.operand1 = arrOfStr[0];
            this.operand2 = arrOfStr[1];
        }
    }

    public int eval(Sheet sheet) throws SheetException{
        int op1 = sheet.constructIntFromOperandStr(operand1,sheet);
        int op2 = sheet.constructIntFromOperandStr(operand2,sheet);
        int end = 0;
        if(operator == '+'){
            end = op1 + op2;
        }
        else if(operator == '-'){
            end = op1 - op2;
            if(end < 0){
                throw new ArithmeticException("Negative");
            }
        }
        else if(operator == '*'){
            end = op1 * op2;
        }
        else if(operator == '/'){
            if(op2 == 0){
                throw new ArithmeticException("Zero");
            }
            else{
                end = op1 / op2;
            }
        }
        return end;
    }
}
