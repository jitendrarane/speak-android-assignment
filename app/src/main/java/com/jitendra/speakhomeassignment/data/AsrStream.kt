package com.jitendra.speakhomeassignment.data

import kotlinx.serialization.Serializable

@Serializable
data class AsrStream(
    val type: String,
    val chunk: String,
    val isFinal: Boolean? = null,
)
