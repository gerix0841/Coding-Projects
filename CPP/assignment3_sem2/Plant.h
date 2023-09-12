#pragma once
#include <string>
#include <ostream>
#include <stdlib.h>

#include "Emission.h"

using namespace std;

class Plant
{
    public:
        Plant(string name,int health) : _name(name), _health(health) { }
        string getName() {return _name;}
        int getHealth() {return _health;}
        void addHealth(int health) {_health = _health + health;}
        void useHealth(int health) {_health = _health - health;}
        virtual bool isAlive() {return false;}
        virtual string type() = 0;

        virtual void cross(Emission* emission) = 0;

        virtual ~Plant () {}

    protected:
        string _name;
        int _health;
};

class Puffancs : public Plant
{
    public:
        Puffancs(const string &name, int health) : Plant(name, health) { }
        bool isAlive() override {return (_health < 11 && _health > 0);}
        string type() override {return "Puffancs";}

        void cross(Emission* emission) override { return emission->cross(this); }
};

class Deltafa : public Plant
{
    public:
        Deltafa(const string &name, int health) : Plant(name, health) { }
        bool isAlive() override {return _health > 0;}
        string type() override {return "Deltafa";}

        void cross(Emission* emission) override { return emission->cross(this); }
};

class Parabokor : public Plant
{
    public:
        Parabokor(const string &name, int health) : Plant(name, health) { }
        bool isAlive() override {return _health > 0;}
        string type() override {return "Parabokor";}

        void cross(Emission* emission) override { return emission->cross(this); }
};
