<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Simplofy - Login / Registration</title>
    <link rel="stylesheet" href="/css/style.css">
    
	<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
	<link rel="icon" href="/favicon.ico" type="image/x-icon">
</head>
<body class="fixed-header">


<header class="header-login">
    <div>
        <a href="" class="logo"><img src="/images/svg/logo.svg" alt=""></a>
    </div>
</header>




<main class="main-container">


<section class="section-main-login">
    <div class="wrap">
        <div class="enter-block">

            <div class="grid-row">
                <div class="grid-2">
                    <h1 class="enter-title">Log In</h1>
                </div>
                <div class="grid-2">
                    <h1 class="enter-title move-down">Create Account</h1>
                </div>
            </div>

            <div class="grid-row">
                <div class="grid-2">
                    <!-- <div class="enter-block--form"> -->
                    <form:form action="/" method="POST" class="enter-block--form">
                        <!--<h1 class="enter-title">Log In</h1>-->

                        <div class="email wrapper">
                            <i class="icon-human enter-block--icon"></i>
                            <input class="enter-block--field" name="username" placeholder="Username/Email" type="email"/>

                        </div>
                        <div class="password wrapper">
                            <i class="icon-lock enter-block--icon"></i>
                            <input class="enter-block--field" name="password" placeholder="Password" type="password"/>

                        </div>
                        
                        <c:if test="${param.error != null}">
						    <div class="error">
						        <spring:message code="message.badCredentials">   
						        </spring:message>
						    </div>
						</c:if>
                        
                        <div class="enter-block--button">
                            <input type="submit" class="btn-yellow" value="Log In"/>
                        </div>


                    </form:form>
                </div>

                <!--<hr class="vline" />-->

                <div class="grid-2 border">
                    <!-- <div class="enter-block--form"> -->
                    <form:form modelAttribute="signupForm" method="POST" action="/registration" class="enter-block--form">
                        <!--<h1 class="enter-title">Create Account</h1>-->

                        <div class="email wrapper">
                            <i class="icon-human enter-block--icon"></i>
                            <input class="enter-block--field " name="email" placeholder="Email" type="email" value="${signupForm.email}"/>
                            <div class="error">
                            	<form:errors path="email"/>
                            </div>

                        </div>
                        <div class="password wrapper">
                            <i class="icon-lock enter-block--icon"></i>
                            <input class="enter-block--field" name="password" placeholder="Password" type="password" value="${signupForm.password}"/>
                            <div class="error">
                            	<form:errors path="password"/>
                            </div>

                        </div>
                        <div class="confirm-password wrapper">
                            <i class="icon-lock enter-block--icon"></i>
                            <input class="enter-block--field" name="confirmPassword"
                                   placeholder="Retype Password" type="password" value="${signupForm.confirmPassword}"/>
                            <div class="error">
                            	<form:errors path="confirmPassword"/>
                            </div>

                        </div>
                        <div class="terms-service">
                        	<p>By creating an account you are agree with our</p>
                        	<a href="/docs/Simplofy_Terms_Of_Service.pdf" target="_blank"><b>terms of service</b></a>
                        </div>
                        <div class="enter-block--button">
                            <input type="submit" class="btn-yellow" value="Create Account"/>
                        </div>


                    </form:form>
                </div>

            </div>
        </div>
        <div class="push"></div>
    </div>
</section>


</main>

<footer class="mainfooter">
  <div class="wrap">
    <ul class="foot-menu">
   	  <!-- <li><a href="mailto:simplofy@gmail.com">Contact Us</a></li> -->
      <li class="foot-menu--logo"><a href="/"><img src="/images/svg/logo.svg" alt=""></a></li>
      <!-- <li><a href="/docs/Simplofy_Privacy_Policy.pdf" target="_blank">Privacy Policy</a></li> -->
    </ul>
    <ul class="foot-menu">
    	<li><a href="mailto:simplofy@gmail.com">Contact Us</a></li>
    	<li><div class="copy">2018 All rights reserved.</div></li>
    	<li><a href="/docs/Simplofy_Privacy_Policy.pdf" target="_blank">Privacy Policy</a></li>
    </ul>
  </div>
</footer>




<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.25.0/codemirror.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.25.0/mode/xml/xml.min.js"></script>

<!-- Include Editor JS files. -->
<script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.7.5/js/froala_editor.min.js'></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.7.5/js/froala_editor.pkgd.min.js"></script>
<script src="/js/scripts.min.js"></script>


</body>
</html>
