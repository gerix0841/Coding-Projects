#pragma once

#include "nameEnor.h"
#include <string>

using namespace std;

struct tenperYear{
	int yearWithTen;
	bool atleastTen;
};

inline ostream& operator<<(ostream& os, const tenperYear& e){
	os << e.yearWithTen << " " << (e.atleastTen?"Igen":"Nem");
	return os;
}

class yearEnor{
	private:
		nameEnor _tt;
		tenperYear _cur;
		bool _end;
	public:
		yearEnor(const string &str): _tt(str) { };
		void first() { _tt.first(); next(); }
		void next();
		tenperYear current() const { return _cur; }
		bool end() const { return _end; }
};

void yearEnor::next(){
	_end = _tt.end();
	if ( !_end ){
		_cur.yearWithTen = _tt.current().year;
		_cur.atleastTen = false;
		int counter = 0;
		while ( !_tt.end() && _cur.yearWithTen == _tt.current().year ){
			if (_tt.current().haveHighjump){
				counter++;
			}
			if(counter >= 10){
                _cur.atleastTen = true;
			}
			_tt.next();
		}
	}
}
