import label from "../../img/label.png";

export function FunctionTesterTitle({fn}){
    return(
        <>
            <img src={label}/>
            <h2>Function</h2>
            {fn.toString()}
            <br/>
        </>
    );
}