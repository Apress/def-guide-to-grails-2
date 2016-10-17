package com.gtunes

class AlbumController {
	def index() {}

    def list() {
        [albumList: Album.list()]
    }
}
