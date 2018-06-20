package com.kokuhaku2.faceapi

import org.apache.http.HttpEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.DefaultHttpClient
import org.springframework.stereotype.Service

@Service
class FaceApiService {

    fun post(request: HttpPost): HttpEntity {
        val httpClient = DefaultHttpClient()
        val response = httpClient.execute(request)
        return response.entity
    }
}