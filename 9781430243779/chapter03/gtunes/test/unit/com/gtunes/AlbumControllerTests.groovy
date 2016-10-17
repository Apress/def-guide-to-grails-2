package com.gtunes

import grails.test.mixin.*
import grails.test.mixin.domain.*

@TestFor(AlbumController)
@TestMixin(DomainClassUnitTestMixin)
class AlbumControllerTests {

    void testListAction() {
        /*
        Mock the Album domain class and provide a couple of Maps 
        that will be used to instantiate instances of the Album 
        class and add them to the unit test datastore.
         */
        mockDomain(Album, [[title: 'Trilogy', year: 1972, genre: 'Prog Rock'], [title: 'Tarkus', year: 1971, genre: 'Prog Rock']])

        def model = controller.list()
        assert model.albumList?.size() == 2
    }
}

