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
)

data class Overlay(val url: String)