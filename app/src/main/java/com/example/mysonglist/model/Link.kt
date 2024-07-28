package com.example.mysonglist.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

//@Root(name = "link", strict = false)
//data class Link(
//    @field:Attribute(name = "href", required = false)
//    var href: String? = null
//)

@Root(name = "link", strict = false)
data class Link(
    @field:Element(name = "type", required = false)
    var type: String? = null,

    @field:Element(name = "href", required = false)
    var href: String? = null
)
