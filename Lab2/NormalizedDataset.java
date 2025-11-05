package Lab2;

public class NormalizedDataset extends Dataset {
    private Vector minInput;
    private Vector maxInput;
    private double minOutput;
    private double maxOutput;
    
    public NormalizedDataset(int dim, Vector minInput, Vector maxInput, double minOutput, double maxOutput) {
        super(dim);
        this.minInput = minInput;
        this.maxInput = maxInput;
        this.minOutput = minOutput;
        this.maxOutput = maxOutput;
    }
    
    public Record transform(Record r) {
        Vector x = r.getInput();
        double y = r.getOutput();
        
        Vector rangeInput = maxInput.subtract(minInput);
        double[] rangeValues = rangeInput.getNumbers();
        for (int i = 0; i < rangeValues.length; i++) {
            if (Math.abs(rangeValues[i]) < 1e-10) {
                rangeValues[i] = 1.0;
            }
        }
        Vector safeRangeInput = new Vector(rangeValues);
        
        Vector normalizedX = x.subtract(minInput).DivisionNumbers(safeRangeInput);
        
        double rangeOutput = maxOutput - minOutput;
        if (Math.abs(rangeOutput) < 1e-10) {
            rangeOutput = 1.0;
        }
        double normalizedY = (y - minOutput) / rangeOutput;
        
        return new Record(normalizedX, normalizedY);
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
