package com.example.divisionsearch_usingretrofil

import java.io.Serializable

data class DivisionResponseItem(
    val districts: List<District>,
    val id: Int,
    val name: String
): Serializable