package com.nhnacademy.jdbc.board.repository;

import com.nhnacademy.jdbc.board.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Like.Pk> {
}
