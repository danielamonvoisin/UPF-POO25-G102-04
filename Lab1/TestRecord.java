package Lab1;

import java.util.Arrays;

public class TestRecord {
    public static void main(String[] args){
        double[] data1 = {1.0, 4.0}; 
        double[] data2 = {2.0, 3.0}; 
        Vector Vector1 = new Vector(data1);
        Vector Vector2 = new Vector(data2); 
        Vector Vector3 = new Vector(3,2); 

        System.out.println("CHECK VECTOR CLASS TESTS: "); 

        System.out.println(Vector1.getDimension()); // check getDimendion method
        System.out.println(Vector2.getDimension());
        System.out.println(Vector3.getDimension());  

        System.out.println(Arrays.toString(Vector1.getNumbers())); // check getNumbers method and toString
        System.out.println(Arrays.toString(Vector2.getNumbers())); // check the constructor 1
        System.out.println(Arrays.toString(Vector3.getNumbers())); // check the constructor 2

         
        System.out.println(Arrays.toString(Vector1.add(Vector2).getNumbers())); // Add vector2 to vector1
        // System.out.println(Arrays.toString(Vector1.add(Vector3).getNumbers())); // checking the check dimension test
        System.out.println(Arrays.toString(Vector1.subtract(Vector2).getNumbers())); // subtract vector1 - vector2
        System.out.println(Arrays.toString(Vector1.MultiplyNumbers(Vector2).getNumbers())); // check multiplication
        System.out.println(Arrays.toString(Vector1.DivisionNumbers(Vector2).getNumbers())); // check division
        System.out.println(Arrays.toString(Vector3.MultiplyScalar(2).getNumbers())); // check multiplication by scalar
        System.out.println(Arrays.toString(Vector3.DivideScalar(2).getNumbers()));  // check division by scalar
        System.out.println(Arrays.toString(Vector1.SquareRoot().getNumbers())); // check the square root vector. 
        System.out.println(Vector2.DotProduct(Vector1)); // chekck the dot product vector
        System.out.println(Vector2.NormVector()); // check normvectr method


        System.out.println("CHECK RECORD CLASS TESTS: "); 

        Record record1 = new Record(2.0, 10.0);
        Record record2 = new Record(-3.5, 7.2);
        Record record3 = new Record(0.0, 0.0);


        System.out.println("Record1 X: " + record1.getX()); // getter method check
        System.out.println("Record1 Y: " + record1.getY());
        System.out.println("Record2 X: " + record2.getX());
        System.out.println("Record2 Y: " + record2.getY());

        System.out.println("Record1: " + record1.convertString()); // Convert to string check
        System.out.println("Record2: " + record2.convertString());
        System.out.println("Record3: " + record3.convertString());

        Record record5 = new Record(-1.0, -1.0);
        System.out.println("Record5: " + record5.convertString());

    }
    
}
