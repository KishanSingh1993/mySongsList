package com.example.mysonglist.model

import com.example.mysonglist.model.Entry
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "feed", strict = false)
data class Feed(
    @field:ElementList(entry = "entry", inline = true)
    var entries: List<Entry>? = null
)

