package Lab3;

public class Record {
    private Vector input;    // Multi-dimensional input vector
    private double output;   

    public Record(Vector input, double output) {
        this.input = input;
        this.output = output;
    }

    public Vector getInput() {
        return this.input;
    }

    public double getOutput() {
        return this.output;
    }

    public String convertString() {
        return "(Input: " + input.convertString() + ", Output: " + output + ")";
    }
}