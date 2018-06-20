package com.kokuhaku2.faceapi

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("face-api")
class FaceApiSettings {
    lateinit var key: String
}