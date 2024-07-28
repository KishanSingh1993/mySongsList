package com.example.mysonglist.model

import org.simpleframework.xml.Root
import org.simpleframework.xml.Text

@Root(name = "id", strict = false)
data class Id(
    @Text var label: String = ""
)
