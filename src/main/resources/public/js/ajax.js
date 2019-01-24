$(document).ready(function () {

	var baseUrl = window.location.protocol + "//" + window.location.host + "/";
	
    $('#addItem').on( "click", function(e) {

        e.preventDefault();
        
        var btnText = $(this).text();
        var itemName = $('#item-name').val();

        console.log("add-item - item-name: " + itemName + ", btnText: " + btnText);
        
        //add story
        if (btnText.includes("Story")) {

            //ajax call
            var storyJson =  {"storyName": itemName};
            console.log(storyJson);

            $.ajax({
                type: 'PUT',
                url: '/story/create',
                contentType: 'application/json',
                data: JSON.stringify(storyJson),
                dataType: 'json'

            }).done (function(data) {

                console.log(data);
                	
                $('.modal-add').removeClass('open');
                
                var newStory = $('#storyItem-0').clone();
                $(newStory).removeClass('not-display');
               
                
                $(newStory).find('.item--content').empty();
                $(newStory).find('.item--title').text(data.storyName);
                $(newStory).find('.item--id').text(data.id);
                $(newStory).find('.created-time').text(data.createdAgo);
                $(newStory).find('.story-item-label').attr('for', 'story-' + data.id);
                $(newStory).find('.story-item-checkbox').attr('id', 'story-' + data.id);
                $(newStory).attr('id', 'storyItem-' + data.id)
                $(newStory).find('.open-share-modal').attr('data-url', baseUrl + 'view?type=story&id=' + data.id);
                $(newStory).find('.icon-zoom').attr('href', '/overview/' + data.id);
                console.log(newStory);
                
                $('.accordion').prepend(newStory);
                


            }).fail (function(err) {
                console.error(err);
            });


        }
        //add chapter
        else if (btnText.includes("Chapter")) {

        	var storyId = $('#addId').text();
        	var trigId = $('#trigId').text();
        	
        	
        	//ajax call
            var chapterJson =  {'chapterName': itemName, 'storyId': storyId};
            console.log(JSON.stringify(chapterJson) + ', trigId: ' + trigId);

            $.ajax({
                type: 'PUT',
                url: '/chapter/create',
                contentType: 'application/json',
                data: JSON.stringify(chapterJson),
                dataType: 'json'

            }).done (function(data) {

                console.log(data);
                	
                $('.modal-add').removeClass('open');
                
                var pageName = $('#pageName').text();
                if (pageName == 'overview') {
                	
                	var newChapter = $('#chapter-0').clone();
                	$(newChapter).removeClass('not-display');
                	$(newChapter).attr('id', 'chapter-' + data.id);
                	$(newChapter).find('.chapter-id').text(data.id);
                	$(newChapter).find('.chapter-name').text(data.chapterName);
                	$(newChapter).find('.chapter-name').text(data.chapterName);
                	$(newChapter).find('.pages').empty();
                	
                	$('.block-whitebg--content').append(newChapter);
                	
                }
                else if (pageName == 'stories') { 
                	var newChapter = $('#chapterItem-0-0').clone();
                	$(newChapter).removeClass('not-display');
	                $(newChapter).find('.item--content').empty();
	                $(newChapter).find('.item--title').text(data.chapterName);
	                $(newChapter).find('.item--id').text(data.id);
	                $(newChapter).find('.chapter-item-label').attr('for', 'chapter-' + storyId + '-' + data.id);
	                $(newChapter).find('.chapter-item-checkbox').attr('id', 'chapter-' + storyId + '-' + data.id);
	                $(newChapter).attr('id', 'chapterItem-' + storyId + '-' + data.id)
	                $(newChapter).find('.open-share-modal').attr('data-url', baseUrl + 'view?type=chapter&id=' + data.id);
	                $(newChapter).find('.icon-zoom').attr('href', '/overview/' + data.id + '?type=chapter');
	                console.log(newChapter);
	                
	                var chapters = $('#' + trigId).find('.item--content:first');
	                $(chapters).append(newChapter);
	
	                
	                /*$(chapters).find('.chapter-item').each(function(index) {
	
	                    $(this).find('.chapter-item-label').attr('for', 'chapter-' + storyId + '-' + (index + 1));
	
	                    $(this).find('.chapter-item-checkbox').attr('id', 'chapter-' + storyId + '-' + (index + 1));
	                });*/
                }

            }).fail (function(err) {
                console.error(err);
            });
        	
        }
        //add page
        else if (btnText.includes("Page")) {

        	var chapterId = $('#addId').text();
        	var trigId = $('#trigId').text();
        	
        	
        	//ajax call
            var pageJson =  {'title': itemName, 'chapterId': chapterId};
            console.log(JSON.stringify(pageJson) + ', trigId: ' + trigId);

            $.ajax({
                type: 'PUT',
                url: '/page/create',
                contentType: 'application/json',
                data: JSON.stringify(pageJson),
                dataType: 'json'

            }).done (function(data) {

                console.log(data);
                	
                $('.modal-add').removeClass('open');
                
                var pageName = $('#pageName').text();
                if (pageName == 'overview') {
                	var newPage = $('#page-0').clone();
                	$(newPage).removeClass('not-display');
                	//var newPage = $('.page-item:first').clone();
                	$(newPage).find('.excerpt-page--inner').empty();
                	$(newPage).find('.excerpt-page--foot').text(data.title);
	                $(newPage).find('.page-id').text(data.id);
	                $(newPage).attr('id', 'page-' + data.id);
	                
	                var pages = $('#chapter-' + chapterId).find('.pages');
	                $(pages).append(newPage);
                	
                }
                else if (pageName == 'stories') { 
                
	                var storyId = $('#' + trigId).parents('.story-item:first').find('.item--id:first').text();
	                console.log("Add page: storyId: " + storyId);
	                
	                var newPage = $('#pageItem-0-0-0').clone();
                	$(newPage).removeClass('not-display');
	                //var newPage = $('.page-item:first').clone();
	                $(newPage).find('.item--content').empty();
	                $(newPage).find('.item--title').text(data.title);
	                $(newPage).find('.item--id').text(data.id);
	                $(newPage).find('.page-item-label').attr('for', 'page-' + storyId + '-' + chapterId + '-' + data.id);
	                $(newPage).find('.page-item-checkbox').attr('id', 'page-' + storyId + '-' + chapterId + '-' + data.id);
	                $(newPage).attr('id', 'pageItem-' + storyId + '-' + chapterId + '-' + data.id)
	                console.log(newPage);
	                
	                var pages = $('#' + trigId).find('.item--content:first');
	                $(pages).append(newPage);
	
	                
	                /*$(chapters).find('.page-item').each(function(index) {
	
	                    $(this).find('.page-item-label').attr('for', 'page-' + storyId + '-' + chapterId + '-' + (index + 1));
	
	                    $(this).find('.page-item-checkbox').attr('id', 'page-' + storyId + '-' + chapterId + '-' + (index + 1));
	                });*/

                }
            }).fail (function(err) {
                console.error(err);
            });
        	
        }
    });
    
    $('#addPageContent').on( "click", function(e) {
    	
    	e.preventDefault();
    	
    	var chapterId = $('#addId').text();
    	var itemName = $('#item-name').val();
    	
    	//ajax call
        var pageJson =  {'title': itemName, 'chapterId': chapterId};
        console.log(JSON.stringify(pageJson));

        $.ajax({
            type: 'PUT',
            url: '/page/create',
            contentType: 'application/json',
            data: JSON.stringify(pageJson),
            dataType: 'json'

        }).done (function(data) {

            console.log(data);
            	
            $('.modal-add').removeClass('open');
            
            window.location.href = "/create-page/" + data.id;


        }).fail (function(err) {
            console.error(err);
        });
    	
    });
    
    $('#editPageContent').on( "click", function(e) {
    	
    	e.preventDefault();
    	
    	var id = $('#editId').text();
    	var itemName = $('#edit-item-name').val();
    	
    	//ajax call
        var pageJson =  {'title': itemName, 'id': id};
        console.log(JSON.stringify(pageJson));

        $.ajax({
            type: 'POST',
            url: '/page/edit',
            contentType: 'application/json',
            data: JSON.stringify(pageJson),
            dataType: 'json'

        }).done (function(data) {

            console.log(data);
            	
            $('.modal-edit').removeClass('open');
            
            window.location.href = "/create-page/" + data.id;


        }).fail (function(err) {
            console.error(err);
        });
    	
    });
    
    
    $('#editItem').on( "click", function(e) {

        e.preventDefault();
        
        var btnText = $(this).text();
        var itemName = $('#edit-item-name').val();
        var id = $('#editId').text();

        console.log("edit-item - item-name: " + itemName + ", btnText: " + btnText);
        
        //edit story
        if (btnText.includes("Story")) {

            //ajax call
            var storyJson =  {"id": id, "storyName": itemName};
            console.log(storyJson);

            $.ajax({
                type: 'POST',
                url: '/story/edit',
                contentType: 'application/json',
                data: JSON.stringify(storyJson),
                dataType: 'json'

            }).done (function(data) {

                console.log(data);
                	
                $('.modal-edit').removeClass('open');
                
                $('#storyItem-' + id).find('.item--title:first').text(itemName);


            }).fail (function(err) {
                console.error(err);
            });


        }
        //edit chapter
        else if (btnText.includes("Chapter")) {

        	/*var storyId = $('#addId').text();*/
        	var trigId = $('#trigId').text();
        	
        	
        	//ajax call
            var chapterJson =  {'chapterName': itemName, 'id': id};
            console.log(JSON.stringify(chapterJson));

            $.ajax({
                type: 'POST',
                url: '/chapter/edit',
                contentType: 'application/json',
                data: JSON.stringify(chapterJson),
                dataType: 'json'

            }).done (function(data) {

                console.log(data);
                	
                $('.modal-edit').removeClass('open');
                
                var storyId = $('#' + trigId).find('.item--id:first').text();
                
                $('#chapterItem-' + storyId + '-' + id).find('.item--title:first').text(itemName);

            }).fail (function(err) {
                console.error(err);
            });
        	
        }
        //edit page
        else if (btnText.includes("Page")) {

        	var trigId = $('#trigId').text();
        	
        	//ajax call
            var pageJson =  {'title': itemName, 'id': id};
            console.log(JSON.stringify(pageJson) + ', trigId: ' + trigId);

            $.ajax({
                type: 'POST',
                url: '/page/edit',
                contentType: 'application/json',
                data: JSON.stringify(pageJson),
                dataType: 'json'

            }).done (function(data) {

                console.log(data);
                	
                $('.modal-edit').removeClass('open');
                
                var pageName = $('#pageName').text();
                if (pageName == 'overview') {
                	$('#page-' + data.id).find('.excerpt-page--foot').text(data.title);
                }
                else if (pageName == 'stories') {
	                var chapterId = $('#' + trigId).find('.item--id:first').text();
	                var storyId = $('#' + trigId).parents('.story-item:first').find('.item--id:first').text();
	                
	                console.log('chapterId: ' + chapterId);
	                console.log('storyId: ' + storyId);
	                
	                $('#pageItem-' + storyId + '-' + chapterId + '-' + id).find('.item--title:first').text(itemName);
	
	                console.log($('#pageItem-' + storyId + '-' + chapterId + '-' + id).find('.item--title:first').text());
                }
                
            }).fail (function(err) {
                console.error(err);
            });
        	
        }
    });
    
    $('#deleteItem').on( "click", function(e) {

        e.preventDefault();
        
        var itemName = $(this).parents('.modal-inner').find('.item-name:first').text();
        var id = $('#deleteId').text();

        console.log("delete-item - item-id: " + id + ", itemName: " + itemName);
        
        //delete user
        if (itemName == "User") {
        	//ajax call
            var storyJson =  {"id": id};
            console.log(storyJson);

            $.ajax({
                type: 'POST',
                url: '/admin/delete',
                contentType: 'application/json',
                data: JSON.stringify(storyJson)

            }).done (function(data) {

                console.log(data);
                	
                $('.modal-delete').removeClass('open');
                
                $('#user-' + id).remove();


            }).fail (function(err) {
                console.error(err);
            });
        }
        
        //delete story
        if (itemName == "Story") {

            //ajax call
            var storyJson =  {"id": id};
            console.log(storyJson);

            $.ajax({
                type: 'POST',
                url: '/story/delete',
                contentType: 'application/json',
                data: JSON.stringify(storyJson)

            }).done (function(data) {

                console.log(data);
                	
                $('.modal-delete').removeClass('open');
                
                $('#storyItem-' + id).remove();


            }).fail (function(err) {
                console.error(err);
            });


        }
        //delete chapter
        else if (itemName == "Chapter") {

        	var trigId = $('#trigId').text();
        	
        	
        	//ajax call
            var chapterJson =  {'id': id};
            console.log(JSON.stringify(chapterJson));

            $.ajax({
                type: 'POST',
                url: '/chapter/delete',
                contentType: 'application/json',
                data: JSON.stringify(chapterJson)

            }).done (function(data) {

                console.log(data);
                	
                $('.modal-delete').removeClass('open');
                
                var storyId = $('#' + trigId).find('.item--id:first').text();
                
                $('#chapterItem-' + storyId + '-' + id).remove();

            }).fail (function(err) {
                console.error(err);
            });
        	
        }
        //delete page
        else if (itemName == "Page") {

        	var trigId = $('#trigId').text();
        	
        	//ajax call
            var pageJson =  {'id': id};
            console.log(JSON.stringify(pageJson));
            
            var pageName = $('#pageName').text();
            if (pageName == 'overview') {
            	$.ajax({
	                type: 'POST',
	                url: '/page/delete',
	                contentType: 'application/json',
	                data: JSON.stringify(pageJson)
	
	            }).done (function(data) {
	
	                console.log(data);
	                	
	                $('.modal-delete').removeClass('open');
	                
	                $('#page-' + id).remove();
	                
	
	            }).fail (function(err) {
	                console.error(err);
	            });
            	
            }
            else if (pageName == 'create-page') {
            	$.ajax({
	                type: 'POST',
	                url: '/page/delete-getnext',
	                contentType: 'application/json',
	                data: JSON.stringify(pageJson)
	
	            }).done (function(data) {
	
	                console.log(data);
	                	
	                $('.modal-delete').removeClass('open');
	                
	                console.log(data);
	                
	                location.href = "/create-page/" + data.id;
	
	
	            }).fail (function(err) {
	                console.error(err);
	            });
            }
            else if (pageName == 'stories') {
	            $.ajax({
	                type: 'POST',
	                url: '/page/delete',
	                contentType: 'application/json',
	                data: JSON.stringify(pageJson)
	
	            }).done (function(data) {
	
	                console.log(data);
	                	
	                $('.modal-delete').removeClass('open');
	                
	                var chapterId = $('#' + trigId).find('.item--id:first').text();
	                var storyId = $('#' + trigId).parents('.story-item:first').find('.item--id:first').text();
	                
	                console.log('chapterId: ' + chapterId);
	                console.log('storyId: ' + storyId);
	                
	                $('#pageItem-' + storyId + '-' + chapterId + '-' + id).remove();
	
	
	            }).fail (function(err) {
	                console.error(err);
	            });
            }
        	
        }
    });
    
    $('#copyLink').on( "click", function(e) {
    	
    	var link = $('#itemLink').val();
    	document.execCommand("copy");
    	
    	var $temp = $("<input>");
	    $("body").append($temp);
	    $temp.val($('#itemLink').val()).select();
	    document.execCommand("copy");
	    $temp.remove();
	    
	    $(this).text('Copied!');
	    //$(this).addClass('success-text');
	    
	    setTimeout(function(){
	    	$('.modal-share').removeClass('open');
    	}, 800);
	    
    });
    
    $('#saveTitle').on('click', function (e) {
		e.preventDefault();

		var id = $('#newPageId').text();
		var title = $('#page-title').val();
		
        var pageJson =  {"id": id, "title": title};
        console.log(pageJson);

        $.ajax({
            type: 'POST',
            url: '/page/edit',
            contentType: 'application/json',
            data: JSON.stringify(pageJson),
            dataType: 'json'

        }).done (function(data) {

            console.log("Page title saved successfully: " + data);
            $('#createPageTitle').text(title);
            
            $('.modal-new-page').removeClass('open');

        }).fail (function(err) {
            console.error(err);
        });
    })
    
    $('#updatePassword').on('click', function (e) {
		e.preventDefault();

		var id = $('#updatePasswordId').text();
		var password = $('#newPassword').val();
		
        var userJson =  {"id": id, "password": password};
        console.log(userJson);

        $.ajax({
            type: 'POST',
            url: '/admin/edit',
            contentType: 'application/json',
            data: JSON.stringify(userJson),
            dataType: 'json'

        }).done (function(data) {

            console.log("Password updated successfully: " + data);
            
            $('.modal-update-password').removeClass('open');

        }).fail (function(err) {
            console.error(err);
        });
    })
    

});