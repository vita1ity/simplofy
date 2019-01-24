<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Simplofy - Overview</title>
    
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

<span id="pageName" class="not-display">overview</span>
<span id="storyId" class="not-display">${story.id}</span>

<c:if test="${not empty story}">

	<section class="section-main">
	  <div class="wrap">
	  
	    <div class="block-whitebg">
	      <div class="block-whitebg--header">
	        <h3 class="block-whitebg--title1">
	          <span class="block-whitebg--story-name">${story.storyName}</span>
	        </h3>
	      </div>
	      <div class="block-whitebg--content">
	      	<c:forEach var="chapter" items="${story.chapters}">
	      		<div id="chapter-${chapter.id}" class="chapter-item">
		      		<span class="not-display chapter-id">${chapter.id}</span>
			        <h4 class="block-whitebg--title2"><span class="chapter-name">${chapter.chapterName}</span></h4>
			        
			        <div class="grid-row pages">
			        	<c:forEach var="page" items="${chapter.pages}" varStatus="pageStatus">
			        		
			        		<div id="page-${page.id}" class="grid-4 excerpt-page formats-rules page-item">
			        			<span class="not-display page-id">${page.id}</span>
					            <div class="excerpt-page--inner">
					            	<div class="short-text">
					            		${page.pageContent}
					            	</div>
					            </div>
					            <div class="excerpt-page--foot">${page.title}</div>
					
					            <div class="excerpt-page--hover open-overview-modal">
					              <%-- <div class="excerpt-page--center">
					                <a href="/create-page/${page.id}" class="icon-edit"></a>
					                <a href="" class="icon-del open-delete-modal"></a>
					              </div> --%>
					            </div>
					          </div>
			        	</c:forEach>
			        
			         </div>
	          		</div>
	         </c:forEach>
	         
	        </div>
	      </div>
	
	  <div class="push"></div>
	  </div>
	</section>
</c:if>

<c:if test="${not empty chapter}">
	<span id="pageName" class="not-display">overview</span>
	<span id="chapterId" class="not-display">${chapter.id}</span>
	
	<section class="section-main">
	  <div class="wrap">
	  
	    <div class="block-whitebg">
	      <div class="block-whitebg--header">
	        <h3 class="block-whitebg--title1">
	          <span class="block-whitebg--story-name">${chapter.chapterName}</span>
	        </h3>
	      </div>
	      <div class="block-whitebg--content">
	      	
		        <div class="grid-row pages">
		        
		        	<c:forEach var="page" items="${chapter.pages}" varStatus="pageStatus">
		        		
		        		  <div id="page-${page.id}" class="grid-4 excerpt-page formats-rules page-item">
		        			<span class="not-display page-id">${page.id}</span>
				            <div class="excerpt-page--inner">
				            	<div class="short-text">
				            		${page.pageContent}
				            	</div>
				              
				            </div>
				            <div class="excerpt-page--foot">${page.title}</div>
				
				            <div class="excerpt-page--hover open-overview-modal">
				              
				            </div>
				            
				          </div>
				         
		        	</c:forEach>
		        
		         </div>
	          		
	         
	        </div>
	      </div>
	
	  <div class="push"></div>
	  </div>
	</section>
</c:if>	

</main>

<%@include file="footer.jsp"%>


</body>
</html>

