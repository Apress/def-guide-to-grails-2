package com.blog

import org.springframework.dao.DataIntegrityViolationException

class PostController {

    static allowedMethods = [save: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        [postInstanceList: Post.list(params)]
    }

    def create() {
        [postInstance: new Post(params)]
    }

    def save() {
        def postInstance = new Post()
        postInstance.properties['title', 'body'] = params
        if (!postInstance.save(flush: true)) {
            render(view: "create", model: [postInstance: postInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'post.label', default: 'Post'), postInstance.id])
        redirect(action: "list")
    }
}
