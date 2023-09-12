#include <iostream>
#include <vector>
#include "nameEnor.h"
#include "yearEnor.h"

using namespace std;

//First task
bool first_search(const string &fname,Contestant& e)
{
    nameEnor t(fname);
    bool l = false;
    for(t.first(); !l && !t.end(); t.next()){
        l = t.current().atleastThree;
        e = t.current();
    }
    return l;
}

//Second task
vector<int> second_search(const string &fname,tenperYear& e)
{
    yearEnor t(fname);
    vector<int> l;
    for (t.first(); ! t.end(); t.next()){
        if (t.current().atleastTen){
            l.push_back(t.current().yearWithTen);
        }
    }
    return l;
}

//#define NORMAL_MODE
#ifdef NORMAL_MODE

int main()
{
    cout<<"First task\n";
	try{
        Contestant e;
        if(first_search("input.txt",e)){
            cout<<e.name<<" has got "<<e.golds<<" gold in "<<e.year<<endl;
        }else{
            cout<<"There is no contestant matching our search criteria.\n";
        }
	}catch(nameEnor::FileError err)
	{
		cerr<<"Can't find the input file" <<endl;
	}

	cout<<"Second task\n";
	try{
        tenperYear y;
        for(int i = 0;i < second_search("input.txt",y).size();i++){
            cout<<second_search("input.txt",y)[i]<<endl;
        }
	}catch(nameEnor::FileError err)
	{
		cerr<<"Can't find the input file" <<endl;
	}


    return 0;
}

#else
#define CATCH_CONFIG_MAIN
#include "catch.hpp"

TEST_CASE("tc1 empty file", "t1.txt") {
    Contestant e;
    CHECK_FALSE(first_search("t1.txt", e));
}

TEST_CASE("tc2", "t2.txt") {
    Contestant e;
    CHECK(first_search("t2.txt", e));
    CHECK(e.name=="Peter Adam");
}

TEST_CASE("tc5", "t5.txt") {
    Contestant e;
    CHECK(first_search("t5.txt", e));
}

TEST_CASE("tc3 empty file", "t3.txt") {
    tenperYear e;
    vector<int> l = {1,2,3,4,5,6,7,8,9,10,11,12};
    CHECK_FALSE(second_search("t3.txt", e) == l);
}

TEST_CASE("tc4", "t4.txt") {
    tenperYear e;
    vector<int> l = {2002,2020};
    CHECK(second_search("t4.txt", e) == l);
}

TEST_CASE("tc6", "t6.txt") {
    tenperYear e;
    vector<int> l = {};
    CHECK(second_search("t6.txt", e) == l);
}

#endif
