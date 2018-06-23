package com.kokuhaku2.faceapi.controller

import com.kokuhaku2.faceapi.detect.DetectResponse
import com.kokuhaku2.faceapi.detect.DetectService
import com.sun.tools.corba.se.idl.StringGen
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("detect")
class DetectController(@Autowired val service: DetectService) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun detect(@RequestBody body: Detect): String {
        return service.getDetects(body.url)
    }

    @PostMapping("funscore")
    @ResponseStatus(HttpStatus.CREATED)
    fun funScore(@RequestBody body: Detect): FunScore {
        return service.getFunScore(body.url)
    }
}