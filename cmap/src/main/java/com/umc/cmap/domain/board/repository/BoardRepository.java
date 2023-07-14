package com.umc.cmap.domain.board.repository;

import com.umc.cmap.domain.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> loadBoard(Long id);
}
