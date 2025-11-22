package Lab4;

import java.util.ArrayList;
import java.util.Random;

public class StochasticGradientDescent extends Algorithm{
    private int batchSize; 
    private int iterations; 
    private Random random; 

    public StochasticGradientDescent(double ls, int bs, int it ){
        super(ls); 
        this.batchSize = bs; 
        this.iterations = it; 
        this.random = new Random(); 
    }
    
    public Vector StochasticGradient(Dataset dataset, Model model){
        if(this.batchSize > dataset.getData().size()){
            throw new IllegalArgumentException("Batch size must be smaller than dataset size!");
        }
        int[] r = random.ints(0, dataset.getData().size()).distinct().limit(this.batchSize).toArray(); // now we have set the array thta will store the r positions to access the arraylist of records of the dataset. 
        int sizer = r.length; 
        ArrayList<Record> records = dataset.getData(); 
        ArrayList<Record> miniBatch = new ArrayList<>(); 
        for(int i = 0; i < sizer; i++){
            miniBatch.add(records.get(r[i])); 
        }
        // now I have the new minibatch B that stores some records from the full dataset, lets perform the same process as in the gradient descen on this minibatch: 

        Vector gradientSUM = new Vector(model.getParams().getDimension(), 0);
        for (Record record : miniBatch) { // CHANGE ! --> iterate through the miniBatch ArrayList not through all the dataset. 
            Vector InputAugmented = record.getInput().augment();
            double error = model.getParams().DotProduct(InputAugmented) - record.getOutput();
            Vector gradient_i = InputAugmented.MultiplyScalar(error);
            gradientSUM = gradientSUM.add(gradient_i);
            }
        
            Vector gradient = gradientSUM.DivideScalar(sizer);

        return gradient;
    }


    @Override
    public Model solve(Dataset dataset){ // now that we have found the stochastic gradient we can train the model and obtain the params vector. 
        Model model = new Model(dataset.getDim() + 1); 
        for(int i = 0; i < iterations; i++){ // no longer works comparing it with the normalized stopping criterion but with number of iterations. 
            Vector gradientVector = this.StochasticGradient(dataset, model);
            model.update(gradientVector, learningRate); 
        }
        return model; 
    }
}