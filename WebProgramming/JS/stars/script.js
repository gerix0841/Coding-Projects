document.querySelectorAll('div').forEach(e =>{
    e.innerHTML += '<span class="stars"></span>';
});

let maxsizes = [];
let values = [];
let count = 0;
document.querySelectorAll('input').forEach(e =>{
    maxsizes[count] = e.max;
    values[count] = e.value;
    count++;
});


/*
let count2 = 0;
document.querySelectorAll('span').forEach(e =>{
    for(let i = 0;i < maxsizes[count2];i++){
        e.innerHTML += '<span>★</span>';
    }
    count2++;
});
*/

let count3 = 0;
document.querySelectorAll('span').forEach(e =>{
    for(let i = 0;i < maxsizes[count3];i++){
        if(i < values[count3]){
            e.innerHTML += '<span>★</span>';
        }
        else{
            e.innerHTML += '<span>☆</span>';
        }
    }
    count3++;
});

document.querySelectorAll('input').forEach(e =>{
    e.style.display = 'none';
});
