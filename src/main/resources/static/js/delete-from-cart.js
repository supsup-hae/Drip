document.querySelectorAll('.delete-btn').forEach(function (button) {
  button.addEventListener('click', function () {
    const row = this.closest('tr');
    const productId = row.getAttribute('productId');
    row.remove();
  });
});