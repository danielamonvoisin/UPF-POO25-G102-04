package Lab2;

public class TestDataset {
    public static void main(String[] args) {
       
    
        Dataset dataset = new Dataset(2); // to crate a dataset with 2 dimensional vector types
        
       
        dataset.addRecord(new Record(new Vector(new double[]{1.0, 2.0}), 3.0)); // using the addRecord method to the dataset
        dataset.addRecord(new Record(new Vector(new double[]{2.0, 3.0}), 4.0));
        dataset.addRecord(new Record(new Vector(new double[]{3.0, 4.0}), 5.0));

    
        System.out.println("Original Dataset:"); // printing the original dataset, to compare later and look that methods like (meanInput, stdInput...) work
        System.out.println(dataset);
        System.out.println("Mean Input: " + dataset.meanInput().convertString());
        System.out.println("Std Input: " + dataset.stdInput().convertString());
        System.out.println("Mean Output: " + dataset.meanOutput());
        System.out.println("Std Output: " + dataset.stdOutput());
        System.out.println();

        
        StandardizedDataset standardized = dataset.standardize(); // Standardize the dataset (mean = 0, variance = 1)


        System.out.println("Standardized Dataset:"); // now printing the standarized dataset, applying the methods as before but on the standarized set. 
        System.out.println(standardized);
        System.out.println("Mean Input (after standardization): " + standardized.meanInput().convertString());
        System.out.println("Std Input (after standardization): " + standardized.stdInput().convertString());
        System.out.println("Mean Output (after standardization): " + standardized.meanOutput());
        System.out.println("Std Output (after standardization): " + standardized.stdOutput());
        System.out.println();

      
        Record newRecord = new Record(new Vector(new double[]{2.5, 3.5}), 4.5); // test if the transfrom method wroks --> transforming a new record using the standardization parameters. 
        Record transformed = standardized.transform(newRecord);
        System.out.println("Transform Test:");
        System.out.println("Original Record: " + newRecord.convertString());
        System.out.println("Transformed Record: " + transformed.convertString());
    }
}


