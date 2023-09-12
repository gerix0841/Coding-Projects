function veletlenKozott(alja, teteje) {
  return Math.floor(Math.random() * (teteje - alja) + alja);
}

const tablazatsor = document.querySelector("table tr");
const cellak = document.querySelector("#cellak");
const hatar = document.querySelector("#hatar");
const abrazolGomb = document.querySelector("#abrazol");
const szamolGomb = document.querySelector("#szamol");

// ========================================================
//a.
abrazolGomb.addEventListener("click",click);

function click(e){
  let cellaszam = cellak.value;
  tablazatsor.innerHTML = "";

  for(let j = 0;j < cellaszam;j++){
    let td = tablazatsor.insertCell();
    td.appendChild(document.createTextNode("0"));
  }     
}

//b.
szamolGomb.addEventListener("click",click2);

function click2(e){
  const tablazatcellak = document.querySelectorAll("td");
  tablazatcellak.forEach(event => {
    event.innerHTML = parseInt(event.innerHTML) + veletlenKozott(1, hatar.value-1);
  })

  //c.
  let max = parseInt(tablazatcellak[0].innerHTML);
  tablazatcellak.forEach(event => {
    if(parseInt(event.innerHTML) > max){
      max = parseInt(event.innerHTML);
    }
  })
  console.log(max);

  //d.
  tablazatcellak.forEach(event => {
    let világosság = (parseInt(event.innerHTML) * 100 / max);
    event.style.backgroundColor = `hsl(10, 70%, ${világosság}%)`;
  })

}

