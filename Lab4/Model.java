package Lab4;

public class Model {
    private Vector params; 

    public Model(int dimension){ // intializing the model class with the parameter vector setted to 0. 
        this.params = new Vector(dimension, 0); 
    } 

    public Vector getParams(){
        return this.params;  
    }

    public double predict(Vector vect){
        double prediction = vect.DotProduct(this.params);
        return prediction;  
    }

    public void update(Vector gradientVect, double rate){
        double[] gradNumbers = gradientVect.getNumbers(); 
        double[] paramNumbers = this.params.getNumbers(); 
        for(int i = 0; i < this.params.getDimension(); i++){
            paramNumbers[i] = paramNumbers[i] - rate * gradNumbers[i]; 
        }
    }
    
}