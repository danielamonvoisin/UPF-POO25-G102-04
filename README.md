This repository contains a five-lab project developed for the Object-Oriented Programming course at Universitat Pompeu Fabra (UPF). 
The project builds a small machine-learning framework from scratch, progressively applying object-oriented programming concepts to data representation, 
preprocessing, model training, and prediction.

The final system implements supervised learning for linear regression using both Gradient Descent and Stochastic Gradient Descent. During training, every record contains an 
input vector and its corresponding true label (the expected numerical output). Once the model has learned its parameters, it can predict an output for a new input whose label is 
unknown.

Supervised vs. unsupervised learning: this project is supervised because the training data includes true labels. 
Predicting an unknown output for a new input is still supervised learning; unsupervised learning would train without labels and would require a different objective, 
such as discovering clusters or hidden structure. Unsupervised algorithms are not implemented in these labs.

The main goal was to understand how a machine-learning system can be designed as a collection of reusable and independent objects instead of relying on an existing ML library. 
The project covers:

  - Mathematical vector operations

  - Multidimensional labelled records

  - Dataset construction and validation

  - Normalization and standardization

  - Linear regression with a bias term

  - Batch Gradient Descent

  - Mini-batch Stochastic Gradient Descent

  - Prediction on unseen inputs

  - Abstraction, inheritance, polymorphism, encapsulation, and composition

  - Migration of the complete implementation from Java to C++
