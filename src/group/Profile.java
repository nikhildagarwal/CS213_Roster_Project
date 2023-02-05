package group;

/**
 * Class to implement Profile Object.
 * Holds LastName, FirstName, and DoB as parameters.
 * Contains methods to fetch profile values from outside this class,
 * as well as methods that override equals, toString, and compareTo.
 * @author Nikhil Agarwal
 */
public class Profile implements Comparable<Profile>{
    private String lname;
    private String fname;
    private Date dob;

    /**
     * Constructor for Profile Object.
     * @param lname Last Name of profile.
     * @param fname First Name of profile.
     * @param dob DoB of profile.
     */
    public Profile(String lname, String fname, Date dob){
        this.lname = lname;
        this.fname = fname;
        this.dob = dob;
    }

    /**
     * @return Gets last name of Profile Object.
     */
    public String getLastName(){
        return lname;
    }

    /**
     * @return Gets first name of Profile Object.
     */
    public String getFirstName(){
        return fname;
    }

    /**
     * @return Gets date of Profile Object.
     */
    public Date getDate(){
        return dob;
    }

    /**
     * Overrides toString method from Java Object class.
     * @return Profile as string printed as: firstName lastName doB
     */
    @Override
    public String toString(){
        return fname+" "+lname+" "+dob.toString();
    }

    /**
     * Overrides equals method from Java Object class.
     * @param obj object to be checked.
     * @return true if object equals Profile, false if otherwise.
     */
    @Override
    public boolean equals(Object obj){
        Profile profile = (Profile) obj;
        if(profile.fname.equalsIgnoreCase(fname)){
            if(profile.lname.equalsIgnoreCase(lname)){
                if(dob.equals(profile.dob)){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Overrides compareTo method from Java Comparable class.
     * @param profile the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Profile profile){
        int compareLastName = lname.compareTo(profile.lname);
        if(compareLastName==0){
            int compareFirstName = fname.compareTo(profile.fname);
            if(compareFirstName==0){
                return dob.compareTo(profile.dob);
            }else{
                return compareFirstName;
            }
        }else{
            return compareLastName;
        }
    }

}
