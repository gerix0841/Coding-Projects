#include <iostream>
#include <fstream>
#include <vector>

#include "Plant.h"
#include "Planet.h"
#include "Emission.h"

using namespace std;

int create(const string &fileName, vector<Plant*> &plants)
{
    ifstream f(fileName);
    if(f.fail())
    {
        cout << "Wrong file name!\n";
        exit(1);
    }

    // Plants
    unsigned int n;
    f >> n;
    plants.resize(n);
    for( unsigned int i = 0; i < n; ++i )
    {
        char ch;
        string name;
        int health;
        f >> name >> ch >> health;
        if(health>0)
        {
            switch(ch)
            {
                case 'p' :
                    plants[i] = new Puffancs(name,health);
                    break;
                case 'd' :
                    plants[i] = new Deltafa(name,health);
                    break;
                case 'b' :
                    plants[i] = new Parabokor(name,health);
                    break;
            }
        }
    }

    // days
    int days;
    f >> days;
    return days;
}

// Change between manual and test mode
#define NORMAL_MODE
#ifdef NORMAL_MODE

int main()
{
    vector<Plant*> plants;
    vector<Emission*> emissions;

    Planet planet;

    int days = create("input.txt", plants);

    emissions.push_back(noEmission::instance());

    cout<<"--DAY 1"<<" / "<<days<<" --"<<endl;
    for(int j = 0;j < plants.size();j++){
        cout<<plants[j]->getName()<<" "<<plants[j]->getHealth()<<endl;
    }
    cout<<"noEmission"<<endl;


    int j;
    for(int i = 1;i < days;i++){
        cout<<"--DAY "<<i+1<<" / "<<days<<" --"<<endl;
        emissions.push_back(planet.simulateDay(plants,emissions[i-1]));
        for(j = 0;j < plants.size();j++){
            /*if(plants[j]->isAlive()){
                cout<<plants[j]->getName()<<" "<<plants[j]->getHealth()<<endl;
            }*/
            cout<<plants[j]->getName()<<" "<<plants[j]->type()<<" "<<plants[j]->getHealth()<<endl;
        }
        if(emissions[i] == noEmission::instance()){
            cout<<"noEmission"<<endl;
        }
        else if(emissions[i] == Delta::instance()){
            cout<<"Delta"<<endl;
        }
        else{
            cout<<"Alpha"<<endl;
        }
        //
        if(emissions[i] != noEmission::instance() && emissions[i-1] != noEmission::instance()){
            break;
        }
    }

    /*for(int i = 0;i < emissions.size()-1;i++){
        if(emissions[i] == noEmission::instance() && emissions[i+1] == noEmission::instance()){
            cout<<i+1<<","<<i+2<<endl;
            break;
        }
    }*/

    return 0;
}

#else
#define CATCH_CONFIG_MAIN
#include "catch.hpp"

TEST_CASE("4 correct", "input.txt")
{
    vector<Plant*> plants;
    vector<Emission*> emissions;

    Planet planet;

    int days = create("input.txt", plants);

    emissions.push_back(noEmission::instance());

    days = 10;

    for(int i = 1;i < days;i++){
        emissions.push_back(planet.simulateDay(plants,emissions[i-1]));
    }

    int yes = 0;

    for(int i = 0;i < emissions.size()-1;i++){
        if(emissions[i] == noEmission::instance() && emissions[i+1] == noEmission::instance() ){
            yes++;
        }
    }

    CHECK(yes == 4);
    CHECK(emissions[9] == noEmission::instance());
}

TEST_CASE("2 correct", "input.txt")
{
    vector<Plant*> plants;
    vector<Emission*> emissions;

    Planet planet;

    int days = create("input.txt", plants);

    emissions.push_back(noEmission::instance());

    days = 3;

    for(int i = 1;i < days;i++){
        emissions.push_back(planet.simulateDay(plants,emissions[i-1]));
    }

    int yes = 0;

    for(int i = 0;i < emissions.size()-1;i++){
        if(emissions[i] == noEmission::instance() && emissions[i+1] == noEmission::instance() ){
            yes++;
        }
    }

    CHECK(yes == 2);
    CHECK(emissions[1] == noEmission::instance());
}

TEST_CASE("0 plant", "inp2.txt")
{
    vector<Plant*> plants;
    vector<Emission*> emissions;

    Planet planet;

    int days = create("inp2.txt", plants);

    emissions.push_back(noEmission::instance());

    days = 10;

    for(int i = 1;i < days;i++){
        emissions.push_back(planet.simulateDay(plants,emissions[i-1]));
    }

    CHECK(plants.size() == 0);
    CHECK(emissions.size() == 10);
}

TEST_CASE("1 plant", "inp1.txt")
{
    vector<Plant*> plants;
    vector<Emission*> emissions;

    Planet planet;

    int days = create("inp1.txt", plants);

    emissions.push_back(noEmission::instance());

    days = 3;

    for(int i = 1;i < days;i++){
        emissions.push_back(planet.simulateDay(plants,emissions[i-1]));
    }

    CHECK(plants.size() == 1);
    CHECK(emissions[1] == Alpha::instance());
}

TEST_CASE("no correct", "inp3.txt")
{
    vector<Plant*> plants;
    vector<Emission*> emissions;

    Planet planet;

    int days = create("inp3.txt", plants);

    emissions.push_back(noEmission::instance());

    days = 2;

    for(int i = 1;i < days;i++){
        emissions.push_back(planet.simulateDay(plants,emissions[i-1]));
    }

    int yes = 0;

    for(int i = 0;i < emissions.size()-1;i++){
        if(emissions[i] == noEmission::instance() && emissions[i+1] == noEmission::instance() ){
            yes++;
        }
    }

    CHECK(plants.size() == 3);
    CHECK(yes == 0);
}

TEST_CASE("first emission", "input.txt")
{
    vector<Plant*> plants;
    vector<Emission*> emissions;

    Planet planet;

    int days = create("input.txt", plants);

    emissions.push_back(noEmission::instance());

    days = 2;

    for(int i = 1;i < days;i++){
        emissions.push_back(planet.simulateDay(plants,emissions[i-1]));
    }

    CHECK(emissions[0] == noEmission::instance());
}


#endif // NORMAL_MODE
