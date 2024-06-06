document.querySelectorAll('.delete-btn').forEach(function (button) {
  button.addEventListener('click', function () {
    const row = this.closest('tr');
    const productId = row.getAttribute('productId');
    row.remove();
    var id = button.getAttribute("data-id");
    var cartItemId = button.getAttribute("data-cart-item-id");
    var url = `/cart/${id}/${cartItemId}/delete`;
    window.location.href = url;
  });
});