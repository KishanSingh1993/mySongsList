package com.example.mysonglist.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "attributes", strict = false)
data class LinkAttributes(
    @field:Element(name = "href")
    var href: String? = null
)
