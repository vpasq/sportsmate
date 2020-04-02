package sportsmate.utilty;

import java.util.Scanner;
import java.util.regex.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class check {
    /**
     * check str include special char
     * @param str
     * @return true means include，false means not include
     */
    public static boolean isSpecialChar(String str) {
        // check string empty
        if (str == null || str.length() == 0) {
            return true;
        }
        // check SpecialChar
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }
    /**
     * check str include number
     * @param str
     * @return true means include，false means not include
     */
    public static boolean isNumberChar(String str) {
        // check string empty
        if (str == null || str.length() == 0) {
            return true;
        }
        // check NumberChar
        String regEx =".*\\d+.*";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.matches();
    }
    /**
     * check str equal gender word, three option: male, female, secret
     * @param str
     * @return true means legal option，false means not legal option
     */
    public static boolean isgenderChar(String str) {
        // check string empty
        if (str == null || str.length() == 0) {
            return false;
        }
        else return true;
    }
    /**
     * check str is a legal date
     * @param str
     * @return true means legal date，false means not legal date and the day is today or after today
     */
    public static boolean check_date_of_day (String str) {
        SimpleDateFormat sd=new SimpleDateFormat("MM-dd-yyyy");
        try {
            sd.setLenient(false);
            Date input_date = sd.parse(str);//change str into a date
            Date current_date = sd.parse(sd.format(new Date()));//get current time
            long time1 = input_date.getTime();
            long time2 = current_date.getTime();
            long diff = time1 - time2 ;
            if (diff >= 0){
                return true;
            }
        }
        catch (Exception e) {
            return false;
        }
        return false;
    }
    /**
     * check str is a legal time
     * @param str
     * @return true means legal time，false means not legal time format
     */
    public static boolean check_date_in_the_day (String str) {
        SimpleDateFormat sd=new SimpleDateFormat("HH:mm:ss");
        try {
            sd.setLenient(false);
            sd.parse(str);//change str into a date
            }
        catch (Exception e) {
            return false;
        }
        return true;
    }
    /**
     * check str is a legal game type
     * @param str
     * @return str of game type，or return "unlegal"
     */
    public static String check_game_type (String str) {
        if(str.equals("1")){
            return "1";
        } else if(str.equals("2")){
            return "2";
            } else if(str.equals("3")){
                return "3";
                } else if(str.equals("4")){
                     return "4";
                    } else if(str.equals("5")) {
                        return "5";
                        } else{
                             return "unlegal";
                            }
    }
    /**
     * check str is a postive interger AND less 20
     * @param str
     * @return ture it is a is a postive interger AND less 20, false it is not
     */
    public static boolean isPostiveInt(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);

        if( isNum.matches()){
            int n = Integer.parseInt(str);
            if (n>0 && n <=20){
                return true;
            }
            else return false;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        Boolean flag = isPostiveInt(str);
        System.out.println("str合法：" + flag);

    }


}


