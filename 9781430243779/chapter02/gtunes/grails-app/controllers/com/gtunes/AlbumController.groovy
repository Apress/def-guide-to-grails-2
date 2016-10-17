package com.gtunes

import org.springframework.dao.DataIntegrityViolationException

class AlbumController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [albumInstanceList: Album.list(params), albumInstanceTotal: Album.count()]
    }

    def create() {
        [albumInstance: new Album(params)]
    }

    def save() {
        def albumInstance = new Album(params)
        if (!albumInstance.save(flush: true)) {
            render(view: "create", model: [albumInstance: albumInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'album.label', default: 'Album'), albumInstance.id])
        redirect(action: "show", id: albumInstance.id)
    }

    def show() {
        def albumInstance = Album.get(params.id)
        if (!albumInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'album.label', default: 'Album'), params.id])
            redirect(action: "list")
            return
        }

        [albumInstance: albumInstance]
    }

    def edit() {
        def albumInstance = Album.get(params.id)
        if (!albumInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'album.label', default: 'Album'), params.id])
            redirect(action: "list")
            return
        }

        [albumInstance: albumInstance]
    }

    def update() {
        def albumInstance = Album.get(params.id)
        if (!albumInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'album.label', default: 'Album'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (albumInstance.version > version) {
                albumInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'album.label', default: 'Album')] as Object[],
                          "Another user has updated this Album while you were editing")
                render(view: "edit", model: [albumInstance: albumInstance])
                return
            }
        }

        albumInstance.properties = params

        if (!albumInstance.save(flush: true)) {
            render(view: "edit", model: [albumInstance: albumInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'album.label', default: 'Album'), albumInstance.id])
        redirect(action: "show", id: albumInstance.id)
    }

    def delete() {
        def albumInstance = Album.get(params.id)
        if (!albumInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'album.label', default: 'Album'), params.id])
            redirect(action: "list")
            return
        }

        try {
            albumInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'album.label', default: 'Album'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'album.label', default: 'Album'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
