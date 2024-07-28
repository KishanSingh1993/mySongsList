package com.example.mysonglist.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "category", strict = false)
data class Category(
    @field:Attribute(name = "term")
    var term: String? = null,

    @field:Attribute(name = "scheme")
    var scheme: String? = null,

    @field:Attribute(name = "label")
    var label: String? = null
)
