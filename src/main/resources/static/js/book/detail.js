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
  var formData = $('#book-detail-form').serializeArray();

  // ajax communication
  $.ajax({
    type : "PUT",
    cache : false,
    url : '/book/update',
    data: formData,
    dataType : 'json',
  }).done(function(data) {
    // ajax success
    alert('Updated book successfully');
    // Redirect to user list screen
    window.location.href = '/book/detail/'+formData[0].value;

  }).fail(function(jqXHR, textStatus, errorThrown) {
    // ajax failed
    alert('Failed to update book');
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
	if (data==1){
		alert('Delete book ' + formData[1].value + ' successfully');
		window.location.href = '/book/list';
	}else {
		alert('Failed to delete Book\nBecause there are user not returned books');
	}
    
    // Redirect to user list screen
    

  }).fail(function(jqXHR, textStatus, errorThrown) {
    // ajax failed
    alert('Failed to delete book');

  }).always(function() {
    // Process to always execute
  });
}