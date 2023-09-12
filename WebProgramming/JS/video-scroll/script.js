const observer = new IntersectionObserver(entries => {
        entries.forEach(entry =>{
            if(entry.isIntersecting){
                entry.target.play();
            }
            else{
                entry.target.pause();
            }
        });
    },{ threshold: 0 }
);

document.querySelectorAll('video').forEach(element => observer.observe(element));
