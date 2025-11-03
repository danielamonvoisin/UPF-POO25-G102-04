package Lab2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dataset{
    private ArrayList <Record> data; //aggregation relationship with the Record class
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

    public Vector meanInput() {
    if (data.isEmpty()) {
        return new Vector(dim, 0.0);
    }

    Vector sum = new Vector(dim, 0.0);
    for (Record record : data) {
        Vector inputVector = new Vector(dim, record.getX());
        sum = sum.add(inputVector);
    }
    return sum.DivideScalar(data.size());
    }

    public Vector stdInput() {
        if (data.isEmpty()) {
            return new Vector(dim, 0.0);
        }

        Vector mean = meanInput();
        Vector sumSquaredDifferences = new Vector(dim, 0.0);

        for (Record record : data) {
            Vector inputVector = new Vector(dim, record.getX());
            Vector difference = inputVector.subtract(mean);
            Vector squaredDifference = difference.MultiplyNumbers(difference);
            sumSquaredDifferences = sumSquaredDifferences.add(squaredDifference);
        }
        
        return sumSquaredDifferences.DivideScalar(data.size()).SquareRoot();
    }

    public double meanOutput() {
        if (data.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        for (Record record : data) {
            sum += record.getY();
        }
        return sum / data.size();
    }

    public double stdOutput() {
        if (data.isEmpty()) {
            return 0.0;
        }

        double mean = meanOutput();
        double sumSquaredDifferences = 0.0;

        for (Record record : data) {
            double difference = record.getY() - mean;
            sumSquaredDifferences += difference * difference;
        }

        double variance = sumSquaredDifferences / data.size();
        return Math.sqrt(variance);
    }

    //WE ARE MISSING STANDARIZE()

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Record record : data) {
            sb.append("  ").append(record.convertString()).append("\n");
        }
        return sb.toString();
    }
}
