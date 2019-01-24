$(document).ready(function () {
	
	var id = $('#pageId').text();
	
    var pageJson =  {"id": id};
    console.log(pageJson);

    $.ajax({
        type: 'POST',
        url: '/page/has-prev',
        contentType: 'application/json',
        data: JSON.stringify(pageJson)

    }).done (function(data) {

        if (!data) {
        	$('#prevBtn').addClass('not-active');
        }

    }).fail (function(err) {
        console.error(err);
    });
	
});