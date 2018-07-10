package com.kokuhaku2.faceapi.cloudinary

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties("cloudinaly")
class CloudinarySettings {
    lateinit var cloudName: String
    lateinit var apiKey: String
    lateinit var apiSecret: String
    lateinit var overlayImagePixelLimit: String
    lateinit var meterPixelSize: String
    lateinit var previewMeterPixelSize: String
}