package com.kokuhaku2.faceapi.controller

import com.kokuhaku2.faceapi.detect.FunScoreCalculator

data class FunScore(
        val totalScore: Int,
        val smileScore: Int,
        val peopleScore: Int,
        val genderScore: Int,
        val areaScore: Int,
        val faceAreaRatio: Double,
        val smileAverage: Double,
        val genderRatio: FunScoreCalculator.GenderRatio
) {
    fun detected() = totalScore > 0
}



data class Overlay(val detected: Boolean, val url: String?, val previewUrl: String?, val score: FunScore)