#ifndef STOCHASTICGRADIENTDESCENT_H
#define STOCHASTICGRADIENTDESCENT_H

#include "Algorithm.h"
#include "Vector.h"
#include "Dataset.h"
#include "Model.h"
#include <vector>
#include <random>
#include <algorithm>
#include <iterator>
#include <stdexcept>

class StochasticGradientDescent : public Algorithm {
private:
    int batchSize;
    int iterations;

public:
    StochasticGradientDescent(double lr, int bs, int it)
        : Algorithm(lr), batchSize(bs), iterations(it) {
        if (batchSize <= 0) {
            throw std::invalid_argument("La mida del batch ha de ser positiva");
        }
        if (iterations <= 0) {
            throw std::invalid_argument("El nombre d'iteracions ha de ser positiu");
        }
    }

    int getBatchSize() const { return batchSize; }
    int getIterations() const { return iterations; }

    Vector stochasticGradient(const Dataset& dataset, const Model& model) {
        std::vector<Record> allData = dataset.getData();
        std::vector<Record> batch;

        std::random_device rd;
        std::mt19937 g(rd());
        std::shuffle(allData.begin(), allData.end(), g);

        int actualSize = std::min(batchSize, (int)allData.size());
        batch.assign(allData.begin(), allData.begin() + actualSize);

        int dim = dataset.getDim() + 1;
        Vector grad(dim, 0.0);
        if (batch.empty()) return grad;

        for (const Record& r : batch) {
            Vector x = r.getInput();
            double y = r.getOutput();
            Vector x_aug = x.augment();
            double y_pred = model.predict(x_aug);
            Vector term = x_aug.multiply(y_pred - y);
            grad = grad.add(term);  
        }
        return grad.divide(batch.size());
    }

    Model solve(const Dataset& dataset) override {
        int dim = dataset.getDim() + 1;
        Model model(dim);
        for (int iter = 0; iter < iterations; iter++) {
            Vector grad = stochasticGradient(dataset, model);
            model.update(grad, learningRate);
        }
        return model;
    }
};

#endif