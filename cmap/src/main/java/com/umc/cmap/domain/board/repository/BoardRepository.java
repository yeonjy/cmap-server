package com.umc.cmap.domain.board.repository;

import com.umc.cmap.domain.board.entity.Board;
import com.umc.cmap.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findAllByRemovedAtIsNull(Pageable pageable);
    Page<Board> findByIdxInAndRemovedAtIsNull(List<Long> boardIdx, Pageable pageable);
    Page<Board> findByBoardTitleContainingOrBoardContentContainingAndRemovedAtIsNull(String boardTitle, String boardContent, Pageable pageable);
    Long countByUserIdxAndRemovedAtIsNull(Long userIdx);
    Optional<Board> findAllByUserIdxAndRemovedAtIsNull(Long userIdx);
}
