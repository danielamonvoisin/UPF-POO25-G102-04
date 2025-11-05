package Lab2;

public class NormalizedDataset extends Dataset{
    private double maxOutput; 
    private double minOutput;
    private Vector maxInput; // a Vector containing the maximum values for each dimension
    private Vector minInput; // a Vector containing the minimum values for each dimension

    public NormalizedDataset(int dimension, Vector minInput, Vector maxInput, double minOutput, double maxOutput) {
        super(dimension);
        this.minInput = minInput;
        this.maxInput = maxInput;
        this.minOutput = minOutput;
        this.maxOutput = maxOutput;
    }

// we will build a function that individually transforms records ! To being normalized. 
    public Record transform(Record record){
        Vector vectorInput = record.getInput(); // to store the input vector from the record
        double[] InitialInput = vectorInput.getNumbers(); // store as an array the numbers of each dimension in the vectorInput, to easily manage them. 

        double[] normalizedValues = new double[vectorInput.getDimension()]; // finaly to convert the normal array of Initial inputs into the normalized array. 

        double[] minNumbers = minInput.getNumbers(); // to get the minimum value of each dimension for the following loop. 
        double[] maxNumbers = maxInput.getNumbers(); // to get the maximum value of each dimension for the following loop.


        for(int i = 0; i < vectorInput.getDimension(); i++){ // loop through all elements of the vector input

            if(minNumbers[i] == maxNumbers[i]){ // we would first need to handle division by zero, since maxNumbers[i] - minNumbers[i] = 0. 
                normalizedValues[i] = 0.5; // we set 0.5 since all dimension i values are all the same, so they like the the "average". 
            }
            else if(InitialInput[i] == minNumbers[i]){
                normalizedValues[i] = 0; 
            }
            else if(InitialInput[i] == maxNumbers[i]){
                normalizedValues[i] = 1; 
            }
            else{
                normalizedValues[i] = (InitialInput[i] - minNumbers[i])/(maxNumbers[i] - minNumbers[i]); 
            }
        }

        Vector normalizedVector = new Vector(normalizedValues); 

        // now we operate to find the normalized output
        double NormalizedOutput; // first we define the variable in which we want to store the value of the output normalized. 

        double InitialOutput = record.getOutput();
        if(InitialOutput == this.maxOutput){
            NormalizedOutput = 1; 
        }
        else if(InitialOutput == this.minOutput){
            NormalizedOutput = 0; 
        }
        else{
            NormalizedOutput = (InitialOutput - this.minOutput)/(this.maxOutput - this.minOutput); // apply the normalization formula with the given max and min. 
        }

        return new Record(normalizedVector,NormalizedOutput); 
    }

    public void addNormalizedData(Dataset dataset){  

        for(int i = 0; i < dataset.getData().size(); i++){
            Record record = dataset.getData().get(i);  // get each Record[i] of the ArrayList of records that the dataset d contains
            Record normalizedRecord = transform(record);  // apply the method done above for normalizing each record
            addRecord(normalizedRecord); 
        }
    }   

    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Normalized Dataset with ").append(getData().size())
          .append(" records, dimension: ").append(getDim()).append("\n");
        for (Record record : getData()) {
            sb.append("  ").append(record.convertString()).append("\n");
        }
        sb.append("Min Input: ").append(minInput.convertString()).append("\n");
        sb.append("Max Input: ").append(maxInput.convertString()).append("\n");
        sb.append("Min Output: ").append(minOutput).append("\n");
        sb.append("Max Output: ").append(maxOutput).append("\n");
        return sb.toString();
    }
}

