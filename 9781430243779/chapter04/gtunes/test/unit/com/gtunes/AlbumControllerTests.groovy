package com.gtunes

import grails.test.mixin.*
import grails.test.mixin.domain.*

@TestFor(AlbumController)
@Mock(Album)
class AlbumControllerTests {

    void testListAction() {
        new Album(title: 'Trilogy', year: 1972, genre: 'Prog Rock').save()
        new Album(title: 'Tarkus', year: 1971, genre: 'Prog Rock').save()

        def model = controller.list()
        assert model.albumList?.size() == 2
    }
}

