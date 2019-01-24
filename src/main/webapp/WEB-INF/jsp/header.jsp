<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<header class="mainheader cf">
        <div class="fl">
            <a href="/stories" class="logo"><img src="/images/svg/logo.svg" alt=""></a>
        </div>
        <div class="fr">
            <a href="/stories" class="btn-top">
                <span class="btn-top--center">
                    <span class="btn-top--icon icon-book"></span>
                    <span class="btn-top--title">Stories</span>
                </span>
            </a>
            
            <sec:authorize access="hasRole('ROLE_ADMIN')">
				<a href="/admin" class="btn-top">
	                <span class="btn-top--center">
	                    <span class="btn-top--icon icon-human"></span>
	                    <span class="btn-top--title">Admin</span>
	                </span>
	            </a>
			</sec:authorize>
            <sec:authorize access="isAuthenticated()">
	           <a href="/logout" class="btn-top">
	               <span class="btn-top--center">
	                   <span class="btn-top--icon icon-logout"></span>
	               </span>
	           </a>
            </sec:authorize>
        </div>

</header>