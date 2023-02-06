package group;

/**
 * Roster Data Structure that holds Student Objects.
 * Ability to add and remove student from our DS.
 * Check if our DS is empty.
 * Expand the size of our DS as we add more elements
 * As well as sorting methods and printing methods for our Roster DS.
 * @author Nikhil Agarwal, Hyeon Oh
 */
public class Roster {
    private Student[] roster;
    private int size;

    public static final int NOT_FOUND = -1;
    public static final int INITIAL_SIZE = 4;
    public static final int INITIAL_NUMBER_OF_ELEMENTS = 0;


    /**
     * Initialize Roster Object (Constructor)
     */
    public Roster(){
        this.roster = new Student[INITIAL_SIZE];
        this.size = INITIAL_NUMBER_OF_ELEMENTS;
    }

    /**
     * Checks Roster to see if there exists a particular student in our Roster.
     * @param student student that we want to find.
     * @return index of the student if found, or -1 if not found.
     */
    private int find(Student student) {
        for(int i = 0;i<size;i++){
            if(roster[i].equals(student)){
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * Expands the size of our Roster DS once we have filled our array to max capacity.
     */
    public void grow(){
        int prevLength = roster.length;
        Student[] newRoster = new Student[prevLength+INITIAL_SIZE];
        for(int i = 0;i<prevLength;i++){
            newRoster[i] = roster[i];
        }
        roster = newRoster;
    }

    /**
     * Add given student to Roster.
     * @param student student to be added.
     * @return true if student was successfully added.
     */
    public boolean add(Student student){
        if(size==roster.length){
            grow();
        }
        roster[size] = student;
        size++;
        return true;
    }

    /**
     * Check to see if a particular student is in the roster.
     * If so, change the major of that student.
     * @param student student that we want to change the major of.
     * @param newMajor the major we need to change to.
     * @return true if major changed successfully, false if student not found.
     */
    public boolean change(Student student, Major newMajor){
        int index = find(student);
        if(index==NOT_FOUND){
            return false;
        }
        roster[index].changeMajor(newMajor);
        return true;
    }

    /**
     * Remove the given student from the list.
     * Does nothing if the student is not in the list.
     * @param student the student to be removed.
     * @return true if the student was successfully removed, false otherwise.
     */
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

    /**
     * Check our roster to see if it contains a particular student.
     * Public method that all classes can access as opposed to the "find" method.
     * @param student the student we want to see in roster.
     * @return true if roster contains the student, false otherwise.
     */
    public boolean contains(Student student){
        switch(find(student)){
            case -1:
                return false;
            default:
                return true;
        }
    }

    /**
     * Check if roster is empty.
     * @return true if the roster is empty, false otherwise.
     */
    public boolean isEmpty(){
        return size==0;
    }

    /**
     * Prints the sorted roster (sorted by last name, first name, DOB).
     */
    public void print(){
        rosterProfileSort(roster);
        for(int i = 0;i<size;i++){
            System.out.println(roster[i]);
        }
    }

    /**
     * Sorts roster by last name, first name, DOB.
     * Makes use of student.compareTo method.
     * @param roster array of student objects to be sorted.
     */
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

    /**
     * Prints the sorted roster (sorted by major, school).
     */
    public void printByMajor(){
        rosterMajorSort(roster);
        for(int i = 0;i<size;i++){
            System.out.println(roster[i]);
        }
    }

    /**
     * Sorts roster by Major, school.
     * The comparable in this method, is the majors of the student objects.
     * @param roster array of student objects to be sorted.
     */
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

    /**
     * Prints the sorted roster (sorted by standing).
     */
    public void printByStanding(){
        rosterStandingSort(roster);
        for(int i = 0;i<size;i++){
            System.out.println(roster[i]);
        }
    }

    /**
     * Sorts roster by standing.
     * The comparable in this method, is the (String) grade of the student Ex:"Freshman".
     * @param roster array of student objects to be sorted.
     */
    
    
    private void rosterStandingSort(Student[] roster){
        int[] compareables = new int[size-1];
        for(int i =0;i<size-1;i++){
            String grade1 = roster[i].getStanding(roster[i].getCreditCompleted());
            String grade2 = roster[i+1].getStanding(roster[i+1].getCreditCompleted());
            compareables[i] = grade1.compareTo(grade2);
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
    
    /**
     * * Filters our roster by school.
     * Checks to see if the school is valid school at our university.
     * Then goes through our main roster and adds students to a new roster,
     * If their majors match the input school.
     * @param school school to filter roster by.
     * @param newRoster empty roster we will add students too if they are part of a particular school.
     * @return true if school is valid, false otherwise.
     */
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

    /**
     * Checks to see if array has any positive values.
     * If there is a positive value, it means that there exists two elements,
     * such that element i is greater than element i+1 in our roster.
     * @param compareables array of values of comparisons between elements.
     * @return true if contains positive number, false otherwise.
     */
    private boolean containsPositive(int[] compareables){
        for(int curr:compareables){
            if(curr>0){
                return true;
            }
        }
        return false;
    }

    /**
     * Swap two students in an array of students.
     * @param roster array of students.
     * @param index index of the student that we want swap with the student one index to the right.
     */
    private void swap(Student[] roster,int index){
        Student temp = roster[index];
        roster[index] = roster[index+1];
        roster[index+1] = temp;
    }

}
