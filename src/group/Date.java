package group;
import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * Implements our date object as a comparable object.
 * Consists of three integer components: year, month, day.
 * Overrides for equals, toString, and compareTo.
 * Implements methods to check if dates are valid, or year is a LeapYear.
 * @author Nikhil Agarwal, Hyeon Oh
 */
public class Date implements Comparable<Date>{
    private int year;
    private int month;
    private int day;

    //Array that holds the number of days in each month (Starting from January @index 0)
    //index1 holds value for nonLeapYear february.
    private static final int[] daysInMonth = {31,28,31,30,31,30,31,31,30,31,30,31};

    private static final int QUADRENNIAL = 4;
    private static final int CENTENNIAL = 100;
    private static final int QUATERCENTENNIAL = 400;

    private static final int JANUARY = 1;
    private static final int FEBRUARY = 2;
    private static final int DECEMBER = 12;
    private static final int INDEX_DIFF = 1;
    private static final int FIRSTDAY = 1;
    private static final int LEAP_YEAR_DAYS = 29;
    private static final int MIN_YEAR = 1900;
    private static final int MIN_AGE = 16;

    /**
     * Default constructor for Date object.
     * Initializes object to current day.
     */
    public Date(){
        Calendar calendar = Calendar.getInstance();
        this.year = calendar.get(Calendar.YEAR);
        this.day = calendar.get(Calendar.DATE);
        this.month = calendar.get(Calendar.MONTH)+INDEX_DIFF;
    }

    /**
     * Secondary constructor for Date object that accepts string input of format: mm/dd/yyyy
     * @param date date in format mm/dd/yyyy
     */
    public Date(String date){
        StringTokenizer dateTokens = new StringTokenizer(date,"/");
        this.month = Integer.parseInt(dateTokens.nextToken());
        this.day = Integer.parseInt(dateTokens.nextToken());
        this.year = Integer.parseInt(dateTokens.nextToken());
    }

    /**
     * Override of equals method from object class in Java.
     * Compares each int value in Date.
     * @param obj object to be checked with.
     * @return true if the Date objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj){
        Date date = (Date) obj;
        return day == date.day && year == date.year && month == date.month;
    }

    /**
     * Override of toString method in object class in Java.
     * Prints each int value of Date, with slashes in between.
     * Method used by System.out.println to print our object.
     * @return date as string.
     */
    @Override
    public String toString(){
        String slash = "/";
        return month+slash+day+slash+year;
    }

    /**
     * Override of compareTo method in the Java comparable class.
     * @param date the object to be compared.
     * @return -1  if older date, 0 if same date, 1 if more recent date.
     */
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

    /**
     * Checks if date is a valid Calendar date.
     * @return true if date is a calendar date, false otherwise.
     */
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

    /**
     * Checks to see if there is at least 16 years time between the date object and today.
     * @param today Date object of today.
     * @return true if there is at least 16 years difference, false otherwise.
     */
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
    
    /**
     * Checks if a day value is within the total number of days in a given month.
     * @param day day to be checked.
     * @param totalDays total number of days in the specific month of day given above.
     * @return true if the day is within the number of days in the month, false otherwise.
     */
    private boolean validDay(int day,int totalDays){
        if(day>=FIRSTDAY && day<=totalDays){
            return true;
        }
        return false;
    }

    /**
     * Checks if a year is a Leap year or not.
     * @param year year to be checked.
     * @return true if leap year, false otherwise.
     */
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

    /**
     * Checks if given year is a valid year before today, and after 1900.
     * @param year year to be checked.
     * @return true if year is valid, false otherwise.
     */
    private boolean validYear(int year){
        Calendar calendar = Calendar.getInstance();
        int currYear = calendar.get(Calendar.YEAR);
        if(currYear>=year && year>MIN_YEAR){
            return true;
        }
        return false;
    }

    /**
     * Checks if given month is between January and December (inclusive).
     * @param month month to be checked.
     * @return return true if month is valid, false otherwise.
     */
    private boolean validMonth(int month){
        if(month>=JANUARY && month<=DECEMBER){
            return true;
        }
        return false;
    }

    /**
     * Used to test the test cases for the isValid method of the Date class
     * @param date the date that the test case contains
     * @param expectedOutput the correct output based off of the test case date
     * @param actualOutput the output generated by calling the isValid method
     */
    private static void testResult(Date date, boolean expectedOutput, boolean actualOutput)
    {
        if(actualOutput == expectedOutput)
        {
            System.out.println("isValid method has successfully passed the test case.");
        }
        else
        {
            System.out.println("isValid method has not passed the test case.");
        }
    }

    /**
     * Testbed main to test the isValid method
     * @param args command line arguments
     */
    public static void main(String[] args)
    {
        Date date = new Date("2/29/2011");
        boolean expectedOutput = false;
        boolean actualOutput = date.isValid();
        System.out.println("**Test case #1: a date in a non-leap year has only 28 days in February.");
        testResult(date, expectedOutput, actualOutput);

        date = new Date("2/29/2016");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #2: a date in a leap year has 29 days in February.");
        testResult(date, expectedOutput, actualOutput);

        date = new Date("13/21/1999");
        expectedOutput = false;
        actualOutput = date.isValid();
        System.out.println("**Test case #3: a date with an invalid month value. ");
        testResult(date, expectedOutput, actualOutput);

        date = new Date("3/32/2003");
        expectedOutput = false;
        actualOutput = date.isValid();
        System.out.println("**Test case #4: a date with an invalid day value. ");
        testResult(date, expectedOutput, actualOutput);

        date = new Date("1/29/1899");
        expectedOutput = false;
        actualOutput = date.isValid();
        System.out.println("**Test case #6: a date with an invalid year value. ");
        testResult(date, expectedOutput, actualOutput);

        date = new Date("4/31/2003");
        expectedOutput = false;
        actualOutput = date.isValid();
        System.out.println("**Test case #6: a date with an invalid number of days in a specific month. ");
        testResult(date, expectedOutput, actualOutput);

        date = new Date("1/31/2003");
        expectedOutput = true;
        actualOutput = date.isValid();
        System.out.println("**Test case #7: a date with a valid number of days in a specific month. ");
        testResult(date, expectedOutput, actualOutput);



    }
}
