package calc;

import calc.util.CellName;
import calc.util.SheetException;

import java.lang.StringBuilder;

public class Sheet {
    private int numOfRows;
    private int numOfCols;
    private Evaluable[][] array;

    public Sheet(int numOfRows,int numOfCols){
        if(numOfCols > 25 || numOfCols < 0 || numOfRows < 0){
            throw new java.lang.IllegalArgumentException("ERROR");
        }
        else{
            this.numOfRows = numOfRows;
            this.numOfCols = numOfCols;
            array = new Evaluable[numOfRows][numOfCols];
        }
    }

    public void insertToSheet(String cellName,Evaluable evaluable) throws SheetException{
        CellName cn = new CellName();
        int col = cn.getColIndexFromCellName(cellName);
        int row = cn.getRowIndexFromCellName(cellName);
        array[row][col] = evaluable;
    }

    public Evaluable getFromSheet(String cellName) throws SheetException{
        CellName cn = new CellName();
        int col = cn.getColIndexFromCellName(cellName);
        int row = cn.getRowIndexFromCellName(cellName);
        return array[col][row];
    }

    public int constructIntFromOperandStr(String operandStr,Sheet sheet) throws SheetException{
        if(operandStr.charAt(0) > 'A' && operandStr.charAt(0) < 'Z'){
            Evaluable evaluable = sheet.getFromSheet(operandStr);
            return evaluable.eval(sheet);
        }
        else{
            return Integer.parseInt(operandStr);
        }
    }

    public int getNumOfRows(){
        return numOfRows;
    }

    public int getNumOfCols(){
        return numOfCols;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        try{
            String delim = "";
            for(int i = 0;i < numOfRows;i++){
                for(int j = 0;j < numOfCols;j++){
                    if(j == numOfCols - 1){
                        delim = System.lineSeparator();
                    }
                    else{
                        delim = " ";
                    }
                    if(array[i][j] == null){
                        str.append("null").append(delim);
                    }
                    else{
                        str.append(array[i][j].eval(this)).append(delim);
                    }
                }
            }
        }catch (Exception exc) {}
        return str.toString();
    }
}
