package com.kokuhaku2.faceapi.ranking

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ScoringImageRepository: JpaRepository<ScoringImage, Int>