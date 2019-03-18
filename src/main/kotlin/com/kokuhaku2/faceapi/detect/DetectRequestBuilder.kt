package com.kokuhaku2.faceapi.detect

import org.apache.http.client.methods.HttpPost
import org.apache.http.client.utils.URIBuilder
import org.apache.http.entity.StringEntity
import java.net.URI

class DetectRequestBuilder(private val subscriptionKey: String) {

    private val uriBase = "https://westcentralus.api.cognitive.microsoft.com/face/v1.0/detect"

    private val faceAttributes = arrayOf(
            "age",
            "gender", // male or female
            "smile", // 0 ~ 1
            "facialHair", // ヒゲ sideburns,beard,moustache (0 ~ 1)
            "headPose", // roll,pitch,yaw
            "glasses", // NoGlasses or Glasses
            "emotion", // contempt,surprise,happiness,neutral,sadness,disgust,anger,fear (0 ~ 1)
            "hair", // bald(0 ~ 1),invisible(true or false),hairColor{color(string),confidence(0 ~ 1)}
            "makeup",
            "occlusion",
            "accessories",
            "blur",
            "exposure",
            "noise"
    )



    private fun buildUri(): URI {
        return URIBuilder(uriBase).let {
            it.setParameter("returnFaceId", "true")
            it.setParameter("returnFaceLandmarks", "false")
            it.setParameter("returnFaceAttributes", faceAttributes.joinToString(",") { it })
            it.build()
        }
    }

    fun buildRequest(url: String): HttpPost {
        val urlAttribute = "{\"url\":\"$url\"}"
        return HttpPost(buildUri()).also {
            // Request headers.
            it.setHeader("Content-Type", "application/json")
            it.setHeader("Ocp-Apim-Subscription-Key", subscriptionKey)
            // Request body.
            val reqEntity = StringEntity(urlAttribute)
            it.entity = reqEntity
        }
    }
}