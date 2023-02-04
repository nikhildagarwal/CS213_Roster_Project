package group;

public class Student implements Comparable<Student>{
    private Profile profile;
    private Major major;
    private int creditCompleted;

    public static final int SOPHOMORE = 30;
    public static final int JUNIOR = 60;
    public static final int SENIOR = 90;

    public Student(Profile profile,Major major, int creditCompleted){
        this.profile = profile;
        this.major = major;
        this.creditCompleted = creditCompleted;
    }

    @Override
    public String toString(){
        return profile.getFirstName()+" "+profile.getLastName()+ " "+ profile.getDate().toString()+ " "+getCode_School(major)+" credits completed: "+creditCompleted+" "+getStanding(creditCompleted);
    }

    @Override
    public boolean equals(Object obj){
        Student student = (Student) obj;
        return student.profile.equals(profile);
    }

    @Override
    public int compareTo(Student student){
        return profile.compareTo(student.getProfile());
    }

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

    private String getCode_School(Major major){
        return "("+major.getMajorCode() +" "+ major +" "+ major.getSchool()+")";
    }

    public Profile getProfile(){
        return profile;
    }

    public Major getMajor(){
        return major;
    }

    public int getCreditCompleted(){
        return creditCompleted;
    }

    public void changeMajor(Major newMajor){
        major = newMajor;
    }
}
