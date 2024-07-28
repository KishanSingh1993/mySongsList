package com.example.mysonglist.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "im:collection", strict = false)
data class Collection(
    @field:Element(name = "im:name", required = false)
    var name: String? = null,

    @field:Element(name = "link", required = false)
    var link: String? = null,

    @field:Element(name = "im:contentType", required = false)
    var contentType: ContentType? = null
)
