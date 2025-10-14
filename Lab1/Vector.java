package Lab1;
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
            System.out.println("Vectors must be of the same dimension to add.");
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
            System.out.println("Vectors must be of the same dimension to add.");
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
            System.out.println("Vectors must be of the same dimension to add.");
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
            System.out.println("Vectors must be of the same dimension to add.");
        }
        double[] vvector = v.getNumbers();
        double[] result = new double[this.dimension];
        for (int i = 0; i < this.dimension; i++){
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
        double[] result = new double[this.dimension];
        for (int i = 0; i < this.dimension; i++){
            result[i] = this.numbers[i] / a; 
            }
            return new Vector(result);
    }

    public Vector SquareRoot(){
        for (int i = 0; i < this.dimension; i++){
            Math.sqrt(this.numbers[i]); 
            }
        return new Vector(this.numbers); 
    }

    public double DotProduct(Vector v){
        

    }

    public double NormVector(){

    }


    public String convertString(){
        String s = Arrays.toString(this.numbers);
        return s; 
    }


}


