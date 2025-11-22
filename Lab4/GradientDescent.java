package Lab4;

import java.util.List;

public class GradientDescent extends Algorithm {
    private double stoppingCriterion;
     
     
    public GradientDescent(double sc, double lr){
        super(lr); 
        this.stoppingCriterion = sc; 
     } 

    public Vector gradient(Dataset dataset, Model model){
        List<Record> records= dataset.getData(); // extracting the list of all records in the dataset. 
    
        // first we calulate the error for each record in the dataset: 
        double[] errorlist = new double[records.size()]; // error per record
        int i = 0; 
        for( Record record : records){ // iterate through all the records in the dataset
            errorlist[i] = model.getParams().DotProduct(record.getInput().augment()) - record.getOutput(); 
            i++;  
        }

        // Multiply the augmented input vector by its scalar error to obtain each gradient
        Vector gradientSUM = new Vector(model.getParams().getDimension(), 0); // equally to say the dimension of input + 1. 
        int j = 0; 
        for(Record record : records){ // iterate through all the records in the dataset
            Vector InputAugmented = record.getInput().augment(); 
            Vector gradient_i = InputAugmented.MultiplyScalar(errorlist[j]); 
            gradientSUM = gradientSUM.add(gradient_i); 
            j++;  
        }

        Vector gradient = gradientSUM.DivideScalar(records.size()); // dividing the computed gradient sum by the total amount of records.  

        return gradient; 
    }


    @Override
    public Model solve(Dataset dataset){
        Model model = new Model(dataset.getDim() + 1); 
        Boolean bool = true; 

        while (bool == true){
            Vector gradientVector = this.gradient(dataset, model);
            model.update(gradientVector, learningRate); 

            if (gradientVector.NormVector() < this.stoppingCriterion) { // we will exit the loop if the condition was met. 
                bool = false;
                }
        }

        return model; 
    }
    
}