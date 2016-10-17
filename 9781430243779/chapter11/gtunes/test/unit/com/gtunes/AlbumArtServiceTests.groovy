package com.gtunes

import grails.plugins.rest.client.RestBuilder
import groovy.mock.interceptor.MockFor
import grails.test.mixin.*
//import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(AlbumArtService)
class AlbumArtServiceTests {

    void testNullAlbumAndArtist() {
        def result = service.getAlbumArt(null, null)	
		assert AlbumArtService.DEFAULT_ALBUM_ART_IMAGE == result
    }

    void testGoodResult() {
        mockCodec org.codehaus.groovy.grails.plugins.codecs.URLCodec
        def artworkClient = new groovy.mock.interceptor.MockFor(RestBuilder)
        artworkClient.demand.get { String s ->
            def results = []
            results << [artistName: 'Thin Lizzy', collectionName: 'Jailbreak', artworkUrl100: 'http://somesite/jailbreak.jpg']
            results << [artistName: 'Tool', collectionName: 'Lateralus', artworkUrl100: 'http://somesite/lateralus.jpg']
            [json: [results: results]]
        }
        def result
        artworkClient.use {
            result = service.getAlbumArt('Tool', 'Lateralus')
        }
        assert 'http://somesite/lateralus.jpg' == result
    }
}
