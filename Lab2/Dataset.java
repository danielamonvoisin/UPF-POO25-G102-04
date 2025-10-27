package Lab2;

import java.util.ArrayList;
import java.util.List;

public class Dataset{
    private ArrayList <Record> data;
    private final int dim;

    public Dataset(int D){
        if (D <= 0) {
            throw new IllegalArgumentException("Dimensionality must be positive");
        }
        this.dim = D;
        this.data = new ArrayList<>();
    }

    public int getDim(){
        return this.dim; 
    }

    public List<Record> getData(){
        return new ArrayList<>(data);
    }

    public void addRecord(Record record){
        this.data.add(record);
    }
}
