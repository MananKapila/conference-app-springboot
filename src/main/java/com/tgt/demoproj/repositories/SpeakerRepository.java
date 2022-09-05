package com.tgt.demoproj.repositories;

import com.tgt.demoproj.models.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpeakerRepository extends JpaRepository<Speaker,Long> {
}
