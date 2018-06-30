package com.kokuhaku2.faceapi.cloudinary

import com.cloudinary.Cloudinary
import com.cloudinary.Transformation
import com.cloudinary.utils.ObjectUtils
import org.junit.Test
import java.io.File


class UploadTest {

    val cloudinary: Cloudinary

    init {
        val config = mapOf(
                "cloud_name" to "replace_to_your_cloud_name",
                "api_key" to "replace_to_your_api_key",
                "api_secret" to "replace_to_your_api_secret"
        )
        cloudinary = Cloudinary(config)
    }

    @Test
    fun upload() {
        val uploaded = cloudinary.uploader().upload(File("images.jpeg"), ObjectUtils
                .emptyMap())
        println(uploaded)
        val baseId = "v${uploaded["version"]}"
        println(baseId)


    }

    @Test
    fun compose() {
        val uploaded = cloudinary.uploader().upload(File("E382A2E383B3E38389E383ADE382A4E38389.jpg"), ObjectUtils
                .emptyMap())
        println(uploaded)
        val baseId = "v${uploaded["version"]}"
        println(baseId)
    }

    @Test
    fun overlay() {
        cloudinary.uploader().upload(File("E382A2E383B3E38389E383ADE382A4E38389.jpg"), ObjectUtils.asMap("transformation",
                Transformation<Transformation<*>>().overlay("e3z64qnl3ltjejiupdbg")))
    }
}