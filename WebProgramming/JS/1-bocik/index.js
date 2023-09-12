const tehenek = [
  {
      "fajta" : "Holstein-fríz",
      "szin" : "fekete-fehér",
      "nem" : "bika",
      "kor": 3
  },
  {
      "fajta" : "Magyar tarka",
      "szin" : "barna-fehér",
      "nem" : "tehén",
      "kor": 5
  },
  {
      "fajta" : "Angus marha",
      "szin" : "barna",
      "nem" : "tehén",
      "kor": 10
  },
  {
      "fajta" : "Angus marha",
      "szin" : "barna",
      "nem" : "bika",
      "kor": 2
  },
  {
      "fajta" : "Holstein-fríz",
      "szin" : "fekete-fehér",
      "nem" : "bika",
      "kor": 12
  },
  {
      "fajta" : "Holstein-fríz",
      "szin" : "fekete",
      "nem" : "tehén",
      "kor": 4
  },
  {
      "fajta" : "Magyar tarka",
      "szin" : "barna",
      "nem" : "bika",
      "kor": 13
  },
  {
      "fajta" : "Angus marha",
      "szin" : "barna",
      "nem" : "bika",
      "kor": 11
  },
  {
      "fajta" : "Angus marha",
      "szin" : "barna",
      "nem" : "bika",
      "kor": 1
  }
]

const task1 = document.querySelector('#task1')
const task2 = document.querySelector('#task2')
const task3 = document.querySelector('#task3')
const task4 = document.querySelector('#task4')

// Megoldás innen
function start(){
    //task1
    let task1Bool = true;
    tehenek.forEach(e => {
        if(e.kor < 3){
            task1Bool = false;
        }
    });
    task1.innerHTML = task1Bool;

    //task2
    let counter = 0;
    tehenek.forEach(e => {
        if(e.fajta === "Holstein-fríz"){
            counter++;
        }
    });
    task2.innerHTML = counter;

    //task3
    let max = tehenek[0].kor;
    tehenek.forEach(e => {
        if(e.kor > max){
            max = e.kor;
        }
    });
    task3.innerHTML = max;

    //task4
    let counter2 = 0;
    tehenek.forEach(e => {
        if(e.szin === "fekete-fehér" && e.nem === "tehén"){
            counter2++;
        }
    });
    if(counter2 > 0){
        task4.innerHTML = "true";
    }
    else{
        task4.innerHTML = "false";
    }
}

start();


