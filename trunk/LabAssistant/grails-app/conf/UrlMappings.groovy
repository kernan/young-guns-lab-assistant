class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
		
		"/login/$action?"(controller:"login")
		"/logout/$action?"(controller:"logout")
		
		"/user/list/$id"(controller:"user", action:"list")
		
		"/"(view:'/index')
		"500"(view:'/error')
	}
}
