package com.example.mysonglist.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "entry", strict = false)
data class Entry(
    @field:Element(name = "id", required = false)
    var id: String? = null,

    @field:Element(name = "title", required = false)
    var title: String? = null,

    @field:Element(name = "im:artist", required = false)
    var artist: String? = null,

    @field:Element(name = "im:price", required = false)
    var price: String? = null,

    @field:Element(name = "im:releaseDate", required = false)
    var releaseDate: String? = null,

    @field:ElementList(entry = "link", inline = true, required = false)
    var links: List<Link>? = null,

    @field:Element(name = "im:image", required = false)
    var images: List<Image>? = null
)

