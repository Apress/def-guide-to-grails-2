package com.gtunes


import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
@TestFor(AlbumArtTagLib)
class AlbumArtTagLibTests {

    void testGoodResultFromService() {
        tagLib.albumArtService = [getAlbumArt: { artist, album ->
            '/path/to/some/album-art.gif'
        }]
        assert applyTemplate('<music:albumArt artist="Tool" album="Lateralus" />') == '<img width="100" src="/path/to/some/album-art.gif" border="0"></img>'
    }

    void testSpecifyingImageWidth() {
        tagLib.albumArtService = [getAlbumArt: { artist, album ->
            '/path/to/some/album-art.gif'
        }]
        assert applyTemplate('<music:albumArt artist="Tool" album="Lateralus" width="50"/>') == '<img width="50" src="/path/to/some/album-art.gif" border="0"></img>'
    }
}
