import "./App.css";
import { Holidays } from "./Holidays";
import countries2 from "./assets/countries.json";
import holidayList from "./assets/holidays.json"
import { useEffect, useState } from "react";

function App() {
  const [selectedHoliday, setSelectedHoliday] = useState(holidayList.filter((p) => (p.countryCode === 'AD')));
  const [countries, setCountries] = useState(countries2);

  const handleClick = (id) => {
    setSelectedHoliday(holidayList.filter((p) => (p.countryCode === id)));
    
  };

  return (
    <>
      <div>
        <table>
          <thead>
            <tr>
              <th>Name</th>
            </tr>
          </thead>
          <tbody>
            {countries.map((country) => (
              <tr key={country.countryCode}>
                <td>
                  <a href="#" onClick={() => {handleClick(country.countryCode)}}>{country.name} ({country.countryCode})</a>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <div>
        <Holidays selectedHoliday={selectedHoliday}/>
      </div>
    </>
  );
}

export default App;
