package Lab4;

import java.util.ArrayList;
// import java.util.List;

public abstract class Dataset {

    private int dim; 
    private ArrayList<Record> data; 

    public Dataset(int d){
        if (d <= 0) {
            throw new IllegalArgumentException("Dimensionality must be positive");
        }
        this.dim = d;
        this.data = new ArrayList<>();  
    }

    public int getDim(){
        return this.dim; 
    }

    public ArrayList<Record> getData(){
        return this.data; 
    }

    public void addRecord(Record record) {
        if (record.getInput().getDimension() != dim) {
            throw new IllegalArgumentException("Record input dimension must match dataset dimension");
        }
        this.data.add(record);
    }

    public abstract Record transform(Record r);
    public abstract Vector transform(Vector x);  // abstract method too for input vectors ! 
    public abstract double output(double d); 

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dataset with ").append(data.size()).append(" records, dimension: ").append(dim).append("\n");
        for (Record record : data) {
            sb.append("  ").append(record.convertString()).append("\n");
        }
        return sb.toString();
    }
}
