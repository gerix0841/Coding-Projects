#include <iostream>
#include <vector>
#include <cstring>
#include "xmatrix.h"

using namespace std;

//#define NORMAL_MODE
#ifdef NORMAL_MODE

class Menu
{
public:
    Menu() : matrix(0) { }
    void run();
    string strs;
private:
    xmatrix matrix;

    void menuWrite();
    void getElem();
    void setElem();
    void read();
    void write();
    void sum();
    void mul();
};

int main()
{
    Menu m;
    m.run();
}

bool check(int n) { return 0 <= n && n <= 6; }

void Menu::run()
{
    int n = 0;
    do{
        menuWrite();

        cout<<"\n>>>> ";
        cin>>n;
        if(check(n) == true){
            switch(n)
            {
            case 1:
                getElem();
                break;
            case 2:
                setElem();
                break;
            case 3:
                read();
                break;
            case 4:
                write();
                break;
            case 5:
                sum();
                break;
            case 6:
                mul();
                break;
            }
        }
    }while(n!=0);

}

void Menu::menuWrite()
{
    cout<<"0. Kilepes\n";
	cout<<"1. Matrix elemenek lekerese\n";
	cout<<"2. Matrix elemenek megvaltozatasa\n";
	cout<<"3. Matrix beolvasasa\n";
	cout<<"4. Matrix kiirasa\n";
	cout<<"5. Matrix osszeadasa\n";
	cout<<"6. Matrix szorzasa\n";
}

void Menu::getElem()
{
    int i,j;
    cout << "Give the index of the row: ";
    cin >> i;
    cout << "Give the index of the column: ";
    cin >> j;
    try
    {
        cout << "a[" << i << "," << j << "]= " << matrix.getElement(i,j) << endl;
    }
    catch(const char* str )
    {
        str = "Hibas index!\n";
        cout<<str<<endl;
    }
}

void Menu::setElem()
{
    int i,j,e;
    cout << "Give the index of the row: ";
    cin >> i;
    cout << "Give the index of the column: ";
    cin >> j;
    cout << "Give the value: ";
    cin >> e;
    try
    {
        matrix.setElement(i,j,e);
    }
    catch(const char* str )
    {
        str = "Hibas index!\n";
        cout<<str<<endl;
    }
}

void Menu::read()
{
	int n;
	bool error=false;
	cout<<"Adja meg a hanyszor hanyas a matrix\n";
	do
	{
		cin>>n;
		error=cin.fail() || n < 1;
		if(error) { cout<<"Hibas meret!\n"; cin.clear(); getline(cin,strs);}
	}
	while(error);

    if(n % 2 == 0){
        n = n*2;
    }
    else{
        n = n*2-1;
    }

    matrix.readMatrix(n);
    matrix.printMatrix();
}

void Menu::write()
{
    matrix.printMatrix();
}

void Menu::sum()
{
    try
    {
        int n = matrix.getSize();
        if(n % 2 == 0) n = matrix.getSize();
        else n = matrix.getSize()-1;
        xmatrix matrix2(matrix.getmatrixSize());
        xmatrix emat(matrix.getmatrixSize());

        cout<<"2.Matrix elemei:\n";
        matrix2.readMatrix(n);
        cout<<"2 Matrix osszege: "<<endl;
        emat = matrix.sum(matrix2);
        emat.printMatrix();
    }
    catch(const char* str )
    {
        str = "Hibas bemenet!\n";
        cout<<str<<endl;
    }
}

void Menu::mul()
{
    try
    {
        int n = matrix.getSize();
        if(n % 2 == 0) n = matrix.getSize();
        else n = matrix.getSize()-1;
        xmatrix matrix2(matrix.getmatrixSize());
        xmatrix emat(matrix.getmatrixSize());

        cout<<"2.Matrix elemei:\n";
        matrix2.readMatrix(n);
        cout<<"2 Matrix szorzata: "<<endl;
        emat = matrix.mul(matrix2);
        emat.printMatrix();
    }
    catch(const char* str )
    {
        str = "Hibas bemenet!\n";
        cout<<str<<endl;
    }


}

#else
#define CATCH_CONFIG_MAIN
#include "catch.hpp"

TEST_CASE("getElement, setElement","")
{
    vector<int> matrix = {1,2,3,4,5,6,7,8,9,10,11,12};
    xmatrix xm(matrix);

	CHECK(xm.getElement(1,1)==2);
	CHECK(xm.getElement(2,2)==3);
	CHECK(xm.getElement(1,2)==0);
	CHECK(xm.getElement(2,0)==0);

	xm.setElement(2,2,2);
	xm.setElement(1,1,8);
	xm.setElement(3,3,4);

	CHECK(xm.getElement(1,1)==8);
	CHECK(xm.getElement(0,0)==1);
	CHECK(xm.getElement(3,3)==4);
	try
	{
		xm.getElement(0,2);
	}catch(const char* e)
	{
		CHECK(strcmp("Invalid index!\n",e)==0);
	}
	try
	{
		xm.setElement(2,2,8);
	}catch(const char* e)
	{
		CHECK(strcmp("This element can't be changed!\n",e)==0);
	}
	try
	{
		xm.setElement(0,5,7);
	}catch(const char* e)
	{
		CHECK(strcmp("This element can't be changed!\n",e)==0);
	}
}

TEST_CASE("Osszeadas","")
{
	vector<int> v1={1,2,3,4,5,6,7,8,9,10,11,12};
	vector<int> v2={1,2,3,4,5,6,7,8,9,10,11,12};
	vector<int> v3={2,3,5,7,1,8,6,7,1,1,2,4};
	vector<int> v4={3,2,12,7,5,6,12,7,9,10,6,12};
	xmatrix m1(v1);
	xmatrix m2(v2);
	xmatrix m3(v3);
	xmatrix m4(v4);
    xmatrix me1(v1.size());
	xmatrix me2(v1.size());
	xmatrix me3(v1.size());

	me1 = m1.sum(m2);
	me2 = m2.sum(m3);
	me3 = m4.sum(m3);

	vector<int> ve1;
	for(int i = 1;i <= 12;i++) ve1.push_back(i * 2);
    vector<int> ve2={3,5,8,11,6,14,13,15,10,11,13,16};
    vector<int> ve3={5,5,17,14,6,14,18,14,10,11,8,16};

    xmatrix mev1(ve1);
    xmatrix mev2(ve2);

	CHECK(me1.getData() == mev1.getData());
	CHECK(me2.getData() == mev2.getData());
	CHECK(me3.getData() == ve3);
}

TEST_CASE("Szorzas","")
{
	vector<int> v1={1,2,3,4,5,6,7,8,9,10,11,12};
	vector<int> v2={1,2,3,4,5,6,7,8,9,10,11,12};
	vector<int> v3={2,3,5,7,1,8,6,7,9,10,11,12};
	vector<int> v4={4,2,12,7,5,6,12,7,9,10,11,12};
	xmatrix m1(v1);
	xmatrix m2(v2);
	xmatrix m3(v3);
	xmatrix m4(v4);
	xmatrix me1(v1.size());
	xmatrix me2(v1.size());
	xmatrix me3(v1.size());

	me1 = m1.mul(m2);
	me2 = m2.mul(m3);
	me3 = m4.mul(m3);

	vector<int> ve1={85,92,99,106,113,120,49,56,63,70,77,84};
    vector<int> ve2={86,94,105,118,82,120,62,22,90,90,88,96};
    vector<int> ve3={152,83,150,139,82,120,120,21,171,120,88,96};

    xmatrix mev1(ve1);
    xmatrix mev2(ve2);

	CHECK(me1.getData() == mev1.getData());
	CHECK(me2.getData() == mev2.getData());
	CHECK(me3.getData() == ve3);

}

#endif
