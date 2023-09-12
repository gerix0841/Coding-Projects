import { useState } from "react";

export const Holidays = ({selectedHoliday}) => {
  const [dateValue,setDateValue] = useState(2023);

  const handleChange = (dateVal) => {
    setDateValue(dateVal);
  };

  return (
    <>
      <a href="/">Back</a>
      <table>
        <thead>
          <tr>
            <th>Holidays</th>
            <th>
              <input type="number" value={dateValue}  onChange={(e) => handleChange(e.target.value)} />
            </th>
          </tr>
        </thead>
        <tbody>
          {selectedHoliday/*.filter(holiday => {holiday.date.substring(0,3) === dateValue})*/.map((holiday,index) => (
            <tr key={index}>
              <td>{holiday.date}</td>
              <td>{holiday.localName}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </>
  );
};
