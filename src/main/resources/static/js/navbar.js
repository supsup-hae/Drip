const navEl = document.querySelector('.navbar');

window.addEventListener('scroll', function () {
  if (window.scrollY >= 84) {
    navEl.classList.add('navbar-scrolled');
  } else {
    navEl.classList.remove('navbar-scrolled')
  }
});

