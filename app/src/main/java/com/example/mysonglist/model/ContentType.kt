package com.example.mysonglist.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "im:contentType", strict = false)
data class ContentType(
    @field:Attribute(name = "term")
    var term: String? = null,

    @field:Attribute(name = "label")
    var label: String? = null,

)
