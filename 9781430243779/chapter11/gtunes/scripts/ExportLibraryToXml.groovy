includeTargets << grailsScript("_GrailsBootstrap")

target(main: "Exports the gTunes database to XML") {
    depends bootstrap
    def file = argsMap.params ?
               new File(argsMap.params[0]) : 
               new File("./gtunes-data=${System.currentTimeMillis()}.xml")
    def artistClass = grailsApp.classLoader.loadClass('com.gtunes.Artist')
    def artistCount = artistClass.count()
    println "Creating XML for ${artistCount} artists"
    new FileWriter(file) << new groovy.xml.StreamingMarkupBuilder().bind {
        music {
            artistClass.withTransaction {
                artistClass.withSession { session ->
                    0.step(artistCount, 10) { offset ->
                        def artistList = artistClass.list(offset: offset, max: 10, fetch: [albums: 'join'])
                        for(currentArtist in artistList) {
                            artist(name: currentArtist.name) {
                                for(currentAlbum in currentArtist.albums) {
                                    album(currentAlbum.properties['title', 'year', 'genre', 'price']) {
                                        for(currentSong in currentAlbum.songs) {
                                            song(currentSong.properties['title', 'duration'])
                                        }
                                    }
                                }
                            }
                        }
                        session.clear()
                    }
                }
            }
        }
    }
    println "Done. Created XML ${file.absolutePath}"
}

setDefaultTarget(main)
