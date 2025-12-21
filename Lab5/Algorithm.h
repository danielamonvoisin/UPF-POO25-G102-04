#ifndef ALGORITHM_H  // Include Guard per evitar duplicació
#define ALGORITHM_H

#include "Dataset.h"
#include "Model.h"

class Algorithm {
protected:
    double learningRate;

public:
    Algorithm(double lr) : learningRate(lr) {}
    virtual ~Algorithm() = default;  
    virtual Model solve(const Dataset& dataset) = 0;  
};

#endif
