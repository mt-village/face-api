package com.kokuhaku2.faceapi.detect

import com.kokuhaku2.faceapi.controller.FunScore
import org.springframework.stereotype.Component
import java.awt.image.BufferedImage
import kotlin.math.absoluteValue

@Component
class FunScoreCalculator {
    fun calc(image: BufferedImage, detects: Array<DetectResponse>): FunScore {
        val cntPeople = detects.size
        val smileAverage = smileAverage(detects)
        val faceAreaRatio = faceAreaSum(detects).toDouble() / (image.width * image.height)
        val genderRatio = genderRatio(detects)
        val smileScore = (smileAverage * 80).toInt()
        val peopleScore = when(cntPeople) {
            0,1 -> 0
            2,3 -> 1
            4,5 -> 3
            6,7 -> 5
            8,9 -> 6
            else -> 7
        }
        val diff = (genderRatio.cntMale - genderRatio.cntFemale).absoluteValue
        val genderScore = when {
            genderRatio.cntMale == 0 || genderRatio.cntFemale == 0 -> 0
            diff > 4 -> 0
            diff == 3 -> 1
            diff == 2 -> 1
            diff == 1 -> 2
            diff == 0 -> 3
            else -> 0
        }
        val areaScore = when {
            faceAreaRatio > 0.20 -> 10
            faceAreaRatio > 0.15 -> 9
            faceAreaRatio > 0.10 -> 8
            faceAreaRatio > 0.09 -> 7
            faceAreaRatio > 0.07 -> 6
            faceAreaRatio > 0.05 -> 5
            faceAreaRatio > 0.04 -> 4
            faceAreaRatio > 0.03 -> 3
            faceAreaRatio > 0.02 -> 2
            faceAreaRatio > 0.01 -> 1
            else -> 0
        }
        val totalScore = smileScore + peopleScore + genderScore + areaScore
        return FunScore(totalScore, smileScore, peopleScore, genderScore, areaScore, faceAreaRatio, smileAverage, genderRatio)
    }

    private fun smileAverage(detects: Array<DetectResponse>): Double {
        return detects.map { it.faceAttributes.smile }
                .average()
    }

    private fun faceAreaSum(detects: Array<DetectResponse>): Int {
        return detects.map { it.faceRectangle }
                .map { faceArea(it) }
                .sum()
    }

    private fun faceArea(rectangle: FaceRectangle): Int {
        return rectangle.width * rectangle.height
    }

    private fun genderRatio(detects: Array<DetectResponse>): GenderRatio {
        val cntMale = detects.count { it.faceAttributes.gender == Gender.male }
        val cntFemale = detects.count { it.faceAttributes.gender == Gender.female }

        return GenderRatio(cntMale, cntFemale)
    }

    data class GenderRatio(val cntMale: Int, val cntFemale: Int)
}