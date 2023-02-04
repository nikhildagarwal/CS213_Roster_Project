package group;
//import java.util.StringTokenizer;

public class Roster {
    private Student[] roster;
    private int size;

    public static final int NOT_FOUND = -1;
    public static final int INITIAL_SIZE = 4;
    public static final int INITIAL_NUMBER_OF_ELEMENTS = 0;

    public Roster(){
        this.roster = new Student[INITIAL_SIZE];
        this.size = INITIAL_NUMBER_OF_ELEMENTS;
    }

    private int find(Student student) {
        for(int i = 0;i<size;i++){
            if(roster[i].equals(student)){
                return i;
            }
        }
        return NOT_FOUND;
    }

    public void grow(){
        int prevLength = roster.length;
        Student[] newRoster = new Student[prevLength+INITIAL_SIZE];
        for(int i = 0;i<prevLength;i++){
            newRoster[i] = roster[i];
        }
        roster = newRoster;
    }

    public boolean add(Student student){
        if(size==roster.length){
            grow();
        }
        roster[size] = student;
        size++;
        return true;
    }

    public boolean change(Student student, Major newMajor){
        int index = find(student);
        if(index==NOT_FOUND){
            return false;
        }
        roster[index].changeMajor(newMajor);
        return true;
    }

    public boolean remove(Student student){
        int index = find(student);
        if(index==NOT_FOUND){
            return false;
        }
        for(int i = index+1;i<size;i++) {
            roster[index] = roster[i];
            index++;
        }
        size--;
        roster[size] = null;
        return true;
    }

    public boolean contains(Student student){
        switch(find(student)){
            case -1:
                return false;
            default:
                return true;
        }
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void print(){
        rosterProfileSort(roster);
        for(int i = 0;i<size;i++){
            System.out.println(roster[i]);
        }
    }

    public void printByMajor(){
        rosterMajorSort(roster);
        for(int i = 0;i<size;i++){
            System.out.println(roster[i]);
        }
    }

    public void printByStanding(){
        rosterStandingSort(roster);
        for(int i = 0;i<size;i++){
            System.out.println(roster[i]);
        }
    }

    public boolean filterBySchool(String school,Roster newRoster){
        Major major = Major.CS;
        boolean validSchool = major.isValidSchool(school);
        if(!validSchool){
            return false;
        }
        for(int i = 0;i<size;i++){
            if(roster[i].getMajor().getSchool().equals(school)){
                newRoster.add(roster[i]);
            }
        }
        return true;
    }

    private void rosterMajorSort(Student[] roster){
        int[] compareables = new int[size-1];
        for(int i =0;i<size-1;i++){
            compareables[i] = roster[i].getMajor().compareWith(roster[i+1].getMajor());
        }
        while(containsPositive(compareables)) {
            for(int i = 0;i<compareables.length;i++){
                if(compareables[i]>0){
                    swap(roster,i);
                }
            }
            for(int i = 0;i<compareables.length;i++){
                compareables[i] = roster[i].getMajor().compareWith(roster[i+1].getMajor());
            }
        }
    }

    private void rosterStandingSort(Student[] roster){
        int[] compareables = new int[size-1];
        for(int i =0;i<size-1;i++){
            String major1 = roster[i].getStanding(roster[i].getCreditCompleted());
            String major2 = roster[i+1].getStanding(roster[i+1].getCreditCompleted());
            compareables[i] = major1.compareTo(major2);
        }

        while(containsPositive(compareables)) {
            for(int i = 0;i<compareables.length;i++){
                if(compareables[i]>0){
                    swap(roster,i);
                }
            }
            for(int i = 0;i<compareables.length;i++){
                String major1 = roster[i].getStanding(roster[i].getCreditCompleted());
                String major2 = roster[i+1].getStanding(roster[i+1].getCreditCompleted());
                compareables[i] = major1.compareTo(major2);
            }
        }
    }

    private void rosterProfileSort(Student[] roster){
        int[] compareables = new int[size-1];
        for(int i =0;i<size-1;i++){
            compareables[i] = roster[i].compareTo(roster[i+1]);
        }

        while(containsPositive(compareables)) {
            for(int i = 0;i<compareables.length;i++){
                if(compareables[i]>0){
                    swap(roster,i);
                }
            }
            for(int i = 0;i<compareables.length;i++){
                compareables[i] = roster[i].compareTo(roster[i+1]);
            }
        }
    }

    private boolean containsPositive(int[] compareables){
        for(int curr:compareables){
            if(curr>0){
                return true;
            }
        }
        return false;
    }

    private void swap(Student[] roster,int index){
        Student temp = roster[index];
        roster[index] = roster[index+1];
        roster[index+1] = temp;
    }

}
