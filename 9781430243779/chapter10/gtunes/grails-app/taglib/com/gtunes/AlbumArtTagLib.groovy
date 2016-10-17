package com.gtunes

class AlbumArtTagLib {
        
    static namespace = "music"

    def albumArtService
    
    def albumArt = { attrs, body ->
        def artist = attrs.remove('artist')?.toString()
        def album = attrs.remove('album')?.toString()
        def width = attrs.int('width', 100)
        attrs.remove('width')
        def albumArt = albumArtService.getAlbumArt(artist, album)
        if(albumArt.startsWith("/")) albumArt = "${request.contextPath}${albumArt}"         
        out << "<img width=\"$width\" src=\"${albumArt}\" border=\"0\""
        out << attrs.collect { attributeName, attributeValue ->
            " ${attributeName}=\"${attributeValue.encodeAsHTML()}\""
        }.join(' ')
        out << "></img>"
    }
}

