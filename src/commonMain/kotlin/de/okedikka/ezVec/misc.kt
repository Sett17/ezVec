package de.okedikka.ezVec

import kotlin.math.PI

fun Number.toRadians(): Double = this.toDouble() * PI / 180.0
fun Number.toDegrees(): Double = this.toDouble() * 180.0 / PI