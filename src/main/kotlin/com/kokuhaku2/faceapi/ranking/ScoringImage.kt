package com.kokuhaku2.faceapi.ranking

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "scoring_images")
data class ScoringImage (

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int? = null,

        @Column(nullable = false)
        val url: String = "",

        @Column(nullable = false)
        val score: Int = 0

): Serializable