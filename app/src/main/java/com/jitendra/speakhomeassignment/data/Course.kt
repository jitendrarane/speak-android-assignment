package com.jitendra.speakhomeassignment.data

import kotlinx.serialization.Serializable

@Serializable
data class Course(
    val id: String,
    val info: Info,
    val units: List<Unit>,
)

@Serializable
data class Info(
    val title: String,
    val thumbnailImageUrl: String,
    val subtitle: String,
)

@Serializable
data class Unit(
    val id: String,
    val days: List<Day>,
)

@Serializable
data class Day(
    val id: String,
    val title: String,
    val thumbnailImageUrl: String,
    val subtitle: String,
    val lessons: List<Lesson>? = null,
)

@Serializable
data class Lesson(
    val id: String,
    val title: String,
    val durationMin: Int,
)
