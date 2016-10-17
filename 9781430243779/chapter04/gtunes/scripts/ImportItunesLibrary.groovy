import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import javax.xml.parsers.SAXParserFactory

includeTargets << grailsScript('_GrailsBootstrap')

target(importItunesLibrary: 'Import iTunes Library') {
    depends classpath, checkVersion, bootstrap
    if(args) {
        def file = new File(args.replaceAll(/\n/, ' ').trim())
        if(file.exists()) {
            println "Loading XML, this can take a while..."
            def parser = SAXParserFactory.newInstance().newSAXParser()
            def handler = new SAXItunesParser()
            parser.parse(file, handler)
            println "Finished reading XML, importing to app.."

            def artistClass = classLoader.loadClass('com.gtunes.Artist')
            def albumClass = classLoader.loadClass('com.gtunes.Album')
            def songClass = classLoader.loadClass('com.gtunes.Song')
            def artists = handler.artistsToAlbumsToTracks
            artists.each { artistName, albums ->
                artistClass.withTransaction { tx ->
                    artist = artistClass.newInstance(name: artistName).save(failOnError: true)

                    albums.each { albumTitle, albumDetails ->
                        album = albumClass.newInstance(title: albumTitle, year: albumDetails.year ?: 2012, genre: albumDetails.genre ?: 'Unknown').save(failOnError: true)
                        artist.addToAlbums album
                        artist.save(failOnError: true)
                        albumDetails.tracks?.each { trackInfo ->
                            song = songClass.newInstance(title: trackInfo.name, genre: trackInfo.genre ?: 'Unknown', year: trackInfo.year ?: 2012, trackNumber: trackInfo.tracknumber ?: 1, duration: trackInfo.totaltime ?: 0, artist: artist, album: album)
                            song.save(failOnError: true)
                        }
                    }
                }
            }
        }
        else {
            println "File ${args} does not exist"
        }
    }
    else {
        println "Please specify iTunes library XML file"
    }
}

setDefaultTarget importItunesLibrary


class SAXItunesParser extends DefaultHandler {

    private dictLevel = 0
    private valueOfLastKeyParsed
    private trackDetails = [:]
    private currentlyParsingTracks = false
    private elementBeingParsed

    def artistsToAlbumsToTracks = [:].withDefault {
        [:].withDefault {
            [:].withDefault {
                key -> if('tracks' == key) {
                    return [] 
                } else {
                    return null
                }
            }
        }
    }

    void startElement(String ns, String localName, String qName, Attributes atts) {
        elementBeingParsed = qName
        switch(qName) {
            case 'dict':
            dictLevel++
            if(dictLevel  == 2 && 'Tracks' == valueOfLastKeyParsed) {
                currentlyParsingTracks = true
            }
            break
        }
    }

    void characters(char[] chars, int offset, int length) {
        def str = new String(chars, offset, length)
        if('key' == elementBeingParsed) {
            valueOfLastKeyParsed = str
        } else if(currentlyParsingTracks) {
            switch(valueOfLastKeyParsed) {
                case 'Genre':
                trackDetails.genre = append(trackDetails.genre, str)
                break
                case 'Artist':
                trackDetails.artist = append(trackDetails.artist, str)
                break
                case 'Album':
                trackDetails.album = append(trackDetails.album, str)
                break
                case 'Name':
                trackDetails.name = append(trackDetails.name, str)
                break
                case 'Year':
                trackDetails.year = str.toInteger()
                break
                case 'Bit Rate':
                trackDetails.bitrate = str.toInteger()
                break
                case 'Total Time':
                trackDetails.totaltime = str.toInteger()
                break
                case 'Track Number':
                trackDetails.tracknumber = str.toInteger()
                break
            }
        }
    }

    private append(a, b) {
        (a ?: '') + b
    }

    void endElement(String ns, String localName, String qName) {
        switch(qName) {
            case 'dict':
            dictLevel--
            if(currentlyParsingTracks && dictLevel == 2 && !trackDetails.containsKey('isVideo')) {
                def albums = artistsToAlbumsToTracks.get(trackDetails.artist)
                if(trackDetails.album != null) {
                def album = albums.get(trackDetails.album)
                if(trackDetails.containsKey('year')) {
                    album.year = trackDetails.'year'
                }
                   if(trackDetails.containsKey('genre')) {
                       album.genre = trackDetails.genre
                   }
                trackDetails.remove 'artist'
                trackDetails.remove 'album'
                album.tracks << trackDetails
                }
            }
            trackDetails = [:]
            if(dictLevel == 1) currentlyParsingTracks = false
            break
            case 'true':
            if(valueOfLastKeyParsed in ['TV Show', 'Has Video']) {
                trackDetails.isVideo = true
            }
        }
    }
}

