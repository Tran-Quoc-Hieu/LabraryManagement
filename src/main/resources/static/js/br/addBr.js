'use strict';

/** Processing when loading the screen */
jQuery(function($){

  /** Update button processing */
  $('#btn-add-br').click(function (event) {
    // User update
    addBr();
  });
});

/** User update processing */
function addBr() {

  // Get the value of the form
  var formData = $('#form-add-br').serializeArray();

  // ajax communication
  $.ajax({
    type : "POST",
    cache : false,
    url : '/br/add',
    data: formData,
    dataType : 'json',
  }).done(function(data) {
	console.log(data);
      if(data.result === 10) {
     	alert('Mượn sách thất bại\n->Độc giả chưa trả sách này');
	} else{
      alert('Mượn sách thành công');
      window.location.href = '/br/list';
    }

  }).fail(function(jqXHR, textStatus, errorThrown) {
    // ajax failed
    alert('Mượn sách thất bại');
  }).always(function() {
    // Process to always execute
  });
}