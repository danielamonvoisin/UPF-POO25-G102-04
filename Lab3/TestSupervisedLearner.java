package Lab3; 

import java.util.List;

public class TestSupervisedLearner {

    public static void  main (String[] args){

    // before creating the instance of SupervisedLearner que have to create the attributes that it needs: 

    Dataset dataset = new Dataset(2); // creating a dataset where the number of records is higer than the dimension of the vectors + 1 (n = 4, d = 2)
    dataset.addRecord(new Record(new Vector(new double[]{1.0, 2.0}), 3.0)); 
    dataset.addRecord(new Record(new Vector(new double[]{2.0, 3.0}), 10.0));
    dataset.addRecord(new Record(new Vector(new double[]{3.0, 4.0}), 5.0));
    dataset.addRecord(new Record(new Vector(new double[]{5.0, 0.0}), 3.0));

    Algorithm algorithm = new Algorithm(0.002, 0.000006); 

    SupervisedLearner supervisedLearner = new SupervisedLearner(algorithm, dataset); 
    Model model = new Model(dataset.getDim() + 1); 
  
    System.out.println("\n"); 
    System.out.println("Testing Augmented new method: "); 
    System.out.println("\n"); 

    // test augmented new method in vector class
    Vector vect1 = new Vector(new double[]{1.0, 2.0, 3.0});
    System.out.println("Normal vector:");
    System.out.println(vect1.convertString());
    Vector augmentedvect1 = vect1.augment(); 
    System.out.println("Augmented vector:");
    System.out.println(augmentedvect1.convertString());


    // // Test gradient for a full dataset and update the model class 
    Vector gradient = algorithm.gradient(dataset, model);
    System.out.println("Gradient:");
    System.out.println(gradient.convertString()); // WORKS! to check that the correct gradient was computed, should be grad=(−13.25,−14,−5.25). 

    
    System.out.println("\n"); 
    System.out.println("Checking that iterations are correct (norm becomes smaller and parameters are updated): "); 
    System.out.println("\n"); 

    // Print the norm of the gradient in different iterations to make sure that the norm becomes smaller. 
    int maxIterations = 20;
        for (int k = 0; k < maxIterations; k++) {
            gradient = algorithm.gradient(dataset, model); // compute gradient
            double gradNormalized = gradient.NormVector();             // compute L2 norm
            System.out.println("Iteration " + k + ", gradient normalized: " + gradNormalized);
            model.update(gradient, algorithm.learningRate); // update parameters
            System.out.println("Parameters" + model.getParams().convertString()); // also cheking that the update method on model class works so see if the parameters are updated. 
        }

    System.out.println("\n"); 
    System.out.println("FINAL CHECKING: ");
    System.out.println("\n"); 

    // finally having seen that all works we can check that the SupervisedLearner class works:
    supervisedLearner.solve(); // so we already have the model parameters updated ! So we can predict the output given the input augmented vectors of each record. 
    List<Record> records = dataset.getData(); 

    for(Record record : records) {
        System.out.println("Expected Output:" + record.getOutput()); 
        double predicted = supervisedLearner.predict(record.getInput()); 
        System.out.println("Predicted Output:" + predicted); 
    }

    }
}
