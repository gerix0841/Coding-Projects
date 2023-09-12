/*
  Készítette: Szabó Gergõ
  Neptun: ASPP08
  E-mail: gerix0841@gmail.com
  Feladat: „ProgAlap beadandó feladatok” téma „Madárfajok egyedei több mint 90%-ban egy helységben” feladat
*/

#include <iostream>

using namespace std;

void beolvas(int &n,int &m,double x[400][400]);
void szamolas(int n,int m,const double x[400][400]);

int main()
{
    int n,m;
    double x[400][400];
    beolvas(n,m,x);
    szamolas(n,m,x);

    return 0;
}

void beolvas(int &n,int &m,double x[400][400])
{
    bool hiba;
    string tmp;

    do{
        cerr<<"m,n= ";cin>>m>>n;
        hiba = (cin.fail() || m < 1 || m > 50 || n < 1 || n > 200);
        if(hiba){
            cout<<"Hibas adat\n";
            cin.clear();
            getline(cin,tmp);
        }
    }while(hiba);

    for(int i = 0;i < m;i++){
        for(int j = 0;j < n;j++){
            do{
                cerr<<"x["<<i+1<<"]["<<j+1<<"]= ";cin>>x[i][j];
                hiba = (cin.fail() || x[i][j] < 0 || x[i][j] > 1000);
                if(hiba){
                    cout<<"Hibas adat\n";
                    cin.clear();
                    getline(cin,tmp);
                }
            }while(hiba);
        }
    }
}

void szamolas(int n,int m,const double x[400][400])
{
    double db[400];
    double sum = 0;

    for(int i = 0;i < n;i++){
        db[i] = 0;
    }

    for(int i = 0;i < n;i++){
        for(int j = 0;j < m;j++){
            db[i] = db[i] + x[j][i];
        }
    }

    for(int i = 0;i < n;i++){
        for(int j = 0;j < m;j++){
            if(((x[j][i] / db[i]) * 100) > 90){
                sum++;
            }
        }
    }
    cout<<sum<<endl;

    for(int i = 0;i < n;i++){
        for(int j = 0;j < m;j++){
            if(((x[j][i] / db[i]) * 100) > 90){
                cout<<i+1<<" ";
            }
        }
    }
}
