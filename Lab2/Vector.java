package Lab2;
import java.util.Arrays;


public class Vector {
    private double[] numbers;  
    private int dimension; // size of the vector

    // Constructor 1: create vector from an existing array
    public Vector(double[] numbers) {
        this.numbers = numbers;// copy for safety
        this.dimension = numbers.length;
    }

    // Constructor 2: create vector of given size, filled with a value
    public Vector(int dimension, double val) {
        this.dimension = dimension;
        this.numbers = new double[dimension];
        Arrays.fill(this.numbers, val);
    }

    public int getDimension(){
        return this.dimension; 
    }

    public double[] getNumbers(){
        return this.numbers;
    }

    public Vector add(Vector v) {
        if (this.dimension != v.getDimension()){
            throw new IllegalArgumentException("Vectors must be of the same dimension to add.");
        }
        double[] vvector = v.getNumbers();
        double[] result = new double[this.dimension]; 
        for (int i = 0; i < this.dimension; i++){
            result[i] = this.numbers[i] + vvector[i]; 
            }
            return new Vector(result);
    }
    
     public Vector subtract(Vector v) {
        if (this.dimension != v.getDimension()){
            throw new IllegalArgumentException("Vectors must be of the same dimension to subtract.");
        }
        double[] vvector = v.getNumbers();
        double[] result = new double[this.dimension]; 
        for (int i = 0; i < this.dimension; i++){
            result[i] = this.numbers[i] - vvector[i]; 
            }
            return new Vector(result); 
    }

    public Vector MultiplyNumbers (Vector v){
        if (this.dimension != v.getDimension()){
            throw new IllegalArgumentException("Vectors must be of the same dimension to multiply.");
        }
        double[] vvector = v.getNumbers();
        double[] result = new double[this.dimension];
        for (int i = 0; i < this.dimension; i++){
            result[i] = this.numbers[i] * vvector[i]; 
            }
            return new Vector(result); 
    }

    public Vector DivisionNumbers (Vector v){
        if (this.dimension != v.getDimension()){
            throw new IllegalArgumentException("Vectors must be of the same dimension to divide.");
        }
        double[] vvector = v.getNumbers();
        double[] result = new double[this.dimension];
        for (int i = 0; i < this.dimension; i++){
            if (vvector[i] == 0) {
                throw new ArithmeticException("Division by zero at index " + i);
            }
            result[i] = this.numbers[i] / vvector[i]; 
        }
        return new Vector(result); 
    }

    public Vector MultiplyScalar(double a){
        double[] result = new double[this.dimension];
        for (int i = 0; i < this.dimension; i++){
            result[i] = this.numbers[i] * a; 
            }
            return new Vector(result);
    }

    public Vector DivideScalar(double a){
        if (a == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed.");
        }
        double[] result = new double[this.dimension];
        for (int i = 0; i < this.dimension; i++){
            result[i] = this.numbers[i] / a; 
            }
            return new Vector(result);
    }

    public Vector SquareRoot(){
        double[] result = new double[this.dimension];
        for (int i = 0; i < this.dimension; i++){
            result[i] = Math.sqrt(this.numbers[i]);
        }
        return new Vector(result);
    }

    public double DotProduct(Vector v){
        if (this.dimension != v.getDimension()){
            throw new IllegalArgumentException("Vectors must be of the same dimension to compute the dot product.");
        }
        double result = 0;
        for (int i = 0; i < this.dimension; i++) {
            result += this.numbers[i] * v.numbers[i];
        }
        return result;
    }

    public double NormVector(){
        return Math.sqrt(this.DotProduct(this));
    }

    public String convertString(){
        String s = Arrays.toString(this.numbers);
        return s; 
    }
}