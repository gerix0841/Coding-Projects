import { useState } from "react";
import edit from "../../img/edit.png";
import remove from "../../img/remove.png";
import good from "../../img/good.png";
import bad from "../../img/bad.png";
import action from "../../img/action.png";
import empty from "../../img/empty.png";
import runall from "../../img/runall.png";

const Field = ({ label, error, ...rest }) => {
    return (
      <div className={`field ${error ? "error" : ""}`}>
        <label>{label}</label>
        <input {...rest} />
      </div>
    );
  };
  
const defaultState = {
    name: "",
    a: "",
    b: "",
    output: "",
};

export function FunctionCustomTester({fn, customTest}){
    const [customTests, setCustomTests] = useState(
        customTest.map((test) => ({
          ...test,
          result: null
        }))
    );
    const [formState,setFormState] = useState(defaultState);
    const [testToEdit, setTestToEdit] = useState(null);
    const [formErrors, setFormErrors] = useState([]);

    const handleRunCustomTest = (index) => {
        const result = fn(customTests[index].input) === customTests[index].output;
        setCustomTests((prevCustomTests) => {
          const updatedTests = [...prevCustomTests];
          updatedTests[index].result = result;
          return updatedTests;
        });
    };

    const runAllCustomTests = () => {
        customTests.forEach((_,index) => handleRunCustomTest(index));
    };

    const handleDelete = (id) => {
        setCustomTests((prevCustomTests) =>
          prevCustomTests.filter((test) => test.id !== id));
    };

    const handleEdit = (test) => {
        setFormState({
          name: test.name,
          a: test.input.a.toString(),
          b: test.input.b.toString(),
          output: test.output.toString()
        });
        setTestToEdit(test);
    };

    const handleChange = e => {
        const {name,value} = e.target;
        setFormState({...formState,[name]: value});
    };

    const handleSubmit = (test) => {
        test.preventDefault();
        
        const errors = [];
        if(!formState.name){
            errors.push("Name is required!");
        }
        if(!formState.a){
            errors.push("A is required!");
        }
        if(!formState.b){
            errors.push("B is required!");
        }
        if(!formState.output){
            errors.push("Output is required!");
        }

        if(errors.length > 0){
            setFormErrors(errors);
            return;
        }

        if(testToEdit){
            const updatedTest = {
            ...testToEdit,
            name: formState.name,
            input: {
                a: parseInt(formState.a),
                b: parseInt(formState.b)
            },
            output: parseInt(formState.output)
            };

            setCustomTests((prevCustomTests) => prevCustomTests.map((test) => test.id === updatedTest.id ? updatedTest : test));
            setTestToEdit(null);
        }
        else{
            const newTest = {
            id: customTests.length,
            name: formState.name,
            input: {
                a: parseInt(formState.a),
                b: parseInt(formState.b)
            },
            output: parseInt(formState.output),
            result: null
            };
            setCustomTests((prevCustomTests) => [...prevCustomTests, newTest]);
        }

        setFormState(defaultState);
        setFormErrors([]);

        const inputElements = document.querySelectorAll(".field input");
        inputElements.forEach((input) => {
            input.blur();
        });
    };

    return(
        <>
            <h2>Custom Tests</h2>
            <table>
            <thead>
            <tr>
                <th></th>
                <th>Name</th>
                <th>Result</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            {customTests.map((e,index) => (
            <tr key={index}>
                <td>{e.id}</td>
                <td>{e.name}</td>
                <td>{e.result === null ? <img src={empty}/> : (e.result ? <img src={good}/> : <img src={bad}/>)}</td>
                <td>
                <img src={action} onClick={() => handleRunCustomTest(index)}/>
                <img src={edit} onClick={() => handleEdit(e)}/>
                <img src={remove} onClick={() => handleDelete(e.id)}/>
                </td>
            </tr>
            ))}
            </tbody>
            <tfoot>
            <tr>
            <td></td>
            <td></td>
            <td></td>
            <td><img src={runall} onClick={runAllCustomTests}/></td>
            </tr>
            </tfoot>
        </table>

        <h2>New Custom Test</h2>
        <form test={testToEdit}>
            <Field label="Name: " placeholder="TestName" name="name" value={formState.name} onChange={handleChange} type="text" error={formErrors.includes("Name is required!")}/>
            <Field label="A: " placeholder="a" name="a" value={formState.a} onChange={handleChange} type="number" error={formErrors.includes("A is required!")}/>
            <Field label="B: " placeholder="b" name="b" value={formState.b} onChange={handleChange} type="number" error={formErrors.includes("B is required!")}/>
            <Field label="Output: " placeholder="output" name="output" value={formState.output} onChange={handleChange} type="number" error={formErrors.includes("Output is required!")}/>

            {formErrors.length > 0 && (
            <div className="error-list">
                <p>Errors to fix:</p>
                <ul>
                {formErrors.map((error,index) => (
                    <li key={index} onClick={() => {
                    const errorInput = document.querySelector(`.field input[name="${error.toLowerCase()}"]`);
                    if(errorInput){errorInput.focus()};
                    }}>
                    {error}
                    </li>
                ))}
                </ul>
            </div>
            )}

            <input type="submit" value={"Submit"} onClick={handleSubmit}/>
        </form>
        </>
    );
}