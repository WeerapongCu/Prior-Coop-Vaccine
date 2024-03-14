import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Scanner;

class Person {

    private String gender;
    private String[] splitStr;
    private String start = "8 สิงหาคม พ.ศ.2564";
    private String end = "31 สิงหาคม พ.ศ.2564";
    private String pattern = "วันEEEEที่ dd MMMM พ.ศ.yyyy";
    private boolean pattern_check = false;

    public Person(String gender, String date_of_birth) {
        this.gender = gender;
        this.splitStr = date_of_birth.split(" ");
    }

    public int get_year() {
        return Integer.parseInt(splitStr[3].substring(4));// Year
    }

    public String get_month() {
        return splitStr[2]; // Month
    }

    public int get_day() {
        return Integer.parseInt(splitStr[1]); // day
    }

    public int get_numberMonth() {
        if (get_month() == "มกราคม") {
            return 1;
        } else if (get_month() == "กุมภาพันธ์") {
            return 2;
        } else if (get_month() == "มีนาคม") {
            return 3;
        } else if (get_month() == "เมษายน") {
            return 4;
        } else if (get_month() == "พฤษภาคม") {
            return 5;
        } else if (get_month() == "มิถุนายน") {
            return 6;
        } else if (get_month() == "กรกฎาคม") {
            return 7;
        } else if (get_month() == "สิงหาคม") {
            return 8;
        } else if (get_month() == "กันยายน") {
            return 9;
        } else if (get_month() == "ตุลาคม") {
            return 10;
        } else if (get_month() == "พฤศจิกายน") {
            return 11;
        } else if (get_month() == "ธันวาคม") {
            return 12;
        }else {
            return 0;
        }
    }

    public int get_age(){
        if (get_year() < 2499){
            return 2564 - get_year();
        }else if (get_year() == 2499 && get_numberMonth() <= 8){
            return 65;
        }else if (get_year() == 2499 && get_numberMonth() > 8){
            return 64;
        }else if(get_year() > 2499 && get_year() < 2562){
            return 2564 - get_year();
        }else if (get_year() >= 2562 && get_year() < 2564 ){
            return 2564 - get_year();
        }else{
            return 0;
        }
    }

    public boolean check_string(String date_of_birth) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        sdf.setLenient(false);

        try {
            Date date = sdf.parse(date_of_birth);
            pattern_check = true;
            return true;
        } catch (ParseException e) {
            pattern_check = false;
            return false;
        }
    }

    // public boolean check_string(){
    //     pattern = regex.compile(r'วันจันทร์ที่ \d{1,2} \p{Thai}+ พ\.ศ\.\d{4}');
    //     if(splitStr.length == 4 && get_day() > 0 && get_day() < 32){
    //         return true;
    //     }else{
    //         System.out.println(splitStr.length);
    //         return false;
    //     }
    // }

    public String eligible_flag() {
        if (pattern_check == true) {
            if(get_age() >= 65){
                return "1";
            }else if (get_age() == 64){
                start = "วันที่ " + get_day() + " " + get_month() + " พ.ศ." + get_year();
                return "2";
            }else if (get_age() >= 0 && get_age() <= 2) {
                return "3";
            }else if (get_age() == 0 && get_numberMonth() <= 2){
                start = "วันที่ " + get_day() + " " + get_numberMonth()+6 + " พ.ศ.2564 - 31 สิงหาคม พ.ศ.2564";
                return "4";
            }else if (get_age() == 0 && get_numberMonth() > 2){
                return "5";
            }else {
                return "0";
            }
        }else {
            return "5";//"ไม่สามารถเข้ารับบริการได้"
        }
    }
}

public class Vaccine {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // case 1 >= 65
        // หญิง 
        // วันอังคารที่ 10 สิงหาคม พ.ศ.2499
        // case 2 < 65 && > 2
        // ชาย 
        // วันพุธที่ 10 สิงหาคม พ.ศ.2500  วันพุธที่ 24 ตุลาคม พ.ศ.2499
        // case 3 < 2 && > 0
        // หญิง
        // วันจันทร์ที่ 12 สิงหาคม พ.ศ.2562
        // case 4 = 0 month < 2
        // ชาย 
        // วันจันทร์ที่ 29 สิงหาคม พ.ศ.2561
        // case 5 = 0 month > 2
        // หญิง 
        // วันจันทร์ที่ 10 สิงหาคม พ.ศ.2499
        String gender = sc.nextLine();
        String Date_of_Birth = sc.nextLine();
        Person person = new Person(gender, Date_of_Birth);
       if (person.check_string(Date_of_Birth) == true) {
           System.out.println("String matches the date format pattern");
       } else {
           System.out.println("String does not match the date format pattern");
       }
        System.out.println(person.get_month() + " <- print month");
    }   
}