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

    def display() {
        def album = Album.get(params.id)
        if(album) {
            def artist = album.artist
            render template: 'album', model: [artist: artist, album: album]
        } else {
            render 'Album not found.'
        }
    }
}
