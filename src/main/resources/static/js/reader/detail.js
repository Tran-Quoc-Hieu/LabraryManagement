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

  // Get the value of the form
  var formData = $('#reader-detail-form').serializeArray();

  // ajax communication
  $.ajax({
    type : "PUT",
    cache : false,
    url : '/reader/update',
    data: formData,
    dataType : 'json',
  }).done(function(data) {
    // ajax success
    alert('Updated successfully');
    // Redirect to user list screen
    window.location.href = '/reader/detail/'+formData[0].value;

  }).fail(function(jqXHR, textStatus, errorThrown) {
    // ajax failed
    alert('Failed to update');
  }).always(function() {
    // Process to always execute
  });
}

/** User update processing */
function updatePasswordReader() {

  // Get the value of the form
  var formData = $('#reader-detail-form').serializeArray();

  // ajax communication
  $.ajax({
    type : "PUT",
    cache : false,
    url : '/reader/update/password',
    data: formData,
    dataType : 'json',
  }).done(function(data) {
    // ajax success
    alert('Updated successfully');
    // Redirect to user list screen
    window.location.href = '/reader/detail/'+formData[0].value;

  }).fail(function(jqXHR, textStatus, errorThrown) {
    // ajax failed
    alert('Failed to update');
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
		alert('Failed to delete reader\nBecause there are return books');
	}else {
		alert('Delete user successfully');
		window.location.href = '/reader/list';
	}
  }).fail(function(jqXHR, textStatus, errorThrown) {
    // ajax failed
    alert('Failed to delete reader');

  }).always(function() {
    // Process to always execute
  });
}