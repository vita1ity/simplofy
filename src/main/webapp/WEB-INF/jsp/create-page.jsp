<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Create Page - Simplofy</title>

    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.25.0/codemirror.min.css">

    <!-- Include Editor style. -->
    <!--<link href='https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.7.5/css/froala_editor.min.css' rel='stylesheet' type='text/css' />-->
    <link href='https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.7.5/css/froala_style.min.css' rel='stylesheet' type='text/css' />

    <link rel="stylesheet" href="/css/froala_editor.css">
    <link rel="stylesheet" href="/css/froala_editor.pkgd.min.css">
    <link rel="stylesheet" href="/css/style.css">
    <!-- <link rel="stylesheet" href="/css/gray.min.css">
    <link rel="stylesheet" href="/css/dark.min.css"> -->
    <link rel="stylesheet" href="/css/custom-theme.css">

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.25.0/codemirror.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.25.0/mode/xml/xml.min.js"></script>

    <!-- Include Editor JS files. -->
    <script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.7.5/js/froala_editor.min.js'></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/froala-editor/2.7.5/js/froala_editor.pkgd.min.js"></script>
    <script src="/js/scripts.min.js"></script>
	<script src="/js/froala.js"></script>
	<script src="/js/image.min.js"></script>
	<script src="/js/image_manager.min.js"></script>
	<script src="/js/ajax.js"></script>
	<script src="/js/create-page.js"></script>
	
	<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon">
	<link rel="icon" href="/favicon.ico" type="image/x-icon">

</head>
<body class="fixed-header">

<%@include file="header.jsp"%>

<main class="main-container">



<section class="section-main">
    <div class="wrap">
        <div class="block-whitebg">
            <div class="block-whitebg--header-small">
                <div class="block-whitebg--title-block ">
                    <h3 class="block-whitebg--title3">${page.chapter.story.storyName}
                        &nbsp;&nbsp;|&nbsp;&nbsp;  ${page.chapter.chapterName}
                        &nbsp;&nbsp;|&nbsp;&nbsp; <span id="createPageTitle">${page.title}</span></h3>
                    <span id="pageId" class="item--id">${page.id}</span>     
                    <a href="/overview/${page.chapter.story.id}" class="link-overview">
                        <div class="block-whitebg--link">
                            <i class="icon-zoom icon-inline"></i>
                            <span>Overview</span>
                        </div>

                    </a>
                </div>
            </div>

            <div class="text-editor">
            
            	${page.pageContent}
            
            </div>

            <div class="block-whitebg--footer cf">
                <div class="fl">
                    <a href="" class="link-delete" id="deletePage" data-item="Page">
                        <div class="link-delete--center">
                            <i class="icon-del icon-inline"></i>
                            <span>Delete Page</span>
                        </div>

                    </a>
                </div>
                <div class="fr btn-nav-size" >
                    <a href="/page/prev?pageId=${page.id}" class="btn-nav" id="prevBtn">
                        <span class="btn-nav--center">
                            <span class="btn-nav--icon icon-prev"></span>
                            <span class="btn-nav--title">Prev. Page</span>
                        </span>
                    </a>
                    <!-- <a href="" class="btn-nav">
                        <span class="btn-nav--center">
                            <span class="btn-nav--icon icon-plus"></span>
                            <span class="btn-nav--title">New Chapter</span>
                        </span>
                    </a> -->
                    <a href="/page/next?pageId=${page.id}" class="btn-nav border-radius">
                        <span class="btn-nav--center">
                            <span class="btn-nav--icon icon-next"></span>
                            <span class="btn-nav--title">Next Page</span>
                        </span>
                    </a>
                </div>
            </div>
        </div>
        <div class="push"></div>
    </div>
</section>

</main>

<%@include file="footer.jsp"%>

<c:if test="${empty page.title}">
	<span id="testPageId" class="not-display">${page.id}</span>
	<script>
		openNewPageModal($('#testPageId').text());
    </script>
</c:if>

</body>
</html>
