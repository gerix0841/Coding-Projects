const canvas = document.querySelector('canvas')
const ctx = canvas.getContext('2d')

let circles = [];

// ============= Előadásból kimásolva =================

let lastFrameTime = performance.now();

function next(currentTime = performance.now()) {
  const dt = (currentTime - lastFrameTime) / 1000; // seconds
  lastFrameTime = currentTime;

  update(dt); // Update current state
  render(); // Rerender the frame

  requestAnimationFrame(next);
}

next(); // Start the loop

function update(dt) {
  circles.forEach(circle => {
    circle.vy += circle.ay * dt;
    circle.y += circle.vy * dt;
    if(circle.y < 0 || circle.y > 400){
      circle.vy *= -1;
    }
  });
  console.log(circles)
}

function render() {
  circles.forEach(circle => {
    ctx.beginPath();
    ctx.arc(circle.x, circle.y, 25, 0, 2 * Math.PI);
    ctx.closePath();
    ctx.fill();
  });
}

canvas.addEventListener("click",click);

function click(e){
  circles.push(
    {
      x: e.offsetX,
      y: e.offsetY,
      r: 25,
      sAngle: 0,
      eAngle: 2 * Math.PI,
      vy: 0,
      ay: 250
    }
  );

  ctx.fillStyle = "#517d81";
  ctx.beginPath();
  ctx.arc(e.offsetX, e.offsetY, 25, 0, 2 * Math.PI);
  ctx.closePath();
  ctx.fill();
}

