package com.example.lupicapp.data.model

import java.util.UUID

data class DrugItem(
    val id: String = UUID.randomUUID().toString(),
    val name: String = "",
    var pillsADay: String ="",
    var timesADay: String ="",
    var firstPeriod: String = "",
    var secondPeriod: String = "",
    var totalPills: String = ""
)