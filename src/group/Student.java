package group;

/**
 * Implements our Student object as a comparable object
 * Contains the student's profile, major, and credits completed
 * Contains overrides for equals, toString, and compareTo 
 * Implements methods to calculate a student's standing
 * @author Nikhil Agarwal, Hyeon Oh
*/
public class Student implements Comparable<Student>{
    private Profile profile;
    private Major major;
    private int creditCompleted;

    public static final int SOPHOMORE = 30;
    public static final int JUNIOR = 60;
    public static final int SENIOR = 90;

    /**
     * Default constructor for the Student object
     * @param profile corresponding profile for the student
     * @param major corresponding major for the student 
     * @param creditCompleted corresponding credits completed for the student 
     */
    public Student(Profile profile,Major major, int creditCompleted){
        this.profile = profile;
        this.major = major;
        this.creditCompleted = creditCompleted;
    }

    /**
     * Override of toString method in object class in Java
     * Prints the student's first name, last name, date of birth, major, and credits completed 
     * @return returns student information as stated above in a string 
     */
    @Override
    public String toString(){
        return profile.getFirstName()+" "+profile.getLastName()+ " "+ profile.getDate().toString()+ " "+getCode_School(major)+" credits completed: "+creditCompleted+" "+getStanding(creditCompleted);
    }

    /**
     * Override of equals method in object class in Java
     * Compares student attributes
     * @param obj object to be checked with
     * @return true if the students are equal, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        Student student = (Student) obj;
        return student.profile.equals(profile);
    }

    /**
     * Override of compareTo method in object class in Java 
     * @param student the object that is being compared 
     * @return -1 if object lexicographically precedes argument, 0 if equal, else 1
     */
    @Override
    public int compareTo(Student student){
        return profile.compareTo(student.getProfile());
    }

    /**
     * Assigns a student's standing depending on how many credits they have completed
     * @param creditCompleted
     * @return (Freshman), (Sophomore), (Junior), (Senior)
     */
    public String getStanding(int creditCompleted){
        if(creditCompleted<SOPHOMORE){
            return "(Freshman)";
        }else if(creditCompleted>=SOPHOMORE && creditCompleted<JUNIOR){
            return "(Sophomore)";
        }else if(creditCompleted>=JUNIOR && creditCompleted<SENIOR){
            return "(Junior)";
        }else{
            return "(Senior)";
        }
    }

    /**
     * 
     * @param major the major of the student
     * @return returns major code and school
     */
    private String getCode_School(Major major){
        return "("+major.getMajorCode() +" "+ major +" "+ major.getSchool()+")";
    }

    /**
     * returns profile of the student 
     * @return profile
     */
    public Profile getProfile(){
        return profile;
    }

    /**
     * returns major of the student
     * @return major
     */
    public Major getMajor(){
        return major;
    }

    /**
     * returns credits completed of the student
     * @return creditCompleted
     */
    public int getCreditCompleted(){
        return creditCompleted;
    }

    /**
     * changes the student's major
     * @param newMajor
     */
    public void changeMajor(Major newMajor){
        major = newMajor;
    }
}
