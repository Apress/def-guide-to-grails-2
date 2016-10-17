<ul class="list">
	<g:each in="${albums?}" var="album">
		<li class="icon">
            <g:link controller="album" action="show" id="${album?.id}">
                <g:img dir="images/icons" file="album.png" />
                ${album.title}
            </g:link>
        </li>
	</g:each>
</ul>
