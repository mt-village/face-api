package com.kokuhaku2.faceapi

import org.apache.http.client.fluent.Request
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun downloadImgage(url: String):BufferedImage {
    return download(url).let { ImageIO.read(it) }
}

fun download(url: String):File {
    val tmpFile = File("tmp")
    Request.Get(url).execute()
            .saveContent(tmpFile)
    return tmpFile
}