package com.gtunes

import grails.plugins.rest.client.RestBuilder

import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.web.GroovyPageUnitTestMixin} for usage instructions
 */
@TestFor(AlbumArtTagLib)
class AlbumArtTagLibTests {

    void testNoAlbumSpecified() {
        assert applyTemplate('<music:albumArt artist="Tool" />') == """<img width="100" src="${AlbumArtTagLib.DEFAULT_ALBUM_ART_IMAGE}" border="0"></img>"""
    }

    void testNoArtistSpecified() {
        assert applyTemplate('<music:albumArt album="Lateralus" />') == """<img width="100" src="${AlbumArtTagLib.DEFAULT_ALBUM_ART_IMAGE}" border="0"></img>"""
    }

    void testGoodResult() {
        def artworkClient = new groovy.mock.interceptor.MockFor(RestBuilder)
        artworkClient.demand.get { String s ->
            def results = []
            results << [artistName: 'Thin Lizzy', collectionName: 'Jailbreak', artworkUrl100: 'http://somesite/jailbreak.jpg']
            results << [artistName: 'Tool', collectionName: 'Lateralus', artworkUrl100: 'http://somesite/lateralus.jpg']
            [json: [results: results]]
        }
        artworkClient.use {
            assert applyTemplate('<music:albumArt artist="Tool" album="Lateralus" />') == '<img width="100" src="http://somesite/lateralus.jpg" border="0"></img>'
       }
    }

    void testSpecifyingImageWidth() {
        def artworkClient = new groovy.mock.interceptor.MockFor(RestBuilder)
        artworkClient.demand.get { String s ->
            def results = []
            results << [artistName: 'Thin Lizzy', collectionName: 'Jailbreak', artworkUrl100: 'http://somesite/jailbreak.jpg']
            results << [artistName: 'Tool', collectionName: 'Lateralus', artworkUrl100: 'http://somesite/lateralus.jpg']
            [json: [results: results]]
        }
        artworkClient.use {
            assert applyTemplate('<music:albumArt artist="Tool" album="Lateralus" width="50"/>') == '<img width="50" src="http://somesite/lateralus.jpg" border="0"></img>'
       }
    }
}
