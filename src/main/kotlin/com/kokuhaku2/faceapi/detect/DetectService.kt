package com.kokuhaku2.faceapi.detect

import com.kokuhaku2.faceapi.*
import com.kokuhaku2.faceapi.FaceApiService
import com.kokuhaku2.faceapi.controller.FunScore
import org.apache.http.util.EntityUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DetectService(
        @Autowired val apiService: FaceApiService,
        @Autowired val converter: Converter,
        @Autowired val calculator: FunScoreCalculator,
        @Autowired val settings: FaceApiSettings) {

    fun getDetects(url: String): String {
        val request = DetectRequestBuilder(settings.key)
                .buildRequest(url)
        return apiService.post(request)
                .let { EntityUtils.toString(it) }
//                .let { converter.toDetectResponse(it) }
    }

    fun getFunScore(url: String): FunScore {
        val request = DetectRequestBuilder(settings.key)
                .buildRequest(url)
        return apiService.post(request)
                .let { converter.toDetectResponse(it) }
                .let { calculator.calc(it) }
    }
}