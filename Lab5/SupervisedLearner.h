#ifndef SUPERVISEDLEARNER_H
#define SUPERVISEDLEARNER_H

#include "Algorithm.h"
#include "Dataset.h"
#include "Model.h"
#include "Vector.h"
#include <iostream>

class SupervisedLearner {
private:
    Algorithm* algorithm;
    Dataset dataset;
    Model model;

public:
    SupervisedLearner(Algorithm* algo, const Dataset& ds)
        : algorithm(algo), dataset(ds), model(ds.getDim()) {
    }

    void solve() {
        model = algorithm->solve(dataset);
    }

    double predict(const Vector& v) {
        return model.predict(v.augment());
    }

    friend std::ostream& operator<<(std::ostream& os, const SupervisedLearner& sl) {
        os << sl.model;
        return os;
    }
};

#endif