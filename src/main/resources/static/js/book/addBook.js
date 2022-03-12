'use strict';

/** Processing when loading the screen */
jQuery(function($){

  /** Update button processing */
  $('#btn-add-book').click(function (event) {
    // User update
    addBook();
  });
});

/** User update processing */
function addBook() {

  // Get the value of the form
  var formData = $('#form-add-book').serializeArray();

  // ajax communication
  $.ajax({
    type : "POST",
    cache : false,
    url : '/book/add',
    data: formData,
    dataType : 'json',
  }).done(function(data) {
      if(data.result === 90) {
	
      $.each(data.errors, function (key, value) {
	
        reflectValidResult(key, value)
      });

    } else{
      alert('Thêm thành công');
      window.location.href = '/book/list';
    }

  }).fail(function(jqXHR, textStatus, errorThrown) {
    // ajax failed
    alert('Failed to update book');
  }).always(function() {
    // Process to always execute
  });
}

/** Clear validation results */
function removeValidResult() {
  $('.is-invalid').removeClass('is-invalid');
  $('.invalid-feedback').remove();
  $('.text-danger').remove();
}

/** Reflection of the validation result */
function reflectValidResult(key, value) {

  // Add error message
  if(key === 'gender') { // For gender fields
    // Apply CSS
    $('input[name=' + key + ']').addClass('is-invalid');
    // Add error message
    $('input[name=' + key + ']')
        .parent().parent()
        .append('<div class="text-danger">' + value + '</div>');

  } else { // For fields other than gender
    // Apply CSS
    $('input[id=' + key + ']').addClass('is-invalid');
    // Add error message
    $('input[id=' + key + ']')
        .after('<div class="invalid-feedback">' + value + '</div>');
  }
}