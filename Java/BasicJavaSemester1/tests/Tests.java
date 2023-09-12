package tests;

import calc.util.CellName;
import calc.util.SheetException;
import calc.Equation;
import calc.Evaluable;
import calc.Num;
import calc.Sheet;

import org.junit.Test;
import static org.junit.Assert.*;

public class Tests {

    @Test
    public void isCellNameValid() {
        CellName cn = new CellName();
        String t1 = " 1";
        assertFalse(cn.isCellNameValid(t1));
        String t2 = "a1";
        assertFalse(cn.isCellNameValid(t2));
        String t3 = "A1";
        assertFalse(cn.isCellNameValid(t3));
        String t4 = "A1";
        assertTrue(cn.isCellNameValid(t4));
        String t5 = "A10";
        assertTrue(cn.isCellNameValid(t5));
    }

    @Test
    public void getRowIndexFromCellName() throws SheetException{
        CellName cn = new CellName();
        String t1 = "A8";
        assertEquals(cn.getRowIndexFromCellName(t1),8);
        String t2 = "A11";
        assertEquals(cn.getRowIndexFromCellName(t2),11);
    }

    @Test
    public void getColIndexFromCellName() throws SheetException{
        CellName cn = new CellName();
        String t1 = "A8";
        assertEquals(cn.getColIndexFromCellName(t1),0);
        String t2 = "C11";
        assertEquals(cn.getColIndexFromCellName(t2),2);
    }

    @Test
    public void evalTest() throws SheetException{
        int x = 5;
        Num n = new Num(x);
        assertEquals(n.eval(null),x);
    }

    @Test(expected = IllegalArgumentException.class)
    public void equationTest1(){
        String str = "A +11";
        Equation n = new Equation(str);
    }

    @Test(expected = IllegalArgumentException.class)
    public void equationTest2(){
        String str = "a+11";
        Equation n = new Equation(str);
    }

    @Test(expected = IllegalArgumentException.class)
    public void equationTest3(){
        String str = "A(11";
        Equation n = new Equation(str);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sheetTest1(){
        String expected = "6 5" + System.lineSeparator() + "2 6" + System.lineSeparator() + "2 9";
        Sheet sheet = new Sheet(3,2);
        Num num1 = new Num(6);
        Num num2 = new Num(5);
        Num num3 = new Num(2);
        Num num4 = new Num(6);
        Num num5 = new Num(2);
        Num num6 = new Num(9);
        sheet.insertToSheet("A1", num1);
        sheet.insertToSheet("B1", num2);
        sheet.insertToSheet("A2", num3);
        sheet.insertToSheet("B2", num4);
        sheet.insertToSheet("A3", num5);
        sheet.insertToSheet("B3", num6);

        assertEquals(sheet.toString(),expected);
    }

    @Test()
    public void sheetTest(){
        try{
            String expected1 = "6 5" + System.lineSeparator() + "2 6" + System.lineSeparator() + "2 9";
            String expected2 = "6 5 11" + System.lineSeparator() + "2 6 8" + System.lineSeparator() + "2 9 11";
            String expected3 = "6 5 11 5" + System.lineSeparator() + "2 6 8 4" + System.lineSeparator() + "2 9 11 5";
            String expected4 = "6 5 50" + System.lineSeparator() + "2 6 60" + System.lineSeparator() + "2 9 90";

            Sheet sheet = new Sheet(3,3);
            Num num1 = new Num(6);
            Num num2 = new Num(5);
            Num num3 = new Num(2);
            Num num4 = new Num(6);
            Num num5 = new Num(2);
            Num num6 = new Num(9);

            Equation equation1 = new Equation("6+5");
            Equation equation2 = new Equation("2+6");
            Equation equation3 = new Equation("2+9");

            Equation equation4 = new Equation("11/2");
            Equation equation5 = new Equation("8/2");
            Equation equation6 = new Equation("11/2");

            Equation equation7 = new Equation("5*10");
            Equation equation8 = new Equation("6*10");
            Equation equation9 = new Equation("9*10");

            sheet.insertToSheet("A1", num1);
            sheet.insertToSheet("B1", num2);
            sheet.insertToSheet("A2", num3);
            sheet.insertToSheet("B2", num4);
            sheet.insertToSheet("A3", num5);
            sheet.insertToSheet("B3", num6);

            assertEquals(sheet.toString(),expected1);

            sheet.insertToSheet("C1", equation1);
            sheet.insertToSheet("C2", equation2);
            sheet.insertToSheet("C3", equation3);

            assertEquals(sheet.toString(),expected2);

            sheet.insertToSheet("D1", equation4);
            sheet.insertToSheet("D2", equation5);
            sheet.insertToSheet("D3", equation6);

            assertEquals(sheet.toString(),expected3);

            sheet.insertToSheet("D1", equation4);
            sheet.insertToSheet("D2", equation5);
            sheet.insertToSheet("D3", equation6);

            assertEquals(sheet.toString(),expected3);

            sheet.insertToSheet("D1", equation7);
            sheet.insertToSheet("D2", equation8);
            sheet.insertToSheet("D3", equation9);

            assertEquals(sheet.toString(),expected4);
        }catch(SheetException e){}
    }
}

