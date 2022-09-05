package com.tgt.demoproj.repositories;

import com.tgt.demoproj.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session,Long> {
}
