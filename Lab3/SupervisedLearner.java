package Lab3;

import java.util.Arrays;

public class SupervisedLearner {
    private Algorithm algorithm; 
    private Dataset dataset; 
    private Model model; // at the beggining there is no model 

    public SupervisedLearner(Algorithm a, Dataset d){ // we do not pass yet the model because is what the algotrithm is goign to learn!
                                                        // so it creates a default model internally, with all zeros as parameters (later we can updtaed it)
        this.algorithm = a;
        this.dataset = d; 
        int dimension = d.getDim(); 
        this.model = new Model(dimension + 1); // we add +1 because of the bias term
    }

    public void solve(){
        this.model = this.algorithm.solve(this.dataset); // to apply the gradient descent with the dataset and model that we have in this class. 
    }

    public double predict(Vector x){
        Vector augmented = x.augment();  
        return this.model.predict(augmented); 
    }

    public String toString(){
        double[] parameters = this.model.getParams().getNumbers();
        String params = Arrays.toString(parameters); 
        return "parameters" + params; 
    }
}
