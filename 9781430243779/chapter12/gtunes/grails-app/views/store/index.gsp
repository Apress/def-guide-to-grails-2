<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta name="layout" content="main">
    <title>gTunes Welcome</title>
    <g:javascript library="jquery"></g:javascript>
</head>
<body>
    <g:if test="${!session?.user}">

        <div class="colset clearfix">
            <div id="loginBox">
                <g:render template="/user/loginBox"/>
            </div>
        </div>

    </g:if>
    <div class="message notice">
        <h1>Your online music store and storage service!</h1>
        <p>Manage your own library, browse music and purchase new tracks as they become available</p>
        <br/>
        <p><g:link action="showTime" elementId="timeLink">Show the time!</g:link>
        <div id="time">
        </div>
    </div>
<r:script>
$('#timeLink').click(function() {
    $('#time').load(this.href); return false;
});
$(function() {
    $('#loginForm').ajaxForm(function(result) {
        $('#loginBox').html(result);
    });
});
</r:script>
</body>
</html>
