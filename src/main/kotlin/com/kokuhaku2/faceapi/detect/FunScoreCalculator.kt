package com.kokuhaku2.faceapi.detect

import com.kokuhaku2.faceapi.controller.FunScore
import org.springframework.stereotype.Component

@Component
class FunScoreCalculator {
    fun calc(detects: Array<DetectResponse>): FunScore {
        val scoreList = detects.map { score(it) }
        return FunScore(avrScore = scoreList.average(), sumScore = scoreList.sum())
    }

    private fun score(detect: DetectResponse): Double {
        return detect.faceAttributes.smile
    }
}