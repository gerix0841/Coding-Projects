#pragma once

#include <fstream>
#include <sstream>
#include <string>

using namespace std;

struct Contestant{
    string name;
    int year;
    int golds;
    bool atleastThree;
    bool haveHighjump;
};

inline ostream& operator<<(ostream& os, const Contestant& e){
	os << e.name << " " << e.year << " " << e.golds << " " << (e.atleastThree?"Igen":"Nem") << " " << (e.haveHighjump?"Igen":"Nem");
	return os;
}

struct Result{
    string raceName;
    int place;
};

class nameEnor
{
	private:
		std::ifstream _f;
		Contestant _cur;
		bool _end;
	public:
		enum FileError{MissingInputFile};
		nameEnor(const string &str);
		void first() { next(); }
		void next();
		Contestant current() const { return _cur; }
		bool end() const { return _end; }
};

nameEnor::nameEnor(const string &str)
{
	_f.open(str);
	if(_f.fail()){
		throw MissingInputFile;
	}
}

void nameEnor::next(){
	string line;
	string nameHelper;
	getline(_f, line);
	_end = _f.fail();
	if(!_end){
		istringstream is(line);

		is >> _cur.name;
		is >> nameHelper;
		_cur.name = _cur.name + " " + nameHelper;

		while(is >> nameHelper){
            if(!isdigit(nameHelper[0])){
                _cur.name = _cur.name + " " + nameHelper;
            }
            else{
                _cur.year = stoi(nameHelper);
                break;
            }
		}

		_cur.atleastThree = false;
		_cur.haveHighjump = false;
		_cur.golds = 0;
		Result tmp;
		while (is >> tmp.raceName >> tmp.place){
			if (tmp.place == 1){
				_cur.golds++;
			}
            if(_cur.golds >= 3){
                _cur.atleastThree = true;
            }
            if(tmp.raceName == "magasugras"){
                _cur.haveHighjump = true;
            }
		}
	}
}
