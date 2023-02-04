package group;
import java.util.StringTokenizer;

public enum Major {
    CS("01:198","SAS"),
    MATH("01:640","SAS"),
    EE("14:332","SOE"),
    ITI("04:547","SC&I"),
    BAIT("33:136","RBS");

    private final String majorCode;
    private final String school;

    Major (String majorCode,String school){
        this.majorCode = majorCode;
        this.school = school;
    }

    public String getMajorCode(){
        return majorCode;
    }

    public String getSchool(){
        return school;
    }

    public boolean isValidSchool(String school){
        boolean valid = false;
        if(school.equals(CS.school)){
            valid = true;
        }
        if(school.equals(EE.school)){
            valid = true;
        }
        if(school.equals(ITI.school)){
            valid = true;
        }
        if(school.equals(BAIT.school)){
            valid = true;
        }
        return valid;
    }

    public int compareWith(Major major){
        int compareSchool = school.compareTo(major.school);
        if(compareSchool==0){
            int departmentCode1 = getDepartmentCode(majorCode);
            int departmentCode2 = getDepartmentCode(major.majorCode);
            return Integer.compare(departmentCode1,departmentCode2);
        }else{
            return compareSchool;
        }
    }

    public int getDepartmentCode(String majorCode){
        StringTokenizer codeTokens = new StringTokenizer(majorCode,":");
        codeTokens.nextToken();
        return Integer.parseInt(codeTokens.nextToken());
    }

}
