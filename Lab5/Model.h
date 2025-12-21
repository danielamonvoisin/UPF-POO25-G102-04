#ifndef MODEL_H
#define MODEL_H

#include "Vector.h"
#include <iostream>

class Model {
private:
    Vector params;

public:
    Model(int dim) : params(dim, 0.0) {}  // Canviat de 1 a 0.0
    
    double predict(const Vector& input) const {
        return input.dotProduct(params);
    }
    
    void update(const Vector& delta, double learningRate) {
        Vector rate(params.getDim(), learningRate);
        params = params.subtract(delta.multiply(rate));
    }
    
    Vector getParams() const {
        return params;
    }
    
    friend std::ostream& operator<<(std::ostream& os, const Model& m) {
        return os << m.params;
    }
};

#endif