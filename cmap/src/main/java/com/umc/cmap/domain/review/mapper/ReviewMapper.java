package com.umc.cmap.domain.review.mapper;

import com.umc.cmap.domain.review.dto.ReviewResponse;
import com.umc.cmap.domain.review.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ReviewMapper {
    ReviewResponse toResponse(Review review);
}
