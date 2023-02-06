package group;

/**
 * Implements our profile object as a comparable object
 * Contains last name, first name, and date of birth
 * Overrides for equals, toString, and compareTo
 * Implements simple getter methods
 * @author Nikhil Agarwal, Hyeon Oh
*/
public class Profile implements Comparable<Profile>{
    private String lname;
    private String fname;
    private Date dob;

    /**
     * Default constructor for Profile object
     * @param lname corresponding last name for the profile
     * @param fname corresponding first name for the profile
     * @param dob corresponding date of birth for the profile
     */
    public Profile(String lname, String fname, Date dob){
        this.lname = lname;
        this.fname = fname;
        this.dob = dob;
    }

    /**
     * return last name of the Profile object
     * @return lname
     */
    public String getLastName(){
        return lname;
    }

    /**
     * return first name of the Profile object
     * @return fname
     */
    public String getFirstName(){
        return fname;
    }

    /**
     * return date of birth
     * @return dob
     */
    public Date getDate(){
        return dob;
    }

    /**
     * Override of toString method in object class in Java
     * Prints the student's first name, last name, and date of birth
     * @return student's profile in a string
     */
    @Override
    public String toString(){
        return fname+" "+lname+" "+dob.toString();
    }

    /**
     * Override of equals method in object class in Java
     * Compares profile attributes first name, last name, and date of birth 
     * @param obj object to be checked with
     * @return true if the Profile objects are equal, else return false 
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
     * Override of compareTo method in object class in Java
     * @param profile the object that is being compared
     * @return -1 if string lexicographically precedes argument string, 0 if equal, 1 if string lexicographically follows the argument string
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
