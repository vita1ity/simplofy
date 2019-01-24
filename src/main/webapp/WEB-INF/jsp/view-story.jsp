<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


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


<section class="section-main">
    <div class="wrap">
    
    
    	<c:if test="${not empty story}">
    
	        <div class="block-stories">
	            <div class="block-stories--header">
	            	
	                <h2 class="block-stories--title no-uppercase">Story: ${story.storyName}</h2>
	            </div>
	            <div class="block-stories--content">
	
	                <ol class="accordion">
	                	<!-- story -->
	                		
						<li id="storyItem-${story.id}" class="item story-item" >
	                        <span class="item--id">${story.id}</span>
	                        <div class="item--header item--selected">
	                            <div class="item--info item-center">
	                                <label for="story-${story.id}" class="story-item-label item--expend open">
	                                    <i class="icon-arrow-down item--icon"></i>
	                                    <span class="item--title">${story.storyName}</span>
	                                </label>
	                                <span class="created-time">${story.createdAgo}</span>
	
	                            </div>
	                            
	
	                        </div>
	                        <input type="checkbox" class="story-item-checkbox" checked id="story-${story.id}" />
							
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
							
						
	                </ol>
	
	
	            </div>
	        </div>
        
        </c:if>
        <c:if test="${not empty chapter}">
        	<div class="block-stories">
	            <div class="block-stories--header">
	            	
	                <h2 class="block-stories--title no-uppercase">Chapter: ${chapter.chapterName}</h2>
	            </div>
	            <div class="block-stories--content">
	            
	            	<!-- chapters -->
					<ol class="accordion">
						
						<li id="chapterItem-${chapter.story.id}-${chapter.id}" class="item chapter-item">
							<span class="item--id">${chapter.id}</span>
                               <div class="item--header item--sub item--selected">
                                   <div class="item--info item-center">
                                       <label for="chapter-${chapter.story.id}-${chapter.id}" class="chapter-item-label item--expend open">
                                           <i class="icon-arrow-right item--icon"></i>
                                           <span class="item--title">${chapter.chapterName}</span>
                                       </label>

                                   </div>
                                   

                               </div>
                               
                              	<input type="checkbox" checked class="chapter-item-checkbox" 
                              		id="chapter-${chapter.story.id}-${chapter.id}" />
                               
                               
                               <!--pages-->
                           		<ol class="item--content">
                           		
                           			<c:forEach var="page" items="${chapter.pages}" varStatus="pageStatus">
                           				<li id="pageItem-${chapter.story.id}-${chapter.id}-${page.id}" 
                           					class="item page-item">
                           					<span class="item--id">${page.id}</span>
                                      <div class="item--header item--sub--sub">
                                          <div class="item--info item-center">
                                              <label for="page-${chapter.story.id}-${chapter.id}-${page.id}" 
                                              	class="page-item-label item--expend">
                                                  <i class="icon-page item--icon"></i>
                                                  <span class="item--title">${page.title}</span>
                                              </label>

                                          </div>
                                          

                                      </div>
                                      <input type="checkbox" class="page-item-checkbox" 
                                      	id="page-${chapter.story.id}-${chapter.id}-${page.id}" />

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
                             
					</ol>
	            
	            </div>
	        </div>
        </c:if>
        
        <div class="push"></div>
    </div>
</section>

</main>

<%@include file="footer.jsp"%>


</body>
</html>

