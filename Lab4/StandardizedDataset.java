package Lab4;

public class StandardizedDataset extends Dataset {
    private Vector meanInput;   
    private Vector stdInput; 
    private double meanOutput;  
    private double stdOutput;   

    public StandardizedDataset(int dim, Vector meanInput, Vector stdInput, double meanOutput, double stdOutput) {
        super(dim);
        this.meanInput = meanInput;
        this.stdInput = stdInput;
        this.meanOutput = meanOutput;
        this.stdOutput = stdOutput;
    }

    // Lab4: Overriding the two abstract methods in the abstract class it inherits form: 

    @Override
    public Record transform(Record r) {
        Vector x = r.getInput();
        double y = r.getOutput();

        Vector standardizedX = x.subtract(meanInput).DivisionNumbers(stdInput); // standarizing the vector input. 
        double standardizedY = (y - meanOutput) / stdOutput; // standarize the vector associated output. 

        return new Record(standardizedX, standardizedY);
    }

    @Override
    public double output(double d){ // d is indeed the predicted output once we applied the transform method, so lets undone it. 
        return d * stdOutput + meanOutput; 
    }


    @Override
    public Vector transform(Vector x){
        return x.subtract(meanInput).DivisionNumbers(stdInput);  // here we transform only the input vector x, with no output !
    }
}