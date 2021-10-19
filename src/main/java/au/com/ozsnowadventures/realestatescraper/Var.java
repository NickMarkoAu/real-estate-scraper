/*
 * Var.java
 *
 * Created on 25 November 2008, 13:27
 */
package au.com.ozsnowadventures.realestatescraper;

/**
 *
 * @author Server
 * @version
 */
import java.math.BigDecimal;

public class Var {

    /**
     * Creates new Var
     */
    public Var() {
    }

    public static boolean isFilled(String val, int len) {
        if ((val == null) || (val.length() < len)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean notFilled(String val) {
        return !(isFilled(val, 1));
    }

    public static boolean notFilled(String val, int len) {
        return !(isFilled(val, len));
    }

    public static boolean isFilled(String val) {
        return isFilled(val, 1);
    }

    public static String varNull(String dataVar) {
        if (isFilled(dataVar)) {
            return dataVar;
        } else {
            return "";
        }
    }

    public static String varSpace(String dataVar) {
        if (isFilled(dataVar)) {
            return dataVar;
        } else {
            return "&nbsp;";
        }
    }

    public static boolean matches(String dataVar, String val) {
        if (notFilled(dataVar)) {
            return false;
        }
        if (dataVar.equals(val)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isY(String dataVar) {
        if (dataVar == null) {
            return false;
        }
        if (dataVar.equals("Y")) {
            return true;
        } else {
            return false;
        }
    }

    public static String sel(String dataVar, String val) {
        if (dataVar == null) {
            return "";
        }
        if (dataVar.equals(val)) {
            return "SELECTED";
        } else {
            return "";
        }
    }

    public static String sel(int dataVar, int val) {
        if (dataVar == 0) {
            return "";
        }
        if (dataVar == (val)) {
            return "SELECTED";
        } else {
            return "";
        }
    }

    public static String chk(String dataVar, String val) {
        if (dataVar == null) {
            return "";
        }
        if (dataVar.equals(val)) {
            return "CHECKED";
        } else {
            return "";
        }
    }

    public static boolean hasBox(String[] boxList, int idx) {
        if (boxList == null) {
            return false;
        }
        String sIdx = Integer.toString(idx);
        for (int i = 0; i < boxList.length; i++) {
            if (boxList[i].equals(sIdx)) {
                return true;
            }
        }
        return false;
    }

    public static int parseInt(String sInt) {
        if (Var.notFilled(sInt)) {
            return 0;
        }
        try {
            return Integer.parseInt(sInt);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static String numZero(int num) {
        if (num == 0) {
            return "";
        } else {
            return Integer.toString(num);
        }
    }

    public static String numZero(BigDecimal num) {
        if (num == null) {
            return "";
        }
        if (num.compareTo(new BigDecimal("0.00")) == 0) {
            return "";
        } else {
            return num.toString();
        }
    }

    public static boolean notZero(BigDecimal num) {
        if (num.compareTo(new BigDecimal("0.00")) == 0) {
            return false;
        } else {
            return true;
        }
    }

    public static String bigNull(String numString) {
        if (Var.isFilled(numString)) {
            return numString;
        } else {
            return "0.00";
        }
    }

    public static BigDecimal bigNull(BigDecimal bd) {
        if (bd == null) {
            return new BigDecimal("0.00");
        } else {
            return bd;
        }
    }

    public static String bigSpace(BigDecimal bd) {
        if (bd == null) {
            return "&nbsp;";
        } else {
            return bd.toString();
        }
    }

    public static String getMon(int moNo) {
        switch (moNo) {
            case 1:
                return "JAN";
            case 2:
                return "FEB";
            case 3:
                return "MAR";
            case 4:
                return "APR";
            case 5:
                return "MAY";
            case 6:
                return "JUN";
            case 7:
                return "JUL";
            case 8:
                return "AUG";
            case 9:
                return "SEP";
            case 10:
                return "OCT";
            case 11:
                return "NOV";
            case 12:
                return "DEC";
            default:
                return "ERROR";
        }
    }

    public static String country(String code) {
        if (code.equals("AU")) {
            return "DOMESTIC";
        } else if (code.equals("NZ")) {
            return "NEW ZEALAND";
        } else if (code.equals("CA")) {
            return "CANADA";
        } else if (code.equals("US")) {
            return "USA";
        } else if (code.equals("JP")) {
            return "JAPAN";
        } else if (code.equals("AS")) {
            return "AUSTRIA";
        } else if (code.equals("FR")) {
            return "FRANCE";
        } else if (code.equals("SW")) {
            return "SWITZERLAND";
        } else if (code.equals("ZI")) {
            return "INTERNATL MIX";
        } else {
            return "UNKNOWN";
        }
    }

    public static String capFirst(String str) {
        if (Var.notFilled(str)) {
            return "";
        } else if (str.length() == 1) {
            return str.toUpperCase();
        } else {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }

    // if BigDecimal has no decimal value, return String with int value,
    // else return String value of BigDecimal with scale(1)
    public static String strScale(BigDecimal bd) {
        if (bd == null) {
            return "0";
        } else {
            BigDecimal iBig = new BigDecimal(String.valueOf(bd.intValue()));
            if (iBig.compareTo(bd) == 0) {
                return iBig.toString();
            } else {
                return bd.setScale(1, BigDecimal.ROUND_HALF_DOWN).toString();
            }
        }
    }

    public static boolean isMatchNull(String dataVar, String matchVar) {
        if (dataVar == null && matchVar == null) {
            return true;
        }
        if ((dataVar == null && matchVar != null) || (dataVar != null && matchVar == null)) {
            return false;
        }
        if (dataVar.equals(matchVar)) {
            return true;
        } else {
            return false;
        }
    }

    public static String zeroFill(int totNum, int curNum) {
        if (totNum < 100) {
            if (curNum < 10) {
                return "0";
            } else {
                return "";
            }
        } else { // >=100
            if (curNum < 10) {
                return "00";
            } else if (curNum < 100) {
                return "0";
            } else {
                return "";
            }
        }
    }

}
