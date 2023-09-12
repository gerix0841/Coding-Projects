#pragma once
#include <string>

class Puffancs;
class Deltafa;
class Parabokor;

using namespace std;

/// class of abstract Emission
class Emission
{
    public:
        virtual void cross(Puffancs* p) = 0;
        virtual void cross(Deltafa* p) = 0;
        virtual void cross(Parabokor* p) = 0;

        virtual ~Emission() {}
};

/// class of Alpha
class Alpha : public Emission
{
    public:
        static Alpha* instance();
        void cross(Puffancs* p) override;
        void cross(Deltafa* p) override;
        void cross(Parabokor* p) override;

        static void destroy() ;

    private:
        static Alpha* _instance;
};

/// class of NoEmission
class noEmission : public Emission
{
    public:
        static noEmission* instance();
        void cross(Puffancs* p) override;
        void cross(Deltafa* p) override;
        void cross(Parabokor* p) override;

        static void destroy() ;

    private:
        static noEmission* _instance;
};

/// class of Delta
class Delta : public Emission
{
    public:
        static Delta* instance();
        void cross(Puffancs* p) override;
        void cross(Deltafa* p) override;
        void cross(Parabokor* p) override;

        static void destroy() ;

    private:
        static Delta* _instance;
};
