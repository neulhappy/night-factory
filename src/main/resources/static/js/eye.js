const wrap = document.querySelector('.outer_eye');
const eye = document.querySelector('.eye');

const speed = 1;
let x = 0;
let y = 0;
let followX = 0;
let followY = 0;

function animate() {
    x += (followX - x) * speed;
    y += (followY - y) * speed;
    eye.style.transform=`translate(${-x}px, ${-y}px)`;

    requestAnimationFrame(animate);
}

window.addEventListener('mousemove',(e)=>{
    let mouseX = Math.max(-100, Math.min(100, window.innerWidth/2 - e.clientX));
    let mouseY = Math.max(-100, Math.min(100,window.innerHeight/2 - e.clientY));

    followX = (12 * mouseX) / 100;
    followY = (10 * mouseY) / 100;
});

animate();