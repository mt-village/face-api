package com.kokuhaku2.faceapi.ranking

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "scoring_images")
data class ScoringImage (

        @Id
        val url: String,

        @Column(nullable = false)
        val score: Int
)