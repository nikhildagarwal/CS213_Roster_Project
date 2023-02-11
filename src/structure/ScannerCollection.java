package structure;

public class ScannerCollection {
    private String[][] scannerCollection;
    private int size;

    public static final int INITIAL_SIZE = 4;
    public static final int INITIAL_NUMBER_OF_ELEMENTS = 0;

    public ScannerCollection(){
        this.scannerCollection = new String[INITIAL_SIZE][];
        this.size = INITIAL_NUMBER_OF_ELEMENTS;
    }

    public boolean add(String[] tokens){
        if(size==scannerCollection.length){
            grow();
        }
        scannerCollection[size] = tokens;
        size++;
        return true;
    }

    public void grow(){
        int prevLength = scannerCollection.length;
        String[][] newScannerCollection = new String[prevLength+INITIAL_SIZE][];
        for(int i = 0;i<prevLength;i++){
            newScannerCollection[i] = scannerCollection[i];
        }
        scannerCollection = newScannerCollection;
    }

    public int size(){
        return size;
    }

    public String[] get(int index){
        if(index<size){
            return scannerCollection[index];
        }
        return null;
    }


}
