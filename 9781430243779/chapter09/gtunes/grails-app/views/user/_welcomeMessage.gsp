<header id="header">
    <h1 id="logo"><a href="${createLink(uri: '/')}">gTune</a></h1>
    <g:if test="${session?.user}">
        <div id="quickaccess"><a href="#">${session?.user?.firstName}</a>, <g:link controller="user" action="logout">Logout</g:link></div>
        <nav id="navigation" class="clearfix">
            <ul>
                <li class="separator"><g:link controller="user" action="music">My Music</g:link></li>
                <li><g:link controller="store" action="shop">The Store</g:link></li>
            </ul>
        </nav>
    </g:if>
</header>
<div id="message notice">
    <div style="margin-top:20px">
        Welcome back 
        <span id="userFirstName">${session?.user?.firstName}!</span>
        <br><br>

        You have purchased 
       (${session.user.purchasedSongs?.size() ?: 0}) songs.
       <br>
    </div>
</div>

