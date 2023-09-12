import { FunctionTester } from "./function-tester/FunctionTester";
import { FunctionTesterTitle } from "./function-tester/FunctionTesterTitle";
import { FunctionCustomTester } from "./function-tester/FunctionCustomTester";
import { FunctionTesterOKButton } from "./function-tester/FunctionTesterOKButton";
// import json_data from "./stories/example-data/the-example";

function App() {
  const handleFinish = (result) => {
    console.log(result);
  };

  return (
    <>
    
    <FunctionTesterTitle fn={({ a, b }) => a + b}/>

    <FunctionTester
      fn={({ a, b }) => a + b}
      input={{ a: "number", b: "number" }}
      output={"number"}
      tests={[
        { name: "1 + 0", testFn: (fn) => fn({ a: 1, b: 0 }) === 1, points: 25 },
        { name: "0 + 10", testFn: (fn) => fn({ a: 0, b: 10 }) === 10, points: 25 },
        { name: "1 + -1", testFn: (fn) => fn({ a: 1, b: -1 }) === 0, points: 25 },
        { name: "10 + -20", testFn: (fn) => fn({ a: 10, b: -20 }) === -10, points: 25 },
      ]}
    />

    <FunctionCustomTester
      fn={({ a, b }) => a + b}
      customTest={[
        { "id" : 0 , "name": "test1", "input": { "a": 12, "b": 32 }, "output": 45,  "result": false },
        { "id" : 1 , "name": "test2", "input": { "a": 12, "b": 32 }, "output": 44,  "result": true }
      ]}
      />

    <FunctionTesterOKButton
      fn={({ a, b }) => a + b}
      tests={[
        { name: "1 + 0", testFn: (fn) => fn({ a: 1, b: 0 }) === 1, points: 25 },
        { name: "0 + 10", testFn: (fn) => fn({ a: 0, b: 10 }) === 10, points: 25 },
        { name: "1 + -1", testFn: (fn) => fn({ a: 1, b: -1 }) === 0, points: 25 },
        { name: "10 + -20", testFn: (fn) => fn({ a: 10, b: -20 }) === -10, points: 25 }
      ]}
      customTest={[
        { "id" : 0 , "name": "test1", "input": { "a": 12, "b": 32 }, "output": 45,  "result": false },
        { "id" : 1 , "name": "test2", "input": { "a": 12, "b": 32 }, "output": 44,  "result": true }
      ]}
      onFinish={handleFinish}
    />

    </>
  );
}

export default App;
