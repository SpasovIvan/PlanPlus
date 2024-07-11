document.addEventListener("DOMContentLoaded", function() {
    const menuToggle = document.querySelector('.menu-toggle');
    const menuClose = document.querySelector('.menu-close');
    const navbar = document.querySelector('.navbar');

    menuToggle.addEventListener('click', () => {
        navbar.classList.toggle('open');
        document.body.classList.toggle('menu-open');
    });

    menuClose.addEventListener('click', () => {
        navbar.classList.toggle('open');
        document.body.classList.toggle('menu-open');
    });
})
