package com.kokuhaku2.faceapi.detect

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown=true)
data class DetectResponse(val faceAttributes: FaceAttributes)

@JsonIgnoreProperties(ignoreUnknown=true)
data class FaceAttributes(
        val gender: String,
        val smile: Double,
        val emotion: Emotion
)

data class Emotion(
        val contempt: Double,
        val surprise: Double,
        val happiness: Double,
        val neutral: Double,
        val sadness: Double,
        val disgust: Double,
        val anger: Double,
        val fear: Double
)