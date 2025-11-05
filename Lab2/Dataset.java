package Lab2;

import java.util.ArrayList;
import java.util.Arrays;
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

    //NOW, WE ARE GOING TO IMPLEMENT NORMALIZED DATASET

    public Vector minInput() {
        if (data.isEmpty()) {
            return new Vector(dim, 0.0);
        }
        
        double[] minValues = new double[dim];
        Arrays.fill(minValues, Double.MAX_VALUE);
        
        for (Record record : data) {
            Vector input = record.getInput();
            double[] inputValues = input.getNumbers();
            for (int i = 0; i < dim; i++) {
                if (inputValues[i] < minValues[i]) {
                    minValues[i] = inputValues[i];
                }
            }
        }
        
        return new Vector(minValues);
    }

    public Vector maxInput() {
        if (data.isEmpty()) {
            return new Vector(dim, 0.0);
        }
        
        double[] maxValues = new double[dim];
        Arrays.fill(maxValues, Double.MIN_VALUE);
        
        for (Record record : data) {
            Vector input = record.getInput();
            double[] inputValues = input.getNumbers();
            for (int i = 0; i < dim; i++) {
                if (inputValues[i] > maxValues[i]) {
                    maxValues[i] = inputValues[i];
                }
            }
        }
        
        return new Vector(maxValues);
    }

    public double minOutput() {
        if (data.isEmpty()) {
            return 0.0;
        }
        
        double min = Double.MAX_VALUE;
        for (Record record : data) {
            if (record.getOutput() < min) {
                min = record.getOutput();
            }
        }
        return min;
    }

    public double maxOutput() {
        if (data.isEmpty()) {
            return 0.0;
        }
        
        double max = Double.MIN_VALUE;
        for (Record record : data) {
            if (record.getOutput() > max) {
                max = record.getOutput();
            }
        }
        return max;
    }

    public NormalizedDataset normalize() {
        Vector minIn = this.minInput();
        Vector maxIn = this.maxInput();
        double minOut = this.minOutput();
        double maxOut = this.maxOutput();
        
        NormalizedDataset normalizedDb = new NormalizedDataset(
            this.dim, minIn, maxIn, minOut, maxOut);
        
        for (Record record : this.data) {
            Record transformedRecord = normalizedDb.transform(record);
            normalizedDb.addRecord(transformedRecord);
        }
        
        return normalizedDb;
    }

    //END OF NORMALIZED DATASET


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