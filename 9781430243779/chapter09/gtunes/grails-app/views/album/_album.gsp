<div id="album${album.id}" class="album" style="display: none;">
	<div class="albumDetails">
        <div class="albumArt">
            <music:albumArt artist="${artist}" album="${album}"/>
        </div>
		<div class="artistName">${artist.name} (${album.year})</div>
		<div class="albumInfo">
			Genre: ${album.genre ?: 'Other'}<br>
		</div>
		<div class="albumTracks">
            <table>
                <tbody>
                <g:each in="${album.songs}" var="song" status="i">
                    <tr>
                        <td width="10px"><g:img dir="images/icons" file="song.png" /></td>
                        <td width="10px" class="small">${i+1}</td>
                        <td>${song.title}</td>
                    </tr>
                </g:each>
                </tbody>
            </table>
		</div>
	</div>
</div>
