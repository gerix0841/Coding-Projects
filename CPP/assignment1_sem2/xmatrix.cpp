#include "xmatrix.h"
#include <iostream>
#include <vector>
#include <math.h>

using namespace std;

xmatrix xmatrix::sum(const xmatrix& mat)
{
	if(matrixSize != mat.getmatrixSize()){
        throw "Nem egyenlo!\n";
	}
	xmatrix emat(matrixSize);
    emat.vectorSize = getSize();
    emat.matrixSize = getmatrixSize();
    for(int i = 0;i < vectorSize;i++){
        emat.matrix.push_back(0);
    }

	for(int i = 0;i < vectorSize;i++){
        emat.matrix[i] = getE(i) + mat.getE(i);
	}

	return emat;
}

xmatrix xmatrix::mul(const xmatrix& mat)
{
	if(matrixSize != mat.getmatrixSize()){
        throw "Nem egyenlo!\n";
	}
	int x,i = 0,ind1 = 0,ind2 = 0,ind3 = matrixSize,ind4 = matrix.size()-1;
	xmatrix emat(matrixSize);
    emat.vectorSize = matrix.size();
    emat.matrixSize = matrixSize;
    emat.matrix.resize(matrix.size());

    while(i < matrix.size()/2){
        x = matrix[ind1] * mat.matrix[ind2] + matrix[ind3] * mat.matrix[ind4];
        emat.matrix[i] = x;
        i++;
        ind4--;
        ind2++;
        ind1++;
        ind3++;
    }
    ind1 = 0,ind2 = matrixSize-1,ind3 = matrixSize,ind4 = matrixSize;
    while(i < matrix.size()){
        x = matrix[ind1] * mat.matrix[ind4] + matrix[ind3] * mat.matrix[ind2];
        emat.matrix[i] = x;
        i++;
        ind4++;
        ind2--;
        ind1++;
        ind3++;
    }


    return emat;
}

int xmatrix::getIndex(int i, int j) const
{
	if(i >= 0 && i < matrixSize && j >= 0 && j < matrixSize){
		if(i == j || i == matrixSize - j - 1){
            if(i == j){
                return i;
            }
            else{
                return i + matrixSize;
            }
		}
		else return -2;
	}
	else return -1;
}

void xmatrix::printMatrix()
{
    for(int i = 0;i < matrixSize;i++){
        for(int j = 0;j < matrixSize;j++){
            cout<<getElement(i,j)<<" ";
        }
        cout<<endl;
    }
}

int xmatrix::getElement(int i, int j)const
{
	int index = getIndex(i,j);

	if(index == -1){
        throw "Rossz index!\n";
	}
	else if(index == -2){
        return 0;
	}
	else{
        return getE(index);
	}
}

void xmatrix::setElement(int i, int j, int x)
{
	int index = getIndex(i,j);
	if(index == -1 || index == -2){
		throw "Rossz index!\n";
	}
	else{
		matrix[index] = x;
	}
}

void xmatrix::setElementMul(int i, int j, int x)
{
    int index = getIndex(i,j);
    matrix[index] = x;
}

void xmatrix::readMatrix(int n)
{
    vectorSize = n;
    matrix.resize(vectorSize);
    vector<int> m;
    int read;
	for(int i = 0;i < vectorSize;i++){
        cout<<"Add meg a kovetkezo elemet: ";
        cin>>read;
        m.push_back(read);
	}
	if(m.size() % 2 == 0){
        matrixSize = m.size()/2;
	}
	else{
        matrixSize = m.size()/2+1;
	}
	matrix = m;
}

xmatrix::xmatrix(const vector<int>& mat)
{
    int n;
    if(mat.size() % 2 == 0){
        n = mat.size() / 2;
    }
    else{
        n = mat.size() / 2 - 1;
    }
    matrixSize = n;
    vectorSize = mat.size();
    for(int i = 0;i < (int)mat.size();i++){
        matrix.push_back(mat[i]);
    }

}

vector<int> xmatrix::getData()
{
    vector<int> vec;
    int n = getSize();

    for(int i = 0;i < n;i++){
        vec.push_back(matrix[i]);
    }
    return vec;
}

