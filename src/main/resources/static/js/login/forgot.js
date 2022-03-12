'use strict';

/** Processing when loading the screen */
jQuery(function($){

  /** Update button processing */
  $('#btn-search').click(function (event) {
    // User update
    forgot();
  });
});

function forgot() {
	removeValidResult();
  var formData = $('#form-forgot').serializeArray();
  $.ajax({
    type : "PUT",
    cache : false,
    url : '/forgot',
    data: formData,
    dataType : 'json',
  }).done(function(data) {
    if(data.result === 90) {
	
      $.each(data.errors, function (key, value) {
	
        reflectValidResult(key, value)
      });

    }else if(data.result === 10){
		alert('Không tìm thấy email');
	}else {
		alert('Mật khẩu mới của bạn là: "password"');
		window.location.href = '/login';
	}
  }).fail(function(jqXHR, textStatus, errorThrown) {
    alert('Tìm kiếm thất bại');
  }).always(function() {});
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