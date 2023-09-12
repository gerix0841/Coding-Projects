#pragma once
#include <string>
#include <ostream>
#include <stdlib.h>

#include "Emission.h"

using namespace std;

class Puffancs;
class Deltafa;
class Parabokor;

class Planet
{
    public:
        Planet() : _radiation(noEmission::instance()),_aReq(0),_dReq(0) { }
        ~Planet() {}

        Emission* getRadiation() {return _radiation;}
        int reqAlpha() {return _aReq;}
        int reqDelta() {return _dReq;}
        Emission* calcRad(int aReq,int dReq);
        Emission* simulateDay(vector<Plant*> plants,Emission* &emission);

    private:
        Emission* _radiation;
        int _aReq;
        int _dReq;
};

Emission* Planet::calcRad(int aReq,int dReq)
{
    int x = aReq - dReq;

    if(x >= 3){
        return Alpha::instance();
    }
    else if(x <= -3){
        return Delta::instance();
    }
    else{
        return noEmission::instance();
    }
}

Emission* Planet::simulateDay(vector<Plant*> plants,Emission* &emission)
{
    _aReq = 0;
    _dReq = 0;
    for(int i = 0;i < plants.size();i++){
        //emission->cross(plants[i]);
        if(plants[i]->isAlive()){
            plants[i]->cross(emission);
        }

        if(plants[i]->isAlive()){
            if(plants[i]->type() == "Puffancs"){
                _aReq += 10;
            }
            else if(plants[i]->type() == "Deltafa"){
                if(plants[i]->getHealth() < 5){
                    _dReq += 4;
                }
                else if(plants[i]->getHealth() >= 5 && plants[i]->getHealth() <= 10){
                    _dReq += 1;
                }
            }
        }
    }

    return(calcRad(_aReq,_dReq));
}

