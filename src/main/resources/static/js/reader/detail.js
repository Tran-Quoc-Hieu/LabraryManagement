'use strict';

/** Processing when loading the screen */
jQuery(function($){

  /** Update button processing */
  $('#btn-update-reader').click(function (event) {
    // User update
    updateReader();
  });
  
  /** Update Password button processing */
  $('#btn-update-reader-password').click(function (event) {
    // User update
    updatePasswordReader();
  });

  /** Delete button processing */
  $('#btn-delete-reader').click(function (event) {
    // User delete
    deleteReader();
  });
});

/** User update processing */
function updateReader() {
	removeValidResult();
  // Get the value of the form
  var formData = $('#form-update-user').serializeArray();

  // ajax communication
  $.ajax({
    type : "PUT",
    cache : false,
    url : '/reader/update',
    data: formData,
    dataType : 'json',
  }).done(function(data) {
	if(data.result === 90) {
	
      $.each(data.errors, function (key, value) {
	
        reflectValidResult(key, value)
      });
    }else if (data.result === 10) {
		alert('Email đã có người sử dụng');
	}else{
      alert('Cập nhật người dùng thành công');
      window.location.href = '/user/detail/'+formData[0].value;
    }

  }).fail(function(jqXHR, textStatus, errorThrown) {
    // ajax failed
    alert('Cập nhật thất bại');
  }).always(function() {
    // Process to always execute
  });
}

/** User update processing */
function updatePasswordReader() {
	removeValidResult();
  // Get the value of the form
  var formData = $('#form-update-password').serializeArray();

  // ajax communication
  $.ajax({
    type : "PUT",
    cache : false,
    url : '/reader/update/password',
    data: formData,
    dataType : 'json',
  }).done(function(data) {
	if(data.result === 90) {
	
      $.each(data.errors, function (key, value) {
	
        reflectValidResult(key, value)
      });
    } else{
      alert('Cập nhật mật khẩu thành công');
      window.location.href = '/user/detail/'+formData[0].value;
    }
  }).fail(function(jqXHR, textStatus, errorThrown) {
    // ajax failed
    alert('Cập nhật thất bại');
  }).always(function() {
    // Process to always execute
  });
}

/** User delete processing */
function deleteReader() {

  // Get the value of the form
  var formData = $('#reader-detail-form').serializeArray();

  // ajax communication
  $.ajax({
    type : "DELETE",
    cache : false,
    url : '/reader/delete',
    data: formData,
    dataType : 'json',
  }).done(function(data) {
	if (data == 1) {
		alert('Xóa người dùng không thành công\n-> Người dùng chưa trả sách');
	}else {
		alert('Xóa người dùng thành công');
		window.location.href = '/reader/list';
	}
  }).fail(function(jqXHR, textStatus, errorThrown) {
    // ajax failed
    alert('Xóa người dùng thất bại');

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