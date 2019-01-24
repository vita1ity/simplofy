<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>stories</title>

    <link rel="stylesheet" href="/css/style.css">
    
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
	<script src="/js/scripts.min.js"></script>
	<script src="/js/ajax.js"></script>
	
	<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
	<link rel="icon" href="/favicon.ico" type="image/x-icon">
</head>
<body class="fixed-header">

<%@include file="header.jsp"%>


<main class="main-container">

<span id="pageName" class="not-display">admin</span>

<section class="section-main">
    <div class="wrap">
        <div class="block-stories">
            <div class="block-stories--header">
                <h2 class="block-stories--title">Users
                </h2>
            </div>
            <div class="block-stories--content">

                <ol class="accordion">
                    <!--users-->
                    <c:forEach var="user" items="${allUsers}" varStatus="loopStatus">
	                    <li class="item" id="user-${user.id}">
							<span class="user-id not-display">${user.id}</span>
	                        <div class="item--header">
	                            <div class="item--info item-center">
	
	                                <span class="item--title"><span class="user-number">${loopStatus.index + 1}. </span> 
	                                <span class="username">${user.email}</span></span>
	
	                            </div>
	                            <div class="item--links">
	
	                                <a href="" class="icon-edit open-password-update-modal"></a>
	                                <a href="" class="icon-del item--links--last open-delete-user-modal"></a>
	
	                            </div>
	
	                        </div>
	                    </li>
					</c:forEach>
                    
                </ol>


            </div>
        </div>
        <div class="push"></div>
    </div>
</section>

</main>


<%@include file="footer.jsp"%>



</body>
</html>

