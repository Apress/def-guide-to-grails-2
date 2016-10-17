class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

        "/" (controller: 'store')
		"500"(view:'/error')
	}
}
