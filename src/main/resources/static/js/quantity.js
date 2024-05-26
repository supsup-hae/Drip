// Define function to increment value
function incrementValue(e) {
  e.preventDefault();
  var button = e.target;
  var fieldName = button.getAttribute('data-field');
  var parent = button.closest('.input-group');
  var input = parent.querySelector('input[name=' + fieldName + ']');
  var currentVal = parseInt(input.value, 10);

  if (!isNaN(currentVal)) {
    input.value = currentVal + 1;
  } else {
    input.value = 0;
  }
}

// Define function to decrement value
function decrementValue(e) {
  e.preventDefault();
  var button = e.target;
  var fieldName = button.getAttribute('data-field');
  var parent = button.closest('.input-group');
  var input = parent.querySelector('input[name=' + fieldName + ']');
  var currentVal = parseInt(input.value, 10);

  if (!isNaN(currentVal) && currentVal > 0) {
    input.value = currentVal - 1;
  } else {
    input.value = 0;
  }
}

// Attach click event listener for button-plus class
document.querySelectorAll('.input-group .button-plus').forEach(function(button) {
  button.addEventListener('click', incrementValue);
});

// Attach click event listener for button-minus class
document.querySelectorAll('.input-group .button-minus').forEach(function(button) {
  button.addEventListener('click', decrementValue);
});
