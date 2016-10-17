<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8">
		<meta name="layout" content="main">
		<title>gTunes Store</title>
		<g:javascript library="jquery"></g:javascript>
	</head>
	<body id="body">
        <div class="albumSongs">
            <h2>${album.title}</h2>
            <g:render template="album" model="[album:album, artist:album.artist]"></g:render>
            <div style="margin-top:10px">
                <g:link controller="store" action="shop">Back to Store</g:link>
            </div>
        </div>
        <g:javascript>
            $('#album${album.id}').fadeIn('slow');
        </g:javascript>
	</body>
	
</html>
