package com.example.mysonglist.model

import org.simpleframework.xml.Root
import org.simpleframework.xml.Text

@Root(name = "title", strict = false)
data class Title(
    @Text var label: String = ""
)
