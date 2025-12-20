package Lab4;


public abstract class Algorithm {
    public double learningRate;

    public Algorithm(double lr){
        this.learningRate = lr; 
    }

    // The class also contains an abstract method solve since all supervised learning algorithms should be
    // capable of learning and returning a model given a dataset.
    
    public abstract Model solve(Dataset dataset); 

}
