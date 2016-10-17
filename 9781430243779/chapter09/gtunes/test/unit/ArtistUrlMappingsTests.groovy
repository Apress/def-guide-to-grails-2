@TestFor(ArtistUrlMappings)
@Mock(com.gtunes.ArtistController)
class ArtistUrlMappingsTests {

    void testShowArtistUrlMapping() {
        // assert that /showArtist/Jeff_Beck is handled by the 
        // display action in the artist controller
        assertForwardUrlMapping('/showArtist/Jeff_Beck',
                                controller: 'artist', action: 'display')

        // assert that /showArtist/Jeff_Beck is handled by the
        // display action in the artist controller and a request
        // parameter named artistName exists with the value Jeff_Beck
        assertForwardUrlMapping('/showArtist/Jeff_Beck',
                                controller: 'artist', action: 'display') {
            artistName = 'Jeff_Beck'
        }
    }

    void testShowArtistReverseUrlMapping() {
        // assert that when a reverse url lookup is done for the
        // display action in the artist controller with a request
        // parameter named artistName with value Jeff_Beck, then
        // the generated url is /showArtist/Jeff_Beck
        assertReverseUrlMapping('/showArtist/Jeff_Beck',
                                controller: 'artist', action: 'display') {
            artistName = 'Jeff_Beck'
        }
    }

    void testForwardAndReverseUrlMapping() {
       assertUrlMapping('/showArtist/Jeff_Beck',
                                controller: 'artist', action: 'display') {
            artistName = 'Jeff_Beck'
        }

    }
}
