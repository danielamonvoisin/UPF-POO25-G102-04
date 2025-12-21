#ifndef GRADIENTDESCENT_H
#define GRADIENTDESCENT_H

#include "Algorithm.h"
#include "Vector.h"
#include "Dataset.h"
#include "Model.h"
#include <vector>
#include <iostream>
#include <stdexcept>

class GradientDescent : public Algorithm {
private:
    double stoppingCriterion;
    static const int MAX_ITERATIONS = 100000;

public:
    // Constructor CORREGIT
    GradientDescent(double lr, double sc)
        : Algorithm(lr), stoppingCriterion(sc) {
        if (sc <= 0) {
            throw std::invalid_argument("El criteri d'aturada ha de ser positiu");
        }
    }

    Vector gradient(const Dataset& dataset, const Model& model) {
        int dim = dataset.getDim() + 1;
        Vector grad(dim, 0.0);
        std::vector<Record> records = dataset.getData();

        for (const Record& r : records) {
            Vector x = r.getInput();
            double y = r.getOutput();
            Vector x_aug = x.augment();
            double y_pred = model.predict(x_aug);
            Vector term = x_aug.multiply(y_pred - y);
            grad = grad.add(term);  
        }
        return grad.divide(records.size());
    }

    Model solve(const Dataset& dataset) override {
        int dim = dataset.getDim() + 1;
        Model model(dim);
        int iter = 0;

        while (iter < MAX_ITERATIONS) {
            Vector grad = gradient(dataset, model);
            if (grad.norm() < stoppingCriterion) {
                break;
            }
            model.update(grad, learningRate);
            iter++;
        }

        if (iter == MAX_ITERATIONS) {
            std::cout << "AVIS: No ha convergit." << std::endl;
        }
        return model;
    }
};

#endif