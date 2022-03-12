'use strict';

/** Processing when loading the screen */
jQuery(function($){

  /** Update button processing */
  $('#btn-br-book').click(function (event) {
    // User update
    addBr();
  });
});

/** User update processing */
function addBr() {

  // Get the value of the form
  var formData = $('#book-detail-form').serializeArray();

  // ajax communication
  $.ajax({
    type : "POST",
    cache : false,
    url : '/user/add',
    data: formData,
    dataType : 'json',
  }).done(function(data) {
	console.log(data);
      if(data.result === 10) {
     	alert('Mượn sách thất bại\n->Độc giả chưa trả sách này');
	} else{
      alert('Mượn sách thành công');
      window.location.href = '/book/list';
    }

  }).fail(function(jqXHR, textStatus, errorThrown) {
    // ajax failed
    alert('Mượn sách thất bại');
  }).always(function() {
    // Process to always execute
  });
}