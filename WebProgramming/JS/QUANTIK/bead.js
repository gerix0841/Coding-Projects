//StartSite
const startbutton = document.querySelector("#startbutton");
const rulesbutton = document.querySelector("#rulesbutton");
const backbutton = document.querySelector("#backbutton");
const startsiteButton = document.querySelector("#startsiteButton");

const vegeredmenyek = document.querySelector("#vegeredmenyek");

const player1Inp = document.querySelector("#player1");
const player2Inp = document.querySelector("#player2");

const startsite = document.querySelector("#startsite");
const gamesite = document.querySelector("#gamesite");
const rulesite = document.querySelector("#rulesite");

const gametable = document.querySelector("#gametable");

const currentplayer = document.querySelector("#currentplayer");
const activeShapeP = document.querySelector("#activeShapeP");

//red shape
const redCButton = document.querySelector("#redCButton");
const redSButton = document.querySelector("#redSButton");
const redTButton = document.querySelector("#redTButton");
const redXButton = document.querySelector("#redXButton");

//red span
const redCSpan = document.querySelector("#redCSpan");
const redSSpan = document.querySelector("#redSSpan");
const redTSpan = document.querySelector("#redTSpan");
const redXSpan = document.querySelector("#redXSpan");

//green shape
const greenCButton = document.querySelector("#greenCButton");
const greenSButton = document.querySelector("#greenSButton");
const greenTButton = document.querySelector("#greenTButton");
const greenXButton = document.querySelector("#greenXButton");

//green span
const greenCSpan = document.querySelector("#greenCSpan");
const greenSSpan = document.querySelector("#greenSSpan");
const greenTSpan = document.querySelector("#greenTSpan");
const greenXSpan = document.querySelector("#greenXSpan");


//Basic setting
startbutton.addEventListener("click", click);
rulesbutton.addEventListener("click", click2);
backbutton.addEventListener("click", click3);
startsiteButton.addEventListener("click", click4);

redCButton.addEventListener("click", clickredCButton);
redSButton.addEventListener("click", clickredSButton);
redTButton.addEventListener("click", clickredTButton);
redXButton.addEventListener("click", clickredXButton);

greenCButton.addEventListener("click", clickgreenCButton);
greenSButton.addEventListener("click", clickgreenSButton);
greenTButton.addEventListener("click", clickgreenTButton);
greenXButton.addEventListener("click", clickgreenXButton);

gametable.addEventListener("click", play);

let counter = 0;
let player1Name;
let player2Name;

let zones = [];

//images
const greenCircle = new Image(); greenCircle.src = "res/greenCircle.png";
const greenSquare = new Image(); greenSquare.src = "res/greenSquare.png";
const greenTri = new Image(); greenTri.src = "res/greenTri.png";
const greenX = new Image(); greenX.src = "res/greenX.png";

const redCircle = new Image(); redCircle.src = "res/redCircle.png";
const redSquare = new Image(); redSquare.src = "res/redSquare.png";
const redTri = new Image(); redTri.src = "res/redTri.png";
const redX = new Image(); redX.src = "res/redX.png";

let greenCircleCount;
let greenSquareCount;
let greenTriCount;
let greenXCount;

let redCircleCount;
let redSquareCount;
let redTriCount;
let redXCount;

let activeShape;

function click(e){
    //setting name
    player1Name = player1Inp.value;
    player2Name = player2Inp.value;
    
    //starting game
    startsite.hidden = true;
    gamesite.hidden = false;

    //table
    gametable.innerHTML = "";
    tableCreate(4,4);
    currentplayer.innerHTML = "Soron lévő játékos: " + player1Name;
    activeShape = "<img src='" + redCircle.src + "'>";
    activeShapeP.innerHTML = "Jelenlegi forma: <img src='" + redCircle.src + "'>";
    activeShapeP.hidden = false;

    greenCSpan.innerHTML = 2;
    greenSSpan.innerHTML = 2;
    greenTSpan.innerHTML = 2;
    greenXSpan.innerHTML = 2;
    redCSpan.innerHTML = 2;
    redSSpan.innerHTML = 2;
    redTSpan.innerHTML = 2;
    redXSpan.innerHTML = 2;

    greenCircleCount = parseInt(greenCSpan.innerHTML);
    greenSquareCount = parseInt(greenSSpan.innerHTML);
    greenTriCount = parseInt(greenTSpan.innerHTML);
    greenXCount = parseInt(greenXSpan.innerHTML);
    redCircleCount = parseInt(redCSpan.innerHTML);
    redSquareCount = parseInt(redSSpan.innerHTML);
    redTriCount = parseInt(redTSpan.innerHTML);
    redXCount = parseInt(redXSpan.innerHTML);

    startsiteButton.hidden = true;

    counter = 0;
}

function click2(e){
    //rule site
    startsite.hidden = true;
    rulesite.hidden = false;
}

function click3(e){
    //start site
    rulesite.hidden = true;
    startsite.hidden = false;
}

function click4(e){
    //start site
    startsite.hidden = false;
    gamesite.hidden = true;
}

function tableCreate(row, col){
    for(let i = 0; i < row; i++){
        let tr = gametable.insertRow();
        for(let j = 0; j < col; j++){
                let td = tr.insertCell();
                td.appendChild(document.createTextNode(" "));
                if((i === 2 && j === 0) || (i === 3 && j === 0) || (i === 2 && j === 1) || (i === 3 && j === 1) || (i === 0 && j === 2) || (i === 0 && j === 3) || (i === 1 && j === 2) || (i === 1 && j === 3)){
                    td.classList.add("groupYellow");
                }
                else{
                    td.classList.add("groupGreen");
                }
            }     
    }
}

function isEmpty(e){
    return e.target.innerHTML === " ";
}

function play(e){
    if(e.target.matches("td")){
        //piros játékos
        if(isEmpty(e) && counter % 2 === 0 && counter < 16){
            if(activeShape === ("<img src='" + redCircle.src + "'>") && redCircleCount > 0){
                redCircleCount--;
                redCSpan.innerHTML = redCircleCount;
                if(activeTD(e.target)){
                    e.target.innerHTML = activeShape;
                    currentplayer.innerHTML = "Soron lévő játékos: " + player2Name;
                    counter++;
                }
                whoWon(player1Name);
            }
            else if(activeShape === ("<img src='" + redSquare.src + "'>") && redSquareCount > 0){
                redSquareCount--;
                redSSpan.innerHTML = redSquareCount;
                if(activeTD(e.target)){
                    e.target.innerHTML = activeShape;
                    currentplayer.innerHTML = "Soron lévő játékos: " + player2Name;
                    counter++;
                }
                whoWon(player1Name);
            }
            else if(activeShape === ("<img src='" + redTri.src + "'>") && redTriCount > 0){
                redTriCount--;
                redTSpan.innerHTML = redTriCount;
                if(activeTD(e.target)){
                    e.target.innerHTML = activeShape;
                    currentplayer.innerHTML = "Soron lévő játékos: " + player2Name;
                    counter++;
                }
                whoWon(player1Name);
            }
            else if(activeShape === ("<img src='" + redX.src + "'>") && redXCount > 0){
                redXCount--;
                redXSpan.innerHTML = redXCount;
                if(activeTD(e.target)){
                    e.target.innerHTML = activeShape;
                    currentplayer.innerHTML = "Soron lévő játékos: " + player2Name;
                    counter++;
                }
                whoWon(player1Name);
            }
        }
        //zöld játékos
        else if(isEmpty(e) && counter % 2 === 1 && counter < 16){
            if(activeShape === ("<img src='" + greenCircle.src + "'>") && greenCircleCount > 0){
                greenCircleCount--;
                greenCSpan.innerHTML = greenCircleCount;
                if(activeTD(e.target)){
                    e.target.innerHTML = activeShape;
                    currentplayer.innerHTML = "Soron lévő játékos: " + player1Name;
                    counter++;
                }
                whoWon(player2Name);
            }
            else if(activeShape === ("<img src='" + greenSquare.src + "'>") && greenSquareCount > 0){
                greenSquareCount--;
                greenSSpan.innerHTML = greenSquareCount;
                if(activeTD(e.target)){
                    e.target.innerHTML = activeShape;
                    currentplayer.innerHTML = "Soron lévő játékos: " + player1Name;
                    counter++;
                }
                whoWon(player2Name);
            }
            else if(activeShape === ("<img src='" + greenTri.src + "'>") && greenTriCount > 0){
                greenTriCount--;
                greenTSpan.innerHTML = greenTriCount;
                if(activeTD(e.target)){
                    e.target.innerHTML = activeShape;
                    currentplayer.innerHTML = "Soron lévő játékos: " + player1Name;
                    counter++;
                }
                whoWon(player2Name);
            }
            else if(activeShape === ("<img src='" + greenX.src + "'>") && greenXCount > 0){
                greenXCount--;
                greenXSpan.innerHTML = greenXCount;
                if(activeTD(e.target)){
                    e.target.innerHTML = activeShape;
                    currentplayer.innerHTML = "Soron lévő játékos: " + player1Name;
                    counter++;
                }
                whoWon(player2Name);
            }
        }
    }
}

//changing shapes
function clickredCButton(e){
    if(counter % 2 === 0 && redCircleCount > 0){
        activeShape = "<img src='" + redCircle.src + "'>";
        activeShapeP.innerHTML = "Jelenlegi forma: <img src='" + redCircle.src + "'>";
    }
}
function clickredSButton(e){
    if(counter % 2 === 0 && redSquareCount > 0){
        activeShape = "<img src='" + redSquare.src + "'>";
        activeShapeP.innerHTML = "Jelenlegi forma: <img src='" + redSquare.src + "'>";
    }
}
function clickredTButton(e){
    if(counter % 2 === 0 && redTriCount > 0){
        activeShape = "<img src='" + redTri.src + "'>";
        activeShapeP.innerHTML = "Jelenlegi forma: <img src='" + redTri.src + "'>";
    }
}
function clickredXButton(e){
    if(counter % 2 === 0 && redXCount > 0){
        activeShape = "<img src='" + redX.src + "'>";
        activeShapeP.innerHTML = "Jelenlegi forma: <img src='" + redX.src + "'>";
    }
}

function clickgreenCButton(e){
    if(counter % 2 === 1 && greenCircleCount > 0){
        activeShape = "<img src='" + greenCircle.src + "'>";
        activeShapeP.innerHTML = "Jelenlegi forma: <img src='" + greenCircle.src + "'>";
    }
}
function clickgreenSButton(e){
    if(counter % 2 === 1 && greenSquareCount > 0){
        activeShape = "<img src='" + greenSquare.src + "'>";
        activeShapeP.innerHTML = "Jelenlegi forma: <img src='" + greenSquare.src + "'>";
    }
}
function clickgreenTButton(e){
    if(counter % 2 === 1 && greenTriCount > 0){
        activeShape = "<img src='" + greenTri.src + "'>";
        activeShapeP.innerHTML = "Jelenlegi forma: <img src='" + greenTri.src + "'>";
    }
}
function clickgreenXButton(e){
    if(counter % 2 === 1 && greenXCount > 0){
        activeShape = "<img src='" + greenX.src + "'>";
        activeShapeP.innerHTML = "Jelenlegi forma: <img src='" + greenX.src + "'>";
    }
}

function activeTD(e){
    zones = [];
    let num = 0;
    let isactive = true;
    for(var r = 0, n = gametable.rows.length; r < n; r++) {
        for(var c = 0, m = gametable.rows[r].cells.length; c < m; c++) {
            zones[num] = gametable.rows[r].cells[c].innerHTML;
            num++;
        }
    }
    return true;
}

function whoWon(playerName){
    zones = [];
    let num = 0;

    for(var r = 0, n = gametable.rows.length; r < n; r++) {
        for(var c = 0, m = gametable.rows[r].cells.length; c < m; c++) {
            zones[num] = gametable.rows[r].cells[c].innerHTML;
            num++;
        }
    }

    //row
    if(zones[0] != " " && zones[1] != " " && zones[2] != " " && zones[3] != " "){
        redCircleCount = 0;redSquareCount = 0;redTriCount = 0;redXCount = 0;greenCircleCount = 0;greenSquareCount = 0;greenTriCount = 0;greenXCount = 0;
        alert(playerName + " nyert!");
        startsiteButton.hidden = false;
        activeShapeP.hidden = true;
        currentplayer.innerHTML = "Győztes: " + playerName;
        vegeredmenyek.innerHTML += "<li>Játékosok: " + player1Name + " vs " + player2Name + "  Győztes: " + playerName;
    }
    else if(zones[4] != " " && zones[5] != " " && zones[6] != " " && zones[7] != " "){
        redCircleCount = 0;redSquareCount = 0;redTriCount = 0;redXCount = 0;greenCircleCount = 0;greenSquareCount = 0;greenTriCount = 0;greenXCount = 0;
        alert(playerName + " nyert!");
        startsiteButton.hidden = false;
        activeShapeP.hidden = true;
        currentplayer.innerHTML = "Győztes: " + playerName;
        vegeredmenyek.innerHTML += "<li>Játékosok: " + player1Name + " vs " + player2Name + "  Győztes: " + playerName;
    }
    else if(zones[8] != " " && zones[9] != " " && zones[10] != " " && zones[11] != " "){
        redCircleCount = 0;redSquareCount = 0;redTriCount = 0;redXCount = 0;greenCircleCount = 0;greenSquareCount = 0;greenTriCount = 0;greenXCount = 0;
        alert(playerName + " nyert!");
        startsiteButton.hidden = false;
        activeShapeP.hidden = true;
        currentplayer.innerHTML = "Győztes: " + playerName;
        vegeredmenyek.innerHTML += "<li>Játékosok: " + player1Name + " vs " + player2Name + "  Győztes: " + playerName;
    }
    else if(zones[12] != " " && zones[13] != " " && zones[14] != " " && zones[15] != " "){
        redCircleCount = 0;redSquareCount = 0;redTriCount = 0;redXCount = 0;greenCircleCount = 0;greenSquareCount = 0;greenTriCount = 0;greenXCount = 0;
        alert(playerName + " nyert!");
        startsiteButton.hidden = false;
        activeShapeP.hidden = true;
        currentplayer.innerHTML = "Győztes: " + playerName;
        vegeredmenyek.innerHTML += "<li>Játékosok: " + player1Name + " vs " + player2Name + "  Győztes: " + playerName;
    }
    //col
    else if(zones[0] != " " && zones[4] != " " && zones[8] != " " && zones[12] != " "){
        redCircleCount = 0;redSquareCount = 0;redTriCount = 0;redXCount = 0;greenCircleCount = 0;greenSquareCount = 0;greenTriCount = 0;greenXCount = 0;
        alert(playerName + " nyert!");
        startsiteButton.hidden = false;
        activeShapeP.hidden = true;
        currentplayer.innerHTML = "Győztes: " + playerName;
        vegeredmenyek.innerHTML += "<li>Játékosok: " + player1Name + " vs " + player2Name + "  Győztes: " + playerName;
    }
    else if(zones[1] != " " && zones[5] != " " && zones[9] != " " && zones[13] != " "){
        redCircleCount = 0;redSquareCount = 0;redTriCount = 0;redXCount = 0;greenCircleCount = 0;greenSquareCount = 0;greenTriCount = 0;greenXCount = 0;
        alert(playerName + " nyert!");
        startsiteButton.hidden = false;
        activeShapeP.hidden = true;
        currentplayer.innerHTML = "Győztes: " + playerName;
        vegeredmenyek.innerHTML += "<li>Játékosok: " + player1Name + " vs " + player2Name + "  Győztes: " + playerName;
    }
    else if(zones[2] != " " && zones[6] != " " && zones[10] != " " && zones[14] != " "){
        redCircleCount = 0;redSquareCount = 0;redTriCount = 0;redXCount = 0;greenCircleCount = 0;greenSquareCount = 0;greenTriCount = 0;greenXCount = 0;
        alert(playerName + " nyert!");
        startsiteButton.hidden = false;
        activeShapeP.hidden = true;
        currentplayer.innerHTML = "Győztes: " + playerName;
        vegeredmenyek.innerHTML += "<li>Játékosok: " + player1Name + " vs " + player2Name + "  Győztes: " + playerName;
    }
    else if(zones[3] != " " && zones[7] != " " && zones[11] != " " && zones[15] != " "){
        redCircleCount = 0;redSquareCount = 0;redTriCount = 0;redXCount = 0;greenCircleCount = 0;greenSquareCount = 0;greenTriCount = 0;greenXCount = 0;
        alert(playerName + " nyert!");
        startsiteButton.hidden = false;
        activeShapeP.hidden = true;
        currentplayer.innerHTML = "Győztes: " + playerName;
        vegeredmenyek.innerHTML += "<li>Játékosok: " + player1Name + " vs " + player2Name + "  Győztes: " + playerName;
    }

    //zones
    else if(zones[0] != " " && zones[1] != " " && zones[4] != " " && zones[5] != " "){
        redCircleCount = 0;redSquareCount = 0;redTriCount = 0;redXCount = 0;greenCircleCount = 0;greenSquareCount = 0;greenTriCount = 0;greenXCount = 0;
        alert(playerName + " nyert!");
        startsiteButton.hidden = false;
        activeShapeP.hidden = true;
        currentplayer.innerHTML = "Győztes: " + playerName;
        vegeredmenyek.innerHTML += "<li>Játékosok: " + player1Name + " vs " + player2Name + "  Győztes: " + playerName;
    }
    else if(zones[2] != " " && zones[3] != " " && zones[6] != " " && zones[7] != " "){
        redCircleCount = 0;redSquareCount = 0;redTriCount = 0;redXCount = 0;greenCircleCount = 0;greenSquareCount = 0;greenTriCount = 0;greenXCount = 0;
        alert(playerName + " nyert!");
        startsiteButton.hidden = false;
        activeShapeP.hidden = true;
        currentplayer.innerHTML = "Győztes: " + playerName;
        vegeredmenyek.innerHTML += "<li>Játékosok: " + player1Name + " vs " + player2Name + "  Győztes: " + playerName;
    }
    else if(zones[8] != " " && zones[9] != " " && zones[12] != " " && zones[13] != " "){
        redCircleCount = 0;redSquareCount = 0;redTriCount = 0;redXCount = 0;greenCircleCount = 0;greenSquareCount = 0;greenTriCount = 0;greenXCount = 0;
        alert(playerName + " nyert!");
        startsiteButton.hidden = false;
        activeShapeP.hidden = true;
        currentplayer.innerHTML = "Győztes: " + playerName;
        vegeredmenyek.innerHTML += "<li>Játékosok: " + player1Name + " vs " + player2Name + "  Győztes: " + playerName;
    }
    else if(zones[10] != " " && zones[11] != " " && zones[14] != " " && zones[15] != " "){
        redCircleCount = 0;redSquareCount = 0;redTriCount = 0;redXCount = 0;greenCircleCount = 0;greenSquareCount = 0;greenTriCount = 0;greenXCount = 0;
        alert(playerName + " nyert!");
        startsiteButton.hidden = false;
        activeShapeP.hidden = true;
        currentplayer.innerHTML = "Győztes: " + playerName;
        vegeredmenyek.innerHTML += "<li>Játékosok: " + player1Name + " vs " + player2Name + "  Győztes: " + playerName;
    }

}


