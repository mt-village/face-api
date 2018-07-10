package com.kokuhaku2.faceapi.cloudinary

import com.cloudinary.Cloudinary
import com.cloudinary.Transformation
import com.kokuhaku2.faceapi.controller.FunScore
import com.kokuhaku2.faceapi.controller.Overlay
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CloudinaryService(
        @Autowired val settings: CloudinarySettings) {

    val cloudinary: Cloudinary

    init {
        val config = mapOf(
                "cloud_name" to settings.cloudName,
                "api_key" to settings.apiKey,
                "api_secret" to settings.apiSecret
        )
        cloudinary = Cloudinary(config)
    }

    /**
     * @return 採点上書き済み画像のURL
     */
    fun overlayScore(url: String, score: FunScore): Overlay {
        if (!score.detected()) {
            return Overlay(detected = false, url = null, score = score)
        }
        var imageTag = url
                .replace("https://res.cloudinary.com/kogecoo/image/upload/", "remote_media/")
                .replace("http://res.cloudinary.com/kogecoo/image/upload/", "remote_media/")
        val res = cloudinary.url()
                .transformation(createTransformation()
                .width(640)
                .crop("scale")
                .chain()
                .overlay(getMeterImageName(score.totalScore))
                .width(settings.meterPixelSize)
                .crop("scale")
                .gravity("south_east")
                .chain()
                .overlay(getScoreImageName(score.totalScore))
                .width(settings.meterPixelSize)
                .crop("scale")
                .gravity("south_east")
        ).imageTag(imageTag)
        println(res)
        return Overlay(detected = true, url = extraUrl(res), score = score)
    }

    fun createTransformation(): Transformation<Transformation<*>> {
        return Transformation()
    }

    val meterPrefix = "fun_score_meter_"

    fun getMeterImageName(score: Int): String {
        return when(score) {
            in 0..10 -> meterPrefix + "10"
            in 11..20 -> meterPrefix + "20"
            in 21..30 -> meterPrefix + "30"
            in 31..40 -> meterPrefix + "40"
            in 41..50 -> meterPrefix + "50"
            in 51..60 -> meterPrefix + "60"
            in 61..70 -> meterPrefix + "70"
            in 71..80 -> meterPrefix + "80"
            in 81..90 -> meterPrefix + "90"
            in 91..100 -> meterPrefix + "100"
            else -> meterPrefix + "10"
        }
    }

    val scorePrefix = "wedding_scr_"

    fun getScoreImageName(score: Int): String {
        return scorePrefix + score.toString()
    }

    fun extraUrl(imageTag: String): String {
        return imageTag
                .substringAfter("'")
                .substringBefore("'")
    }
}