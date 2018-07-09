package com.kokuhaku2.faceapi.ranking

import com.kokuhaku2.faceapi.controller.Overlay
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RankingService(@Autowired val repository: ScoringImangeRepository) {

    fun save(overlay: Overlay) {
        val url = overlay.url ?: throw IllegalStateException()
        repository.save(ScoringImage(url, overlay.score.totalScore))
    }

    fun clear() {
        repository.deleteAll()
    }

    fun getRanked(n: Int): ScoringImage {
        val images = repository.findAll()
        if (images.isEmpty()) {
            // サンプルを返す
            return ScoringImage(
                    url = "http://res.cloudinary.com/kogecoo/image/upload/v1530611426/h0vrdpdnondnd6pmlj1y.png",
                    score = 0

            )
        }
        return repository.findAll()
                .sortedByDescending { it.score }[n-1]
    }
}