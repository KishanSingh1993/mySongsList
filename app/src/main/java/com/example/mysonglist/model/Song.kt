package com.example.mysonglist.model

import com.example.mysonglist.model.Image
import com.example.mysonglist.model.Link
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "entry", strict = false)
data class Song(
    @field:Element(name = "title")
    var title: String? = null,

    @field:Element(name = "id")
    var id: String? = null,

    @field:Element(name = "link", required = false)
    var link: Link? = null,

    @field:Element(name = "im:image", required = false)
    var image: Image? = null




//    @field:Element(name = "id", required = false)
//    var id: String? = null,
//
//    @field:Element(name = "title", required = false)
//    var title: String? = null,
//
//    @field:Element(name = "im:image", required = false)
//    var image: List<Image>? = null,

//    @field:Element(name = "link", required = false)
//    var link: Link? = null
)

