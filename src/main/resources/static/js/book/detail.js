'use strict';

/** Processing when loading the screen */
jQuery(function($){

  /** Update button processing */
  $('#btn-update-book').click(function (event) {
    // User update
    updateBook();
  });

  /** Delete button processing */
  $('#btn-delete-book').click(function (event) {
    // User delete
    deleteBook();
  });
});

/** User update processing */
function updateBook() {

  // Get the value of the form
  var formData = $('#form-update-book').serializeArray();

  // ajax communication
  $.ajax({
    type : "PUT",
    cache : false,
    url : '/book/update',
    data: formData,
    dataType : 'json',
  }).done(function(data) {
    if(data.result === 90) {
	
      $.each(data.errors, function (key, value) {
	
        reflectValidResult(key, value)
      });

    } else{
      alert('Cập nhật sách thành công');
      window.location.href = '/book/detail/'+formData[0].value;
    }

  }).fail(function(jqXHR, textStatus, errorThrown) {
    // ajax failed
    alert('Cập nhật sách thất bại');
  }).always(function() {
    // Process to always execute
  });
}

/** User delete processing */
function deleteBook() {

  // Get the value of the form
  var formData = $('#book-detail-form').serializeArray();

  // ajax communication
  $.ajax({
    type : "DELETE",
    cache : false,
    url : '/book/delete',
    data: formData,
    dataType : 'json',
  }).done(function(data) {
	if (data===0){
		alert('Xóa sách '+ formData[1].value +' thành công.');
		window.location.href = '/book/list';
	}else {
		alert('Xóa sách thất bại\n-> Có người dùng chưa trả sách');
	}
    
    // Redirect to user list screen
    

  }).fail(function(jqXHR, textStatus, errorThrown) {
    // ajax failed
    alert('Xóa sách thất bại');

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