package data;

import enumerated.Major;

/**
 * Class to implement the Student object.
 * Holds a profile object, Major enum, and an integer.
 * Contains methods to override inherited methods from the Java objects and comparable classes.
 * Calculate the standing of a Student
 * @author Nikhil Agarwal
 */
public class Student implements Comparable<Student>{
    private Profile profile;
    private Major major;
    private int creditCompleted;

    private static final int SOPHOMORE = 30;
    private static final int JUNIOR = 60;
    private static final int SENIOR = 90;

    /**
     * Constructor to initialize the Student Object.
     * @param profile Profile Object of Student.
     * @param major Major of the student.
     * @param creditCompleted the number of credits that a student has completed.
     */
    public Student(Profile profile,Major major, int creditCompleted){
        this.profile = profile;
        this.major = major;
        this.creditCompleted = creditCompleted;
    }

    /**
     * Override toString method from Java Objects class.
     * @return Student as a string in format: firstName lastName DoB (departmentCode Major Scool) creditsCompleted
     */
    @Override
    public String toString(){
        return profile.getFirstName()+" "+profile.getLastName()+ " "+ profile.getDate().toString()+ " "+getCode_School(major)+" credits completed: "+creditCompleted+" "+getStanding(creditCompleted);
    }

    /**
     * Override equals method from Java Objects class.
     * @param obj object to be checked.
     * @return true if object equals Student, false if otherwise.
     */
    @Override
    public boolean equals(Object obj){
        Student student = (Student) obj;
        return student.profile.equals(profile);
    }

    /**
     * Override compareTo method from Java Comparable class.
     * Syntax: student1.compareTo(student2)
     * @param student the object to be compared.
     * @return positive if profile of student1 is lexographically greater than student2, 0 if profiles are equal, negative otherwise.
     */
    @Override
    public int compareTo(Student student){
        return profile.compareTo(student.getProfile());
    }

    /**
     * Checks how many credits a student has completed and returns the standing of the student.
     * @param creditCompleted number of credits student has completed.
     * @return Freshman if creditsCompleted<30,
     *         Sophomore if 30<=creditsCompleted<60,
     *         Junior if 60<=creditsCompleted<90,
     *         Senior if creditsCompleted>=90
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
     * Changes Major of student to a particular newMajor.
     * @param newMajor the major we want to set Major to.
     */
    public void changeMajor(Major newMajor){
        major = newMajor;
    }

    /**
     * @return Profile of our student (last name, first name, DoB)
     */
    public Profile getProfile(){
        return profile;
    }

    /**
     * @return Major of the student.
     */
    public Major getMajor(){
        return major;
    }

    /**
     * @return return number of Credits that the student has completed.
     */
    public int getCreditCompleted(){
        return creditCompleted;
    }

    public boolean isValid(){
        return profile.getDate().isValid() && profile.getDate().isValidAge() && creditCompleted>=0 && major!=null;
    }

    /**
     * Helper method to format output of the Major portion of our student object.
     * @param major major to be formatted to string.
     * @return String of formatted major. Ex: CS -> (01:198 CS SAS)
     */
    private String getCode_School(Major major){
        return "("+major.getMajorCode() +" "+ major +" "+ major.getSchool()+")";
    }


}
