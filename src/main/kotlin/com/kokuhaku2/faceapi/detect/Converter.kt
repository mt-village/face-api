package com.kokuhaku2.faceapi.detect

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.apache.http.HttpEntity
import org.apache.http.util.EntityUtils
import org.springframework.stereotype.Component

@Component
class Converter {

    private var mapper: ObjectMapper = jacksonObjectMapper()

    fun toDetectResponse(entity: HttpEntity): Array<DetectResponse> {
        return mapper.readValue(toJson(entity))
    }

    private fun toJson(entity: HttpEntity) = EntityUtils.toString(entity).trim()

}