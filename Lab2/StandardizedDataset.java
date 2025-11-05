package Lab2;

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

    public Record transform(Record r) {
        Vector x = r.getInput();
        double y = r.getOutput();

        Vector standardizedX = x.subtract(meanInput).DivisionNumbers(stdInput);
        double standardizedY = (y - meanOutput) / stdOutput;

        return new Record(standardizedX, standardizedY);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Record record : getData()) {
            sb.append("  ").append(record.convertString()).append("\n");
        }
        sb.append("Mean Input: ").append(meanInput.convertString()).append("\n");
        sb.append("Std Input: ").append(stdInput.convertString()).append("\n");
        sb.append("Mean Output: ").append(meanOutput).append("\n");
        sb.append("Std Output: ").append(stdOutput).append("\n");
        return sb.toString();
    }
}