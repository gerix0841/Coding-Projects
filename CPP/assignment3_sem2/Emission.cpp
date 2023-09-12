#include "Emission.h"
#include "Plant.h"

using namespace std;

/// implementation of class Alpha
Alpha* Alpha::_instance = nullptr;
Alpha* Alpha::instance()
{
    if(_instance == nullptr) {
        _instance = new Alpha();
    }
    return _instance;
}

void Alpha::destroy()
{
    if ( nullptr!=_instance )
    {
        delete _instance;
        _instance = nullptr;
    }
}

void Alpha::cross(Puffancs *p)
{
    p->addHealth(2);
}

void Alpha::cross(Deltafa *p)
{
    p->useHealth(3);
}

void Alpha::cross(Parabokor *p)
{
    p->addHealth(1);
}

/// implementation of class noEmission
noEmission* noEmission::_instance = nullptr;
noEmission* noEmission::instance()
{
    if(_instance == nullptr) {
        _instance = new noEmission();
    }
    return _instance;
}

void noEmission::destroy()
{
    if ( nullptr!=_instance )
    {
        delete _instance;
        _instance = nullptr;
    }
}

void noEmission::cross(Puffancs *p)
{
    p->useHealth(1);
}

void noEmission::cross(Deltafa *p)
{
    p->useHealth(1);
}

void noEmission::cross(Parabokor *p)
{
    p->useHealth(1);
}


/// implementation of class Delta
Delta* Delta::_instance = nullptr;
Delta* Delta::instance()
{
    if(_instance == nullptr) {
        _instance = new Delta();
    }
    return _instance;
}

void Delta::destroy()
{
    if ( nullptr!=_instance )
    {
        delete _instance;
        _instance = nullptr;
    }
}

void Delta::cross(Puffancs *p)
{
    p->useHealth(2);
}

void Delta::cross(Deltafa *p)
{
    p->addHealth(4);
}

void Delta::cross(Parabokor *p)
{
    p->addHealth(1);
}

