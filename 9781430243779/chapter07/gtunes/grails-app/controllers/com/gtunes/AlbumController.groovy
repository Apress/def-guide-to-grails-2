package com.gtunes

class AlbumController {
	def index() {}

    def show() {
        def album = Album.get(params.id)
        if(album) {
            [album: album]
        } else {
            response.sendError 404
        }
    }
}
