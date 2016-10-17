<!-- -->
<ul class="list">
	<g:each in="${songs?}" var="song">
		<li class="icon">
            <g:link controller="store" action="shop">
                <g:img dir="images/icons" file="song.png" />
                ${song.title}
            </g:link>
        </li>
	</g:each>
</ul>