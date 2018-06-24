package com.kokuhaku2.faceapi.detect

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown=true)
data class DetectResponse(
        val faceRectangle: FaceRectangle,
        val faceAttributes: FaceAttributes
)

@JsonIgnoreProperties(ignoreUnknown=true)
data class FaceAttributes(
        val gender: Gender,
        val smile: Double,
        val emotion: Emotion,
        val occlusion: Occlusion
)

enum class Gender{ male, female}

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

data class Occlusion(
        val eyeOccluded: Boolean,
        val mouthOccluded: Boolean,
        val foreheadOccluded: Boolean
)

data class FaceRectangle(
        val top: Int,
        val left: Int,
        val width: Int,
        val height: Int
)