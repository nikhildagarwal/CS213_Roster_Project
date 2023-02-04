package group;
import java.util.Calendar;
import java.util.StringTokenizer;

/*@author Nikhil Agarwal*/
public class Date implements Comparable<Date>{
    private int year;
    private int month;
    private int day;

    public static final int[] daysInMonth = {31,28,31,30,31,30,31,31,30,31,30,31};

    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;

    public static final int JANUARY = 1;
    public static final int FEBRUARY = 2;
    public static final int DECEMBER = 12;
    public static final int INDEX_DIFF = 1;
    public static final int FIRSTDAY = 1;
    public static final int LEAP_YEAR_DAYS = 29;
    public static final int MAX_LIFESPAN = 200;
    public static final int MIN_AGE = 16;

    @Override
    public boolean equals(Object obj){
        Date date = (Date) obj;
        return day == date.day && year == date.year && month == date.month;
    }

    @Override
    public String toString(){
        String slash = "/";
        return month+slash+day+slash+year;
    }

    @Override
    public int compareTo(Date date){
        int compareYear = Integer.compare(year,date.year);
        if(compareYear==0){
            int compareMonth = Integer.compare(month,date.month);
            if(compareMonth==0){
                return Integer.compare(day,date.day);
            }else{
                return compareMonth;
            }
        }else{
            return compareYear;
        }
    }

    public Date(){
        Calendar calendar = Calendar.getInstance();
        this.year = calendar.get(Calendar.YEAR);
        this.day = calendar.get(Calendar.DATE);
        this.month = calendar.get(Calendar.MONTH)+INDEX_DIFF;
    }
    public Date(String date){
        StringTokenizer dateTokens = new StringTokenizer(date,"/");
        this.month = Integer.parseInt(dateTokens.nextToken());
        this.day = Integer.parseInt(dateTokens.nextToken());
        this.year = Integer.parseInt(dateTokens.nextToken());

    }

    public boolean isValid(){
        boolean dayCheck = false;
        if(validMonth(month)){
            if(month==FEBRUARY){
                boolean leapYear = isLeapYear(year);
                if(leapYear){
                    dayCheck = validDay(day,LEAP_YEAR_DAYS);
                }else{
                    int daysInFeb = daysInMonth[FEBRUARY-INDEX_DIFF];
                    dayCheck = validDay(day,daysInFeb);
                }
            }else{
                int daysInCurrentMonth = daysInMonth[month-INDEX_DIFF];
                dayCheck = validDay(day,daysInCurrentMonth);
            }
        }else{
            return false;
        }
        return dayCheck && validYear(year) && validMonth(month);
    }

    private boolean validDay(int day,int totalDays){
        if(day>=FIRSTDAY && day<=totalDays){
            return true;
        }
        return false;
    }
    private boolean isLeapYear(int year){
        if(year%QUADRENNIAL==0){
            if(year%CENTENNIAL==0){
                if(year%QUATERCENTENNIAL==0){
                    return true;
                }
            }else{
                return true;
            }
        }
        return false;
    }

    private boolean validYear(int year){
        Calendar calendar = Calendar.getInstance();
        int currYear = calendar.get(Calendar.YEAR);
        if(currYear>=year && year>=currYear-MAX_LIFESPAN){
            return true;
        }
        return false;
    }

    private boolean validMonth(int month){
        if(month>=JANUARY && month<=DECEMBER){
            return true;
        }
        return false;
    }

    public boolean isValidAge(Date today){
        int yearDiff = today.year - year;
        if(yearDiff>MIN_AGE){
            return true;
        }else if(yearDiff<MIN_AGE){
            return false;
        }else{
            int monthDiff = today.month-month;
            if(monthDiff>0){
                return true;
            }else if(monthDiff<0){
                return false;
            }else{
                int dayDiff = today.day-day;
                if(dayDiff<0){
                    return false;
                }else{
                    return true;
                }
            }
        }
    }
}
