package Lab4;


public class RawDataset extends Dataset{
 
    public RawDataset(int D) {
        super(D); // adding the parent class constructor to create a dataset. 
        if (D <= 0) {
            throw new IllegalArgumentException("Dimensionality must be positive");
        }
    }

    // START OF NORMALIZATION, now all these methods that StandarizedDataset class wont inherit them because it no longer extends this class! Since in the standarized dataset the mean = 0 and variance = 1 it was unnecessary to inherit these methods. 

    public Vector calculateMinInput() {

        if (getData().isEmpty()) {
            return new Vector(getDim(), 0.0);
        }
    
    double[] minValues = new double[getDim()];
    for (int i = 0; i < getDim(); i++) {
        minValues[i] = Double.MAX_VALUE;
    }
    
    for (int i = 0; i < getData().size(); i++) {
        Record record = getData().get(i);
        double[] inputNumbers = record.getInput().getNumbers();
        for (int j = 0; j < getDim(); j++) {
            if (inputNumbers[j] < minValues[j]) {
                minValues[j] = inputNumbers[j];
            }
        }
    }
    
    return new Vector(minValues);
}

public Vector calculateMaxInput() {
    if (getData().isEmpty()) {
        return new Vector(getDim(), 0.0);
    }
    
    double[] maxValues = new double[getDim()];
    for (int i = 0; i < getDim(); i++) {
        maxValues[i] = Double.MIN_VALUE;
    }
    
    for (int i = 0; i < getData().size(); i++) {
        Record record = getData().get(i);
        double[] inputNumbers = record.getInput().getNumbers();
        for (int j = 0; j < getDim(); j++) {
            if (inputNumbers[j] > maxValues[j]) {
                maxValues[j] = inputNumbers[j];
            }
        }
    }
    
    return new Vector(maxValues);
}

public double calculateMinOutput() {
    if (getData().isEmpty()) {
        return 0.0;
    }
    
    double minOutput = Double.MAX_VALUE;
    for (int i = 0; i < getData().size(); i++) {
        Record record = getData().get(i);
        double output = record.getOutput();
        if (output < minOutput) {
            minOutput = output;
        }
    }
    return minOutput;
}

public double calculateMaxOutput() {
    if (getData().isEmpty()) {
        return 0.0;
    }
    
    double maxOutput = Double.MIN_VALUE;
    for (int i = 0; i < getData().size(); i++) {
        Record record = getData().get(i);
        double output = record.getOutput();
        if (output > maxOutput) {
            maxOutput = output;
        }
    }
    return maxOutput;
}

    public Vector meanInput() {
        if (getData().isEmpty()) {
            return new Vector(getDim(), 0.0);
        }

        Vector sum = new Vector(getDim(), 0.0);
        for (Record record : getData()) {
            Vector inputVector = record.getInput(); 
            sum = sum.add(inputVector);
        }
        return sum.DivideScalar(getData().size());
    }

    public Vector stdInput() {
        if (getData().isEmpty()) {
            return new Vector(getDim(), 0.0);
        }

        Vector mean = meanInput();
        Vector sumSquaredDifferences = new Vector(getDim(), 0.0);

        for (Record record : getData()) {
            Vector inputVector = record.getInput();
            Vector difference = inputVector.subtract(mean);
            Vector squaredDifference = difference.MultiplyNumbers(difference);
            sumSquaredDifferences = sumSquaredDifferences.add(squaredDifference);
        }
        
        return sumSquaredDifferences.DivideScalar(getData().size()).SquareRoot();
    }

    public double meanOutput() {
        if (getData().isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        for (Record record : getData()) {
            sum += record.getOutput();
        }
        return sum / getData().size();
    }

    public double stdOutput() {
        if (getData().isEmpty()) {
            return 0.0;
        }

        double mean = meanOutput();
        double sumSquaredDifferences = 0.0;

        for (Record record : getData()) {
            double difference = record.getOutput() - mean;
            sumSquaredDifferences += difference * difference;
        }

        double variance = sumSquaredDifferences / getData().size();
        return Math.sqrt(variance);
    }

    // END OF NORMALIZATION 


    public StandardizedDataset standardize() {
        Vector µin = this.meanInput();    
        Vector σin = this.stdInput();     
        double µout = this.meanOutput();  
        double σout = this.stdOutput();   

        StandardizedDataset standardizedDb = new StandardizedDataset(
            getDim(), µin, σin, µout, σout);

        for (Record record : getData()) {
            Record transformedRecord = standardizedDb.transform(record);
            standardizedDb.addRecord(transformedRecord);
        }

        return standardizedDb;
    }


    // Lab4: Overriding the two abstract methods in the abstract class it inherits form: 

    @Override
    public Record transform(Record r){ // just returning the argument itself without modifying it as stated in the instructions, since in the raw dataset we do not perform any change in the record. 
        return r; 
    } 

    @Override
    public double output(double d){ // just returning the argument itself without modifying it as stated in the instructions
        return d; 
    }
    @Override
    public Vector transform(Vector x){
        return x;  // because we declared the dataset with a RawDataset type we didn't modify the input vector so we return the same vector. 
    }


}