package calc;

import calc.util.SheetException;

public class Num implements Evaluable {
    private int num;
    public Num(int num){
        if(num < 0){
            throw new IllegalArgumentException("Negative");
        }
        else{
            this.num = num;
        }
    }

    public int eval(Sheet sheet) throws SheetException{
        return num;
    }
}
