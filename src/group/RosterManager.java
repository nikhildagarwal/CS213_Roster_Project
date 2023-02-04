package group;
import java.util.Scanner;

/*@author Nikhil Agarwal*/
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

    private String[] processLine(String command){
        String[] line = command.split("\\s");
        int counter = 0;
        String[] newLine = new String[line.length];
        for(String token:line){
            if(!token.equals("")){
                newLine[counter] = token;
                //System.out.println(newLine[counter]);
                counter++;
            }
        }
        return newLine;
    }

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

    private void processPrintMajor(Roster roster){
        if(roster.isEmpty()){
            System.out.println("Student roster is empty!");
            return;
        }
        System.out.println("* Student roster sorted by school, major **");
        roster.printByMajor();
        System.out.println("* end of roster **");
    }

    private void processPrintStanding(Roster roster){
        if(roster.isEmpty()){
            System.out.println("Student roster is empty!");
            return;
        }
        System.out.println("* Student roster sorted by standing **");
        roster.printByStanding();
        System.out.println("* end of roster **");
    }

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

    private void processRemove(String[] tokens,Roster roster){
        Student studentToRemove = new Student(new Profile(tokens[LASTNAME_INDEX],tokens[FIRSTNAME_INDEX],new Date(tokens[DATE_INDEX])),Major.CS,0);
        boolean removed = roster.remove(studentToRemove);
        if(removed){
            System.out.println(studentToRemove.getProfile()+" removed from the roster.");
            return;
        }
        System.out.println(studentToRemove.getProfile()+" is not in the roster.");
    }

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
        //System.out.println(major);
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
