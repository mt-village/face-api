package com.kokuhaku2.faceapi.detect

import com.kokuhaku2.faceapi.*
import com.kokuhaku2.faceapi.FaceApiService
import com.kokuhaku2.faceapi.cloudinary.CloudinaryService
import com.kokuhaku2.faceapi.controller.FunScore
import com.kokuhaku2.faceapi.controller.Overlay
import org.apache.http.util.EntityUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DetectService(
        @Autowired val apiService: FaceApiService,
        @Autowired val converter: Converter,
        @Autowired val calculator: FunScoreCalculator,
        @Autowired val cloudinaryService: CloudinaryService,
        @Autowired val settings: FaceApiSettings) {

    fun getDetects(url: String): String {
        return DetectRequestBuilder(settings.key).buildRequest(url)
                .let { apiService.post(it) }
                .let { EntityUtils.toString(it) }
    }

    fun getFunScoreSource(url: String): Array<DetectResponse> {
        return DetectRequestBuilder(settings.key).buildRequest(url)
                .let { apiService.post(it) }
                .let { converter.toDetectResponse(it) }
    }

    fun getFunScore(url: String): FunScore {
        val image = downloadImgage(url)
        return getFunScoreSource(url)
                .let { calculator.calc(image, it) }
    }

    fun createOverlayImage(url: String): Overlay {
        val funScore = getFunScore(url)
        return cloudinaryService.overlayScore(url, funScore)
    }
}