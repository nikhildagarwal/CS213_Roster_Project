package group;

public class Profile implements Comparable<Profile>{
    private String lname;
    private String fname;
    private Date dob;

    public Profile(String lname, String fname, Date dob){
        this.lname = lname;
        this.fname = fname;
        this.dob = dob;
    }

    public String getLastName(){
        return lname;
    }

    public String getFirstName(){
        return fname;
    }

    public Date getDate(){
        return dob;
    }

    @Override
    public String toString(){
        return fname+" "+lname+" "+dob.toString();
    }

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
