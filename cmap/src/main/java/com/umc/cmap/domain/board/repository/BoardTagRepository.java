package com.umc.cmap.domain.board.repository;

import com.umc.cmap.domain.board.entity.BoardTag;
import com.umc.cmap.domain.board.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardTagRepository extends JpaRepository<BoardTag, Long> {
    List<Long> findTagIdxListByBoardIdx(Long boardIdx);
    List<Long> findBoardIdxByTagIn(List<Tag> tag);
}
