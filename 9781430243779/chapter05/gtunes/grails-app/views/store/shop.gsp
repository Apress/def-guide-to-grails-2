<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta name="layout" content="main">
    <title>gTunes Store</title>
</head>

<body id="body">
    <div id="top5Panel" class="top5Panel">

        <ul id="tabs" class="tabs clearfix">
            <li class="selected"><a href="#albums">Latest Albums</a></li>
            <li><a href="#songs">Latest Songs</a></li>
            <li><a href="#artists">Newest Artists</a></li>
        </ul>

        <div id="albums" class="top5Item">
            <g:render template="/album/albumList" model="[albums: top5Albums]"></g:render>
        </div>

        <div id="songs" class="top5Item hide">
            <g:render template="/song/songList" model="[songs: top5Songs]"></g:render>
        </div>

        <div id="artists" class="top5Item list hide">
            <g:render template="/artist/artistList" model="[artists: top5Artists]"></g:render>
        </div>
    </div>

    <h1>Online Store <em>Browse the categories below</em></h1>
    <ul class="genreList list">
        <g:each var="genre" in="${genres}">
            <li class="genre"><g:link controller="store" action="genre" params="[name: genre]">${genre}</g:link></li>
        </g:each>
    </ul>

</body>

<r:script>
$(function(){
    $("#tabs a").click(function(){
        $("#albums, #songs, #artists").hide();
        $("#tabs li").removeClass('selected');
        $($(this).attr('href')).show();
        $(this).parent().addClass('selected');
        return false;
    });
});
</r:script>

</html>