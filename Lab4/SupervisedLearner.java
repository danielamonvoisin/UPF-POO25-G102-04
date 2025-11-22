package Lab4;

import java.util.Arrays;

public class SupervisedLearner {
    private Algorithm algorithm; 
    private Dataset dataset; 
    private Model model; // at the beggining there is no model 

    public SupervisedLearner(Algorithm a, Dataset d){ /*we do not pass yet the model because is what the algotrithm is goign to learn!
        so it creates a default model internally, with all zeros as parameters (later we can updtaed it) */
        this.algorithm = a;
        this.dataset = d; 
        int dimension = d.getDim(); 
        this.model = new Model(dimension + 1); // we add +1 because of the bias term
    }

    public void solve(){
        this.model = this.algorithm.solve(this.dataset); // to apply the gradient descent with the dataset and model that we have in this class. 
    }

    public double predict(Vector x){
        
        Vector xTransformed = this.dataset.transform(x); // by polymorphism depending on how we instantiataed the dataset (either RawDataset or StandarizedDataset) it will go to one method or the other (two overriden methods). 
        
        // now we have the Record transformed, so the input vector x standarized because of: Vector standardizedX = x.subtract(meanInput).DivisionNumbers(stdInput);

        Vector augmented = xTransformed.augment(); 
        
        // By polymorphism again we run the output method in th abstract class: 
        
        return this.dataset.output(this.model.predict(augmented)); 
       
    }

    public String toString(){
        double[] parameters = this.model.getParams().getNumbers();
        String params = Arrays.toString(parameters); 
        return "parameters" + params; 
    }
}