//seleccionamos todos los inputs
const inputs = document.querySelectorAll(".input-field");

//seleccionamos los links que cambiaran entre formularios
const toggle_btn = document.querySelectorAll(".toggle");

//seleccionamos el tag main
const main = document.querySelector("main");

const bullets = document.querySelectorAll(".bullets span");

const images = document.querySelectorAll(".image");

//cuando los inputs esten en focus, se activaran, y si no, se desactivaran
inputs.forEach((inp) => {
  inp.addEventListener("focus", () => {
    inp.classList.add("active");
  });
  inp.addEventListener("blur", () => {
    if (inp.value != "") return;
    inp.classList.remove("active");
  });
});
toggle_btn.forEach((btn) => {
  btn.addEventListener("click", () => {
    main.classList.toggle("sign-up-mode");
  });
});

function moveSlider() {
    let index = this.dataset.value;

  let currentImage = document.querySelector(`.img-${index}`);
  images.forEach((img) => img.classList.remove("show"));
  currentImage.classList.add("show");

  const textSlider = document.querySelector(".text-group");
  textSlider.style.transform = `translateY(${-(index - 1) * 2.2}rem)`;

  bullets.forEach((bull) => bull.classList.remove("active"));
  this.classList.add("active");
}
bullets.forEach((bullet) => {
  bullet.addEventListener("click", moveSlider);
});