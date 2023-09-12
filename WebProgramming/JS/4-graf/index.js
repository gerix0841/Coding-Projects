function delegal(szulo, esemeny, szelektor, esemenykezelo) {
    szulo.addEventListener(esemeny, function (event) {
        const targetElement = event.target.closest(szelektor)

        if (this.contains(targetElement)) {
            esemenykezelo.call(targetElement, event)
        }
    })
}

const graf = document.querySelector("svg")
const pontok = document.querySelectorAll("ellipse")
const hibaBekezdes = document.querySelector("p")

// Megoldás innentől
let count = 0;

pontok.forEach(e => {
    e.addEventListener("click",(event)=>{
        if(count > 0){
            if(e.classList.contains("elerheto")){
                hibaBekezdes.innerHTML = "";
                const array = [...e.dataset.paths];
                pontok.forEach(ev => {
                    ev.classList.remove("aktualis");
                    ev.classList.remove("elerheto");
                    if(array.includes(ev.dataset.name)){
                        ev.classList.add("elerheto");
                    }
    
                });
                console.log(e.dataset.name + " -> " + e.dataset.paths);
                e.classList.add("aktualis");
            }
            else{
                hibaBekezdes.innerHTML = "Hiba!";
            }
            count++;
        }
        else{
            hibaBekezdes.innerHTML = "";
            const array = [...e.dataset.paths];
            pontok.forEach(ev => {
                ev.classList.remove("aktualis");
                ev.classList.remove("elerheto");
                if(array.includes(ev.dataset.name)){
                    ev.classList.add("elerheto");
                }

            });
            console.log(e.dataset.name + " -> " + e.dataset.paths);
            e.classList.add("aktualis");
            count++;
        }
    });
})

