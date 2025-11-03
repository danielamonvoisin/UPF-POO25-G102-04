package Lab2;

import java.util.ArrayList;
import java.util.List;

public class Dataset {
    private ArrayList<Record> data;
    private final int dim;

    public Dataset(int D) {
        if (D <= 0) {
            throw new IllegalArgumentException("Dimensionality must be positive");
        }
        this.dim = D;
        this.data = new ArrayList<>();
    }

    public int getDim() {
        return this.dim;
    }

    public List<Record> getData() {
        return new ArrayList<>(data);
    }

    public void addRecord(Record record) {
        if (record.getInput().getDimension() != dim) {
            throw new IllegalArgumentException("Record input dimension must match dataset dimension");
        }
        this.data.add(record);
    }

    public Vector meanInput() {
        if (data.isEmpty()) {
            return new Vector(dim, 0.0);
        }

        Vector sum = new Vector(dim, 0.0);
        for (Record record : data) {
            Vector inputVector = record.getInput(); 
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
            Vector inputVector = record.getInput();
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
            sum += record.getOutput();
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
            double difference = record.getOutput() - mean;
            sumSquaredDifferences += difference * difference;
        }

        double variance = sumSquaredDifferences / data.size();
        return Math.sqrt(variance);
    }

    public StandardizedDataset standardize() {
        Vector µin = this.meanInput();    
        Vector σin = this.stdInput();     
        double µout = this.meanOutput();  
        double σout = this.stdOutput();   

        StandardizedDataset standardizedDb = new StandardizedDataset(
            this.dim, µin, σin, µout, σout);

        for (Record record : this.data) {
            Record transformedRecord = standardizedDb.transform(record);
            standardizedDb.addRecord(transformedRecord);
        }

        return standardizedDb;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Dataset with ").append(data.size()).append(" records, dimension: ").append(dim).append("\n");
        for (Record record : data) {
            sb.append("  ").append(record.convertString()).append("\n");
        }
        return sb.toString();
    }
}