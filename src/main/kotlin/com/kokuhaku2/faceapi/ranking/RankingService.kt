package com.kokuhaku2.faceapi.ranking

import com.kokuhaku2.faceapi.controller.Overlay
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RankingService(@Autowired val repository: ScoringImageRepository) {

    fun save(overlay: Overlay) {
        val url = overlay.url ?: throw IllegalStateException()
        val previewUrl = overlay.previewUrl ?: throw IllegalStateException()
        repository.save(ScoringImage(null, url, previewUrl, overlay.score.totalScore))
    }

    fun clear() {
        repository.deleteAll()
    }

    fun getRanked(n: Int): ScoringImage {
        val images = repository.findAll()
        if (images.size <= n-1) {
            // サンプルを返す
            return ScoringImage(
                    id = -1,
                    url = "http://res.cloudinary.com/kogecoo/image/upload/v1530611426/h0vrdpdnondnd6pmlj1y.png",
                    score = 0
            )
        }
        return repository.findAll()
                .sortedWith(
                        compareByDescending<ScoringImage>{it.score}
                        // 点数が同じ場合は先に登録された方を優先する
                        .thenBy { it.id }
                )[n-1]
    }
}