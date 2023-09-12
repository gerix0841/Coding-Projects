import { useEffect, useState } from "react";
import good from "../../img/good.png";
import bad from "../../img/bad.png";
import action from "../../img/action.png";
import empty from "../../img/empty.png";
import runall from "../../img/runall.png";

export function FunctionTester({ fn, input, output, tests }) {
  const [testResults, setTestResults] = useState(tests.map(() => null));
  const [sumPoints, setSumPoints] = useState(0);

  //console.log(fn);
  //console.log(input);
  //console.log(output);
  //console.log(tests);

  useEffect(() => {
    const summingPoints = tests.reduce((sum,test,currentIndex) => {
      if(testResults[currentIndex]){
        return sum + test.points;
      }
      return sum;
    },0);
    setSumPoints(summingPoints);
  },[testResults,tests]);

  const handleRunTest = (index) => {
    const result = tests[index].testFn(fn);
    setTestResults(prevResults => {
      const updatedResults = [...prevResults];
      updatedResults[index] = result;
      return updatedResults;
    });
  };

  const runAllTests = () => {
    tests.forEach((_,index) => handleRunTest(index));
  };

  return (

    <>
      <h2>Tests</h2>
      <table>
        <thead>
          <tr>
            <th>Name</th>
            <th>Result</th>
            <th>Action</th>
            <th>Points</th>
          </tr>
        </thead>
        <tbody>
        {tests.map((e,index) => (
          <tr key={index}>
            <td>{e.name.toString()}</td>
            <td>{testResults[index] === null ? <img src={empty}/> : (testResults[index] ? <img src={good}/> : <img src={bad}/>)}</td>
            <td>
              <img src={action} onClick={() => handleRunTest(index)}/>
            </td>
            <td>{testResults[index] === null ? 0 : (testResults[index] ? e.points : 0)}</td>
          </tr>
        ))}
        </tbody>
        <tfoot>
        <tr>
          <td></td>
          <td></td>
          <td><img src={runall} onClick={runAllTests}/></td>
          <td>Total:{sumPoints}</td>
        </tr>
        </tfoot>
      </table>
    </>
  );
}
