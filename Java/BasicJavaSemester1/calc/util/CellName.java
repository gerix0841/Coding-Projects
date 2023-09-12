package calc.util;

import java.lang.StringBuilder;

public class CellName{
    private static String colIndexes = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public boolean isCellNameValid(String cellName){
        for(int i = 1;i < cellName.length();i++){
            int x = Integer.parseInt(String.valueOf(cellName.charAt(i)));
            if((colIndexes.indexOf(cellName.charAt(0)) == -1) || (x < 0)){
                return false;
            }
        }
        return true;
    }

    public int getRowIndexFromCellName(String cellName) throws SheetException{
        if(isCellNameValid(cellName)){
            StringBuilder str = new StringBuilder();
            for(int i = 1;i < cellName.length();i++){
                str.append(cellName.charAt(i));
            }

            return Integer.parseInt(str.toString());
        }
        else{
            throw new SheetException("inValid CellName");
        }
    }

    public int getColIndexFromCellName(String cellName) throws SheetException{
        if(isCellNameValid(cellName)){
            return colIndexes.indexOf(cellName.charAt(0));
        }
        else{
            throw new SheetException("inValid CellName");
        }
    }


}