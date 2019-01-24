$(function() {
    $('.text-editor').froalaEditor({
        /*theme: 'gray',
        zIndex: 2001,*/
        /*theme: 'dark',
        zIndex: 2003,*/
        theme: 'custom',
        charCounterCount: false,
        toolbarButtons: ['bold', 'italic', 'underline', 'strikeThrough', '|', 'fontSize',
            'color', '|', 'align', 'outdent', 'indent', 'insertLink', 'insertImage', 'embedly',
            '|', 'emoticons     ', 'specialCharacters', 'insertHR', 'selectAll', '|', 'spellChecker', 'undo', 'redo'],
        quickInsertTags: [],
        placeholderText: '',
        heightMin: 500,
        imageUploadURL: '/image/upload',
        imageMaxSize: 5 * 1024 * 1024,
        imageUploadMethod: 'POST',
    	// Set the load images request URL.
        imageManagerLoadURL: "http://localhost:8080/",
 
        // Set the load images request type.
        imageManagerLoadMethod: "GET",
        /*imageUploadParams: {id: 'my_editor'}*/
    })
    .on('froalaEditor.contentChanged', function (e, editor) {
		console.log("Page content changed");

		var pageContent = $(this).find('.fr-wrapper').html();
		var id = $('#pageId').text();
		
        var pageJson =  {"id": id, "pageContent": pageContent};
        console.log(pageJson);

        $.ajax({
            type: 'POST',
            url: '/page/edit',
            contentType: 'application/json',
            data: JSON.stringify(pageJson),
            dataType: 'json'

        }).done (function(data) {

            //console.log("page " + data.id + " was updated");

        }).fail (function(err) {
            console.error(err);
        });
    })
    .on('froalaEditor.image.uploaded', function (e, editor, response) {
    	console.log("response: " + response);
    	// Parse response to get image url.
    	var responseObj = $.parseJSON( response );
    	console.log("responseObj: " + responseObj);
        //var imgUrl = 'http://localhost:8080/create-page/' + responseObj.link;
        //console.log("imgUrl: " + imgUrl);
        
        // Insert image.
        editor.image.insert(responseObj.link, false, null, editor.image.get(), response);
        console.log("image inserted");
        return false;
     })
    .on('froalaEditor.image.error', function (e, editor, error, response) {
        // Bad link.
        if (error.code == 1) {
        	console.log(error);
        	console.log("response: " + response);
        }
 
        // No link in upload response.
        else if (error.code == 2) { 
        	console.log(error);
        }
 
        // Error during image upload.
        else if (error.code == 3) { 
        	console.log(error);
        }
 
        // Parsing response failed.
        else if (error.code == 4) { 
        	console.log(error);
        	console.log("response: " + response);
        }
 
        // Image too text-large.
        else if (error.code == 5) {
        	console.log(error);
        }
 
        // Invalid image type.
        else if (error.code == 6) {
        	console.log(error);
        }
 
        // Image can be uploaded only to same domain in IE 8 and IE 9.
        else if (error.code == 7) {
        	console.log(error);
        }
 
        // Response contains the original server response to the request if available.
      });

		
});

/*$(document).ready(function () {
	$('.text-editor').on('froalaEditor.contentChanged', function (e, editor) {
		console.log("Page content changed");

		var pageContent = $(this).find('.fr-view').html();
		var id = $('#pageId').text();
		
        var pageJson =  {"id": id, "pageContent": pageContent};
        console.log(pageJson);

        $.ajax({
            type: 'POST',
            url: '/page/edit',
            contentType: 'application/json',
            data: JSON.stringify(pageJson),
            dataType: 'json'

        }).done (function(data) {

            //console.log("page " + data.id + " was updated");

        }).fail (function(err) {
            console.error(err);
        });

		
	});
});*/