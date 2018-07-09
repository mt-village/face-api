package com.kokuhaku2.faceapi.controller

import com.kokuhaku2.faceapi.detect.DetectResponse
import com.kokuhaku2.faceapi.detect.DetectService
import com.kokuhaku2.faceapi.ranking.ScoringImage
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

    @PostMapping("funscore/source")
    @ResponseStatus(HttpStatus.CREATED)
    fun funScoreSource(@RequestBody body: Detect): Array<DetectResponse> {
        return service.getFunScoreSource(body.url)
    }

    @PostMapping("funscore/overlay")
    @ResponseStatus(HttpStatus.CREATED)
    fun funScoreOverlay(@RequestBody body: Detect): Overlay {
        return service.createOverlayImage(body.url)
    }

    @GetMapping("funscore/ranking")
    @ResponseStatus(HttpStatus.OK)
    fun getFunScoreRanking(@RequestParam n: Int): ScoringImage {
        return service.getRanked(n)
    }

    @DeleteMapping("funscore/ranking")
    @ResponseStatus(HttpStatus.OK)
    fun DeleteFunScoreRaning() {
        service.clearRanking()
    }
}