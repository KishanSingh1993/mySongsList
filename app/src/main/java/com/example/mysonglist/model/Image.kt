package com.example.mysonglist.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root
import org.simpleframework.xml.Text

@Root(name = "im:image", strict = false)
data class Image(
    @field:Attribute(name = "height", required = false)
    var height: String? = null,

    @field:Text(required = false)
    var url: String? = null


)
