
<%@ page import="com.blog.Post" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'post.label', default: 'Post')}" />
		<title>Post List</title>
	</head>
	<body>
        <div class="nav"> 
            <span class="menuButton">
                <g:link class="create" action="create">New Post</g:link>
            </span> </div> <div class="blog">
            <h1>${grailsApplication.config.blog.title ?: 'No Title'}</h1>
            <g:render plugin="blog"
                      template="post" var="post"
                      collection="${postInstanceList?.reverse()}" /> 
        </div> 
	</body>
</html>
