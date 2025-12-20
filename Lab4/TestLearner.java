package Lab4;


public class TestLearner {
    public static void main(String args[]) {

        RawDataset rawData = new RawDataset(2);
        // we test vectors that have a linaer relation to check that the predicitons fall behind these outputs ! 
        // y = 2*x1 + 3*x2 + 1
        rawData.addRecord(new Record(new Vector(new double[]{1.0, 1.0}), 6.0));  // 2*1 + 3*1 + 1 = 6
        rawData.addRecord(new Record(new Vector(new double[]{1.0, 2.0}), 9.0));  // 2*1 + 3*2 + 1 = 9  
        rawData.addRecord(new Record(new Vector(new double[]{2.0, 1.0}), 8.0));  // 2*2 + 3*1 + 1 = 8
        
        System.out.println("Raw dataset:");
        System.out.println(rawData);
        
        Dataset stdData = rawData.standardize();
        System.out.println("Standardized dataset:");
        System.out.println(stdData);
        
        // Test Gradient Descent on raw data
        System.out.println("\n=Gradient Descenden on raw data: ");
        GradientDescent gd = new GradientDescent(0.01, 0.008);
        SupervisedLearner learnerGDraw = new SupervisedLearner(gd, rawData);
        learnerGDraw.solve();
        System.out.println("GD Model: " + learnerGDraw);
        
        // Test predictions on training data
        System.out.println("\nGradient Descenden Raw Data Predictions: ");
        for(Record record : rawData.getData()) {
            System.out.println("Expected Output: " + record.getOutput()); 
            double predicted = learnerGDraw.predict(record.getInput()); 
            System.out.println("Predicted Output: " + predicted); 
        }
        
        // Test Gradient Descent on standardized data
        System.out.println("\nGradient Descenden on Standarized Data: ");
        SupervisedLearner learnerGDstd = new SupervisedLearner(gd, stdData);
        learnerGDstd.solve();
        System.out.println("GD Model (std): " + learnerGDstd);
        
        // Test predictions on training data
        System.out.println("\nGradient Descendent Standardized Data Predictions: ");
        for(Record record : stdData.getData()) {
            System.out.println("Expected Output: " + record.getOutput()); 
            double predicted = learnerGDstd.predict(record.getInput()); 
            System.out.println("Predicted Output: " + predicted); 
        }
        
        //  Test Stochastic Gradient Descent on raw data
        System.out.println("\nStochastic Gradient Descendent on Raw data set: ");
        StochasticGradientDescent sgd = new StochasticGradientDescent(0.005, 2, 2000);
        SupervisedLearner learnerSGDraw = new SupervisedLearner(sgd, rawData);
        learnerSGDraw.solve();
        System.out.println("SGD Model: " + learnerSGDraw);
        
        // Test predictions on training data
        System.out.println("\nStochastic Gradient Descendent Raw Data Predictions: ");
        for(Record record : rawData.getData()) {
            System.out.println("Expected Output: " + record.getOutput()); 
            double predicted = learnerSGDraw.predict(record.getInput()); 
            System.out.println("Predicted Output: " + predicted); 
        }
        
        //  Test Stochastic Gradient Descent on standardized data
        System.out.println("\nStochastic Gradient Descendent on Standarized data set: ");
        SupervisedLearner learnerSGDstd = new SupervisedLearner(sgd, stdData);
        learnerSGDstd.solve();
        System.out.println("SGD Model (std): " + learnerSGDstd);
        
        // Test predictions on training data
        System.out.println("\nStochastic Gradient Descendent Standardized Data Predictions: ");
        for(Record record : stdData.getData()) {
            System.out.println("Expected Output: " + record.getOutput()); 
            double predicted = learnerSGDstd.predict(record.getInput()); 
            System.out.println("Predicted Output: " + predicted); 
        }
        
        // Compare all models on a test input
        System.out.println("\nCOMPARISON ON A INPUTED VECTOR: ");
        Vector testInput = new Vector(new double[]{2.0, 1.0});
        System.out.println("Test input: " + testInput.convertString());
        
        System.out.println("GD raw prediction: " + learnerGDraw.predict(testInput));
        System.out.println("GD std prediction: " + learnerGDstd.predict(testInput));
        System.out.println("SGD raw prediction: " + learnerSGDraw.predict(testInput));
        System.out.println("SGD std prediction: " + learnerSGDstd.predict(testInput));
    }
}
