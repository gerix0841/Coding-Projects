#ifndef XMATRIX_H
#define XMATRIX_H
#include <vector>
#include <iostream>

using namespace std;

class xmatrix
{
    public:
        xmatrix(const vector<int> &);
        xmatrix(int mSize):matrixSize(mSize),vectorSize(setSize(mSize)) { }
        xmatrix():matrixSize(0),vectorSize(0) { }

        int getElement(int i, int j)const; //
        void setElement(int i, int j,int x); //
        void setElementMul(int i, int j,int x); //
        int getIndex(int i,int j)const; //
        int getE(int i)const {return matrix[i];} //
        vector<int> getData(); //

        int getmatrixSize()const {return matrixSize;} //
        int getSize()const {return matrix.size();} //

        void printMatrix(); //
        void readMatrix(int n); //

        xmatrix sum(const xmatrix& mat); //
        xmatrix mul(const xmatrix& mat); //


    private:
        vector<int> matrix;
        int matrixSize;
        int vectorSize;

        int setSize(int n){
            if(n % 2 == 0){
                return n*2;
            }
            else{
                return n*2-1;
            }
        }
};

#endif // XMATRIX_H
