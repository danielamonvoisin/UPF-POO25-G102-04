
#ifndef DATASET_H
#define DATASET_H

#include "Record.h"
#include <vector>
#include <iostream>

class Dataset {
protected:
    int dim;
    std::vector<Record> data;
    
public:
    Dataset(int n) : dim(n) {}
    
    int getDim() const { return dim; }
    std::vector<Record> getData() const { return data; }
    
    void addRecord(Record r) {
        data.push_back(r);
    }
    
    friend std::ostream& operator<<(std::ostream& os, const Dataset& ds) {
        if (ds.data.empty()) return os << "[]";
        os << "[" << ds.data[0];
        for (int i = 1; i < ds.data.size(); ++i)
            os << ", " << ds.data[i];
        return os << "]";
    }
};

#endif