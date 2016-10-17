<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Grails"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
    <link href='http://fonts.googleapis.com/css?family=PT+Sans+Narrow' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'gtunes.css')}" type="text/css">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
    <g:layoutHead/>
    <r:layoutResources />
</head>
<body class="application">

    <div id="container">
         <div id="spinner" class="spinner" style="display:none;">
            <img src="${createLinkTo(dir:'images',file:'spinner.gif')}" alt="Spinner" />
        </div>

        <header id="header">
            <h1 id="logo"><a href="${createLink(uri: '/')}">gTune</a></h1>
            <g:if test="${session?.user}">
                <div class="message notice">
                    Welcome back <span id="userFirstName">${session?.user?.firstName}!</span>
                    <a href="#">Profile</a> | <g:link controller="user" action="logout">Logout</g:link><br />
                    You have purchased (${session.user.purchasedSongs?.size() ?: 0}) songs.<br>
                </div>
                <ul>
                    <li><g:link controller="user" action="music">My Music</g:link></li>
                    <li><g:link controller="store" action="shop">The Store</g:link></li>
                </ul>
            </g:if>

        </header>

        <div id="main">
            <g:layoutBody />
        </div>

        <footer id="footer">
            © gTunes 2012
        </footer>
    </div>
    <r:layoutResources />
</body>
</html>
