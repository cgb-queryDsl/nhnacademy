package com.nhnacademy.jdbc.board.repository;

import com.nhnacademy.jdbc.board.entity.Uuid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UuidRepository extends JpaRepository<Uuid, Long> {
}
