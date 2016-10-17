package com.gtunes



import org.junit.*
import grails.test.mixin.*

@TestFor(AlbumController)
@Mock(Album)
class AlbumControllerTests {


    def populateValidParams(params) {
      assert params != null
      // TODO: Populate valid properties like...
      //params["name"] = 'someValidName'

      params['title'] = 'Some Title'
    }

    void testIndex() {
        controller.index()
        assert "/album/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.albumInstanceList.size() == 0
        assert model.albumInstanceTotal == 0
    }

    void testCreate() {
       def model = controller.create()

       assert model.albumInstance != null
    }

    void testSave() {
        controller.save()

        assert model.albumInstance != null
        assert view == '/album/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/album/show/1'
        assert controller.flash.message != null
        assert Album.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/album/list'


        populateValidParams(params)
        def album = new Album(params)

        assert album.save() != null

        params.id = album.id

        def model = controller.show()

        assert model.albumInstance == album
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/album/list'


        populateValidParams(params)
        def album = new Album(params)

        assert album.save() != null

        params.id = album.id

        def model = controller.edit()

        assert model.albumInstance == album
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/album/list'

        response.reset()


        populateValidParams(params)
        def album = new Album(params)

        assert album.save() != null

        // test invalid parameters in update
        params.id = album.id
        //TODO: add invalid values to params object
        params['title'] = ' '

        controller.update()

        assert view == "/album/edit"
        assert model.albumInstance != null

        album.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/album/show/$album.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        album.clearErrors()

        populateValidParams(params)
        params.id = album.id
        params.version = -1
        controller.update()

        assert view == "/album/edit"
        assert model.albumInstance != null
        assert model.albumInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/album/list'

        response.reset()

        populateValidParams(params)
        def album = new Album(params)

        assert album.save() != null
        assert Album.count() == 1

        params.id = album.id

        controller.delete()

        assert Album.count() == 0
        assert Album.get(album.id) == null
        assert response.redirectedUrl == '/album/list'
    }
}
