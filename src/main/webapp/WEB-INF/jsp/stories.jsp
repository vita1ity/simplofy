<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="req" value="${pageContext.request}" />
<c:set var="url">${req.requestURL}</c:set>
<c:set var="uri" value="${req.requestURI}" />
<c:set var="baseUrl" value="${fn:substring(url, 0, fn:length(url) - fn:length(uri))}${req.contextPath}"/>


<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Simplofy - User Stories</title>

    <link rel="stylesheet" href="/css/style.css">

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script type="text/javascript" src="/js/scripts.min.js"></script>
	<script type="text/javascript" src="/js/ajax.js"></script>
	
	<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
	<link rel="icon" href="/favicon.ico" type="image/x-icon">
	
</head>
<body class="fixed-header">

<%@include file="header.jsp"%>


<main class="main-container">

<span id="pageName" class="not-display">stories</span>

<section class="section-main">
    <div class="wrap">
        <div class="block-stories">
            <div class="block-stories--header">
            
                <h2 class="block-stories--title">My Stories
                
                    <a href="" class="link-add-story open-add-modal" data-item="Story">

                        <div class="new-story">
                            <i class="icon-plus block-stories--icon"></i>
                            <span>New Story</span>
                        </div>
                    </a>
                </h2>
            </div>
            <div class="block-stories--content">

                <ol class="accordion">
                	<!-- stories -->
                	<c:forEach var="story" items="${userStories}" varStatus="storyStatus">
						
						<li id="storyItem-${story.id}" class="item story-item" >
	                        <span class="item--id">${story.id}</span>
	                        <div class="item--header">
	                            <div class="item--info item-center">
	                                <label for="story-${story.id}" class="story-item-label item--expend">
	                                    <i class="icon-arrow-right item--icon"></i>
	                                    <span class="item--title">${story.storyName}</span>
	                                </label>
	                                <span class="created-time">${story.createdAgo}</span>
	
	                            </div>
	                            <div class="item--links">
	                                <div class="item--expanded-links">
	                                    <a href="" class="link-add open-add-modal" data-item="Chapter">Add Chapter</a>
	                                    <a href="" class="icon-edit open-edit-modal" data-item="Story"></a>
	                                    <a href="/overview/${story.id}" class="icon-zoom"></a>
	                                    <a href="" class="icon-del open-delete-modal" data-item="Story"></a>
	
	                                </div>
	                                <a href="" class="icon-share item--links--last open-share-modal" data-item="Story"
	                                   data-url="${baseUrl}/view?type=story&id=${story.id}"></a>
	                            </div>
	
	                        </div>
	                        <input type="checkbox" class="story-item-checkbox" id="story-${story.id}" />
							
							<!-- chapters -->
							<ol class="item--content">
								
								<c:forEach var="chapter" items="${story.chapters}" varStatus="chapterStatus">
									<li id="chapterItem-${story.id}-${chapter.id}" class="item chapter-item">
										<span class="item--id">${chapter.id}</span>
		                                <div class="item--header item--sub">
		                                    <div class="item--info item-center">
		                                        <label for="chapter-${story.id}-${chapter.id}" class="chapter-item-label item--expend">
		                                            <i class="icon-arrow-right item--icon"></i>
		                                            <span class="item--title">${chapter.chapterName}</span>
		                                        </label>
		
		                                    </div>
		                                    <div class="item--links">
		                                        <div class="item--expanded-links">
		                                            <a href="" class="link-add add-page" data-item="Page">Add Page</a>
		                                            <a href="" class="icon-edit open-edit-modal" data-item="Chapter"></a>
		                                            <a href="/overview/${chapter.id}?type=chapter" class="icon-zoom"></a>
		                                            <a href="" class="icon-del open-delete-modal" data-item="Chapter"></a>
		                                            <a href="" class="icon-share item--links--last open-share-modal" data-item="Chapter"
		                                               data-url="${baseUrl}/view?type=chapter&id=${chapter.id}"></a>
		                                        </div>
		
		                                    </div>
		
		                                </div>
		                                <input type="checkbox" class="chapter-item-checkbox" 
		                                	id="chapter-${story.id}-${chapter.id}" />
		                                
		                                <!--pages-->
                                		<ol class="item--content">
                                		
                                			<c:forEach var="page" items="${chapter.pages}" varStatus="pageStatus">
                                				<li id="pageItem-${story.id}-${chapter.id}-${page.id}" 
                                					class="item page-item">
                                					<span class="item--id">${page.id}</span>
			                                        <div class="item--header item--sub--sub">
			                                            <div class="item--info item-center">
			                                                <label for="page-${story.id}-${chapter.id}-${page.id}" 
			                                                	class="page-item-label item--expend">
			                                                    <i class="icon-page item--icon"></i>
			                                                    <span class="item--title">${page.title}</span>
			                                                </label>
			
			                                            </div>
			                                            <div class="item--links">
			                                                <div class="item--expanded-links">
			                                                    <a href="/create-page/${page.id}" class="icon-edit" data-item="Page"></a>
			                                                    <a href="" class="icon-del item--links--last open-delete-modal" data-item="Page"></a>
			                                                    <%-- <a href="" class="icon-share item--links--last open-share-modal" data-item="Page"
			                                                       data-url="/view?type=page&id=${page.id}"></a> --%>
			                                                </div>
			
			                                            </div>
			
			                                        </div>
			                                        <input type="checkbox" class="page-item-checkbox" 
			                                        	id="page-${story.id}-${chapter.id}-${page.id}" />
			
			                                        <ol class="item--content">
			                                            <li>
			                                                <div class="item--page">
			                                                	<div class="page-content">
											            			${page.pageContent}
											            		</div>
			                                                </div>
			                                            </li>
			
			                                        </ol>
			
			                                    </li>
                                			</c:forEach>
                                		
                                		</ol>
                                	</li>
                                
								</c:forEach>
							</ol>
							
						</li>
						
					</c:forEach>
					
                </ol>


            </div>
        </div>
        <div class="push"></div>
    </div>
</section>

<li id="storyItem-0" class="item story-item not-display" >
     <span class="item--id"></span>
     <div class="item--header">
         <div class="item--info item-center">
             <label for="story-0" class="story-item-label item--expend">
                 <i class="icon-arrow-right item--icon"></i>
                 <span class="item--title"></span>
             </label>
             <span class="created-time"></span>

         </div>
         <div class="item--links">
             <div class="item--expanded-links">
                 <a href="" class="link-add open-add-modal" data-item="Chapter">Add Chapter</a>
                 <a href="" class="icon-edit open-edit-modal" data-item="Story"></a>
                 <a href="/overview/0" class="icon-zoom"></a>
                 <a href="" class="icon-del open-delete-modal" data-item="Story"></a>

             </div>
             <a href="" class="icon-share item--links--last open-share-modal" data-item="Story"
                data-url="${baseUrl}/view?type=story&id=0"></a>
         </div>

     </div>
     <input type="checkbox" class="story-item-checkbox" id="story-0" />

	<!-- chapters -->
	<ol class="item--content">
	</ol>
</li>

<li id="chapterItem-0-0" class="item chapter-item not-display">
	<span class="item--id"></span>
    <div class="item--header item--sub">
        <div class="item--info item-center">
            <label for="chapter-0-0" class="chapter-item-label item--expend">
                <i class="icon-arrow-right item--icon"></i>
                <span class="item--title"></span>
            </label>

        </div>
        <div class="item--links">
            <div class="item--expanded-links">
                <a href="" class="link-add add-page" data-item="Page">Add Page</a>
                <a href="" class="icon-edit open-edit-modal" data-item="Chapter"></a>
                <a href="/overview/0?type=chapter" class="icon-zoom"></a>
                <a href="" class="icon-del open-delete-modal" data-item="Chapter"></a>
                <a href="" class="icon-share item--links--last open-share-modal" data-item="Chapter"
                   data-url="${baseUrl}/view?type=chapter&id=0"></a>
            </div>

        </div>

    </div>
    <input type="checkbox" class="chapter-item-checkbox" 
    	id="chapter-0-0" />
    
    <!--pages-->
	<ol class="item--content">
	</ol>
</li>

<li id="pageItem-0-0-0" class="item page-item not-display">
	<span class="item--id"></span>
     <div class="item--header item--sub--sub">
         <div class="item--info item-center">
             <label for="page-0-0-0" 
             	class="page-item-label item--expend">
                 <i class="icon-page item--icon"></i>
                 <span class="item--title"></span>
             </label>

         </div>
         <div class="item--links">
             <div class="item--expanded-links">
                 <a href="" class="icon-edit open-edit-modal" data-item="Page"></a>
                 <a href="" class="icon-del item--links--last open-delete-modal" data-item="Page"></a>
                 <%-- <a href="" class="icon-share item--links--last open-share-modal" data-item="Page"
                    data-url="/view?type=page&id=${page.id}"></a> --%>
             </div>

         </div>

     </div>
     <input type="checkbox" class="page-item-checkbox" 
     	id="page-0-0-0" />

     <ol class="item--content">
         <li>
             <div class="item--page">
                 <div class="page-content">
											            			
				</div>
             </div>
         </li>

     </ol>
	
</li>

</main>


<%@include file="footer.jsp"%>


</body>
</html>

