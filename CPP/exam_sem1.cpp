#include <iostream>

//Szab? Gerg? ASPP08

using namespace std;

void beolvasas(int &n,int R[100]);

void feladat(int n,const int R[100]);

int main()
{
    int n;
    int R[100];
    int olvas;
    beolvasas(n,R);
    feladat(n,R);
    while(1){
        cerr<<"Ujra olvasas = 1(igen)/0(nem)"<<endl; cin>>olvas;
        if(olvas == 0){
            break;
        }
        else if(olvas == 1){
            beolvasas(n,R);
            feladat(n,R);
        }
        else{
            cout<<"Hibas bemenet\n";
        }
    }

    return 0;
}

void beolvasas(int &n,int R[100])
{
    string tmp;
    bool hiba;
    do{
        cout<<"Napok szama: ";cin>>n;
        hiba = (cin.fail() || n < 2);
        if(hiba){
            cout<<"Hibas adat\n";
            cin.clear();
            getline(cin,tmp);
        }
    }while(hiba);

    for(int i = 0;i < n;i++){
        do{
            cout<<i+1<<"."<<" napi zaras: ";cin>>R[i];
            hiba = (cin.fail() || R[i] < 0 || R[i] > 1000);
            if(hiba){
                cout<<"Hibas adat\n";
                cin.clear();
                getline(cin,tmp);
            }
        }while(hiba);
    }
}

void feladat(int n,const int R[100])
{
    bool van;
    int ind;

    int mk = 0;
    double mke = abs(R[0] - R[1]);
    for(int i = 1;i < (n - 1);i++){
        if(abs(R[i] - R[i+1]) > mke){
            mke = abs(R[i] - R[i+1]);
            mk = i;
        }
    }
    cout<<mk<<" alkalommal volt csokkenes"<<endl;
    cout<<"atlag: "<<mke<<endl;


    int i = 1;
    while((i <= n) && !(R[i] > (2 * R[0]))){
        i++;
    }
    van = (i <= n);
    if(van){
        ind = i;
        cout<<"Volt megfelelo nap: "<<ind+1<<endl;
    }
    else{
        cout<<"Nem volt megfelelo nap"<<endl;
    }
}