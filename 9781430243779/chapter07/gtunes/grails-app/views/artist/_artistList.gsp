<ul class="list">
	<g:each in="${artists?}" var="artist">
        <li class="icon">
            <g:link controller="store" action="shop">
                <g:img dir="images/icons" file="artist.png" />
                ${artist?.name}
            </g:link>
        </li>
	</g:each>
</ul>