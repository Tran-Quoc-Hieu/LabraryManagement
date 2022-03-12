'use strict';

/** Processing when loading the screen */
jQuery(function($){

  /** Update button processing */
  $('#btn-update-br').click(function (event) {
    // User update
    updateBr();
  });

  /** Delete button processing */
  $('#btn-delete-br').click(function (event) {
    // User delete
    deleteBr();
  });
});

/** User update processing */
function updateBr() {

  // Get the value of the form
  var formData = $('#br-detail-form').serializeArray();

  // ajax communication
  $.ajax({
    type : "PUT",
    cache : false,
    url : '/br/update',
    data: formData,
    dataType : 'json',
  }).done(function(data) {
    // ajax success
    alert('Trả sách thành công');
    // Redirect to user list screen
    window.location.href = '/br/detail/readerId='+formData[0].value+'&bookId='+formData[1].value;

  }).fail(function(jqXHR, textStatus, errorThrown) {
    // ajax failed
    alert('Trả sách thất bại');
  }).always(function() {
    // Process to always execute
  });
}


/** User delete processing */
function deleteBr() {

  // Get the value of the form
  var formData = $('#br-detail-form').serializeArray();

  // ajax communication
  $.ajax({
    type : "DELETE",
    cache : false,
    url : '/br/delete',
    data: formData,
    dataType : 'json',
  }).done(function(data) {
	if (data == 1) {
		alert('Xóa lịch sử thất bại\n-> Độc giả chưa trả sách');
	}else {
		alert('Xóa lịch sử thành công');
		window.location.href = '/br/list';
	}
  }).fail(function(jqXHR, textStatus, errorThrown) {
    // ajax failed
    alert('Xóa lịch sử thất bại');

  }).always(function() {
    // Process to always execute
  });
}