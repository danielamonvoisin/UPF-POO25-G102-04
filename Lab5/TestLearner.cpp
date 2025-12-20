#include "SupervisedLearner.h"
#include "GradientDescent.h"
#include "StochasticGradientDescent.h"
#include "Dataset.h"
#include "Vector.h"
#include "Record.h"
#include <iostream>
#include <vector>

int main() {
   std::cout << "LAB 5: Linear Regression in C++" << std::endl;

// first test to create the dataset (y = 3x + 1)
Dataset ds(1);
ds.addRecord(Record(Vector(std::vector<double>{1.0}), 4.0));
ds.addRecord(Record(Vector(std::vector<double>{2.0}), 7.0));
ds.addRecord(Record(Vector(std::vector<double>{3.0}), 10.0));
ds.addRecord(Record(Vector(std::vector<double>{4.0}), 13.0));
ds.addRecord(Record(Vector(std::vector<double>{5.0}), 16.0));

std::cout << "\nDataset: " << ds << std::endl;
std::cout << "Dataset dimension: " << ds.getDim() << std::endl;

// second test for testing the Gradient Descent: 
std::cout << "\n--- Gradient Descent ---" << std::endl;
try {
    GradientDescent gd(0.01, 0.001);
    SupervisedLearner learner1(&gd, ds);
    learner1.solve();
    std::cout << "Trained model: " << learner1 << std::endl;

    Vector input1(std::vector<double>{6.0});
    double prediction1 = learner1.predict(input1);
    std::cout << "Prediction for x = 6.0: " << prediction1 << std::endl;
    std::cout << "Expected value: 19.0" << std::endl;
} catch (const std::exception& e) {
    std::cout << "ERROR in Gradient Descent: " << e.what() << std::endl;
}

// third test for testing the stochastic Gradient Descent: 
std::cout << "\n--- Stochastic Gradient Descent ---" << std::endl;
try {
    StochasticGradientDescent sgd(0.01, 2, 1000);
    SupervisedLearner learner2(&sgd, ds);
    learner2.solve();
    std::cout << "Trained model: " << learner2 << std::endl;

    Vector input2(std::vector<double>{6.0});
    double prediction2 = learner2.predict(input2);
    std::cout << "Prediction for x = 6.0: " << prediction2 << std::endl;
    std::cout << "Expected value: 19.0" << std::endl;
} catch (const std::exception& e) {
    std::cout << "ERROR in Stochastic Gradient Descent: " << e.what() << std::endl;
}

    // the additional tests: 
    std::cout << "\n--- Additional tests ---" << std::endl;
    try {
        GradientDescent gd2(0.01, 0.001);
        SupervisedLearner learner3(&gd2, ds);
        learner3.solve();

        Vector input3(std::vector<double>{0.0});
        Vector input4(std::vector<double>{10.0});
        Vector input5(std::vector<double>{-2.0});

        std::cout << "Prediction for x = 0.0: " << learner3.predict(input3) << std::endl;
        std::cout << "Prediction for x = 10.0: " << learner3.predict(input4) << std::endl;
        std::cout << "Prediction for x = -2.0: " << learner3.predict(input5) << std::endl;
    } catch (const std::exception& e) {
        std::cout << "ERROR in additional tests: " << e.what() << std::endl;
    }

    std::cout << "\n=== End of test ===" << std::endl;
    return 0;

}