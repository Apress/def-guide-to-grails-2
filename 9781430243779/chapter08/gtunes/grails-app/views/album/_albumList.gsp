<ul class="list">
	<g:each in="${albums?}" var="album">
		<li class="icon">
            <g:link controller="album" action="show" id="${album?.id}">
                <g:img dir="images/icons" file="album.png" />
                ${album.title}
            </g:link>
        </li>
        <g:javascript>
        $('#albumLink${album.id}').click (function() {
            return showAlbum(${album.id});
        });
        </g:javascript>
	</g:each>
</ul>
<g:javascript>
function showAlbum(albumId) {
    $.ajax({
        url: '${createLink(action: "display", controller: "album")}?id=' + albumId,
        success: function(data) {
            $('#musicPanel').html(data);
            $('#album' + albumId).fadeIn('slow');
        }
    });
    return false;
}
</g:javascript>
