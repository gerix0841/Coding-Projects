import okbutton from "../../img/okbutton.png";
import { useState } from "react";

export function FunctionTesterOKButton({fn, tests, customTest, onFinish}){
    const handleFinish = () => {
        const testResults = tests.map((test) => ({
            name: test.name,
            result: test.testFn(fn)
        }));

        const sumPoints = testResults.reduce((sum, test, index) => {
            if (test.result) {
                return sum + tests[index].points;
            }
            return sum;
        }, 0);
      

        const testResult = {
            achieved: sumPoints,
            all: tests.reduce((total, test) => total + test.points, 0)
        }

        const customTests = customTest.map((test) => ({
            name: test.name,
            input: test.input,
            output: test.output,
            result: test.result === true
        }));

        onFinish({
            givenTests: testResults,
            testResult,
            customTests
        });
    };

    return(
        <>
            <button onClick={handleFinish}><img src={okbutton} /></button>
        </>
    );
}
