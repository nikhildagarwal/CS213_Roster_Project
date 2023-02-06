package structure;
import data.Date;
import data.Profile;
import data.Student;
import enumerated.Major;

import java.util.Scanner;

/**
 * Implements the RosterManager class to take care of the roster and make changes to the roster.
 * Processes all console inputs and outputs a response to the console.
 * @author Nikhil Agarwal, Hyeon Oh
*/
public class RosterManager {
    public static final int MIN_CREDITS = 0;
    public static final int CODE_INDEX =0;
    public static final int FIRSTNAME_INDEX =1;
    public static final int LASTNAME_INDEX =2;
    public static final int DATE_INDEX = 3;
    public static final int MAJOR_INDEX = 4;
    public static final int CREDITS_INDEX = 5;
    public static final int ANY_NUMBER_OF_CREDITS = 0;
    public static final int SCHOOL_INDEX = 1;
    public static final int FULL_ROSTER = 0;
    public static final int SCHOOL_ROSTER = 1;
    public static final String allSchools = "";

    /**
     * this is a method that will continuously run the program until the user inputs in a "Q" to terminate the program.
     * this method will be used in RunProject1.java (Driver class)
     */
    public void run(){
        Roster roster = new Roster();
        System.out.println("Roster Manager running...");
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()){
            String[] tokens = processLine(scanner.nextLine());
            try{
                switch(tokens[CODE_INDEX]){
                    case "A":
                        processAdd(tokens,roster);
                        break;
                    case "R":
                        processRemove(tokens,roster);
                        break;
                    case "P":
                        processPrint(roster,FULL_ROSTER,allSchools);
                        break;
                    case "PS":
                        processPrintStanding(roster);
                        break;
                    case "PC":
                        processPrintMajor(roster);
                        break;
                    case "L":
                        processSchoolList(tokens,roster);
                        break;
                    case "C":
                        processChange(tokens,roster);
                        break;
                    case "Q":
                        System.out.println("Roster Manager terminated.");
                        return;
                    default:
                        System.out.println(tokens[0]+" is an invalid command!");
                }
            }catch (Exception e){

            }
        }
    }

    /**
     * uses string methods such as split to take inputs separately 
     * @param command the command which is taken to be split 
     * @return newLine which is a string array that returns the commands
     */
    private String[] processLine(String command){
        String[] line = command.split("\\s");
        int counter = 0;
        String[] newLine = new String[line.length];
        for(String token:line){
            if(!token.equals("")){
                newLine[counter] = token;
                counter++;
            }
        }
        return newLine;
    }

    /**
     * Processes the school list 
     * @param tokens string array that is taken from command line from the user
     * @param roster the roster object that has all students currently @Rutgers, students will be added to a school list from this roster.
     */
    private void processSchoolList(String[] tokens,Roster roster){
        String school = tokens[SCHOOL_INDEX].toUpperCase();
        Roster schoolRoster = new Roster();
        boolean validSchool = roster.filterBySchool(school,schoolRoster);
        if(!validSchool){
            System.out.println("School doesn't exist: "+tokens[SCHOOL_INDEX]);
            return;
        }
        processPrint(schoolRoster,SCHOOL_ROSTER,tokens[SCHOOL_INDEX]);
    }

    /**
     * Changes a student's major if the student is found the roster and the major they want to change too is valid.
     * @param tokens string array that is taken from the command line from the user
     * @param roster the roster object that will be searched to find a specific student.
     */
    private void processChange(String[] tokens, Roster roster){
        Major major = grabMajor(tokens);
        if(major==null){
            return;
        }
        Profile profile = new Profile(tokens[LASTNAME_INDEX],tokens[FIRSTNAME_INDEX],new Date(tokens[DATE_INDEX]));
        Student changedStudent = new Student(profile,major,ANY_NUMBER_OF_CREDITS);
        boolean changed = roster.change(changedStudent,major);
        if(changed){
            System.out.println(profile+" major changed to "+major);
        }else{
            System.out.println(profile+" is not in the roster.");
        }
    }

    /**
     * Displays the roster sorted by school, major
     * @param roster the argument that is to be displayed
     */
    private void processPrintMajor(Roster roster){
        if(roster.isEmpty()){
            System.out.println("Student roster is empty!");
            return;
        }
        System.out.println("* Student roster sorted by school, major **");
        roster.printByMajor();
        System.out.println("* end of roster **");
    }

    /**
     * Displays the roster sorted by standing by calling printByStanding method from the roster class
     * If the roster is empty, it will display to the user that it is empty 
     * @param roster the argument that is to be displayed
     */
    private void processPrintStanding(Roster roster){
        if(roster.isEmpty()){
            System.out.println("Student roster is empty!");
            return;
        }
        System.out.println("* Student roster sorted by standing **");
        roster.printByStanding();
        System.out.println("* end of roster **");
    }

    /**
     * Displays to the user whether the roster or list is empty, and displays the roster
     * @param roster argument used to determine if the roster is empty and also used to call print method from roster class
     * @param typeOfRoster type of roster (Full student roster, school specific roster).
     * @param school if the type of roster is a school list, the school of the list will be input, otherwise this parameter is null.
     */
    private void processPrint(Roster roster,int typeOfRoster,String school){
        if(roster.isEmpty()){
            if(typeOfRoster==FULL_ROSTER){
                System.out.println("Student roster is empty!");
                return;
            }
            if(typeOfRoster==SCHOOL_ROSTER){
                System.out.println("School list is empty!");
                return;
            }
        }
        if(typeOfRoster==FULL_ROSTER){
            System.out.println("* Student roster sorted by last name, first name, DOB **");
        }
        if(typeOfRoster==SCHOOL_ROSTER){
            System.out.println("* Students in "+ school+" *");
        }
        roster.print();
        if(typeOfRoster==FULL_ROSTER){
            System.out.println("* end of roster **");
        }
        if(typeOfRoster==SCHOOL_ROSTER){
            System.out.println("* end of list **");
        }

    }

    /**
     * Used to remove a student from the roster 
     * @param tokens input taken from the user at the command line
     * @param roster roster object that the student object will be removed from.
     */
    private void processRemove(String[] tokens,Roster roster){
        Student studentToRemove = new Student(new Profile(tokens[LASTNAME_INDEX],tokens[FIRSTNAME_INDEX],new Date(tokens[DATE_INDEX])),Major.CS,0);
        boolean removed = roster.remove(studentToRemove);
        if(removed){
            System.out.println(studentToRemove.getProfile()+" removed from the roster.");
            return;
        }
        System.out.println(studentToRemove.getProfile()+" is not in the roster.");
    }

    /**
     * Used to add a student to the roster, checks to see if student is a valid student.
     * @param tokens string array that is taken from the command line from the user .
     * @param roster roster object that the student object will be added too.
     */
    private void processAdd(String[] tokens,Roster roster){
        Date dob = new Date(tokens[DATE_INDEX]);
        Date today = new Date();
        if(!dob.isValid()){
            System.out.println("DOB invalid: "+dob+" not a valid calendar date!");
            return;
        }
        if(!dob.isValidAge(today)){
            System.out.println("DOB invalid: "+dob+" younger than 16 years old.");
            return;
        }
        Major major = grabMajor(tokens);
        if(major ==null){
            return;
        }
        try{
            if(Integer.parseInt(tokens[CREDITS_INDEX])<MIN_CREDITS){
                System.out.println("Credits completed invalid: cannot be negative!");
                return;
            }
        }catch(Exception e){
            System.out.println("Credits completed invalid: not an integer!");
            return;
        }
        Profile profile = new Profile(tokens[LASTNAME_INDEX],tokens[FIRSTNAME_INDEX],dob);
        Student student = new Student(profile,major,Integer.parseInt(tokens[CREDITS_INDEX]));
        if(roster.contains(student)){
            System.out.println(student.getProfile()+" is already in the roster.");
        }else{
            roster.add(student);
            System.out.println(student.getProfile()+" added to the roster.");
        }

    }

    /**
     * Checks to see if a given console input matches a valid major in enum class Major.
     * @param tokens string array which is taken from the command line from the user 
     * @return major returns the major, null if major is not valid.
     */
    private Major grabMajor(String[] tokens){
        Major major = null;
        switch(tokens[MAJOR_INDEX].toUpperCase()){
            case "EE":
                major = Major.EE;
                break;
            case "CS":
                major = Major.CS;
                break;
            case "BAIT":
                major = Major.BAIT;
                break;
            case "MATH":
                major = Major.MATH;
                break;
            case "ITI":
                major = Major.ITI;
                break;
            default:
                System.out.println("Major code invalid: "+tokens[MAJOR_INDEX]);
        }
        return major;
    }


}
