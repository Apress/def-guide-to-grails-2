package com.gtunes

class StoreController {

    def index() {
    }

    def genre() {
        def max = Math.min(params.int('max') ?: 10, 100)
        def offset = params.int('offset') ?: 0

        def total = Album.countByGenre(params.name)
        def albumList = Album.withCriteria {
            eq 'genre', params.name
            projections {
                artist {
                    order 'name'
                }
            }
            maxResults max
            firstResult offset
        }
        [albums: albumList, totalAlbums: total, genre: params.name]
    }

    def shop() {
        def genreList = Album.withCriteria {
            projections {
                distinct 'genre'
            }
        }

        [top5Albums:Album.list(max:5, sort:"dateCreated", order:"desc"),
         top5Songs:Song.list(max:5, sort:"dateCreated", order:"desc"),
         top5Artists:Artist.list(max:5, sort:"dateCreated", order:"desc"),
         genres:genreList.sort()]
    }
}
