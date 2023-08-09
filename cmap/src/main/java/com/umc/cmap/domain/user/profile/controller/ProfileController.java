package com.umc.cmap.domain.user.profile.controller;

import com.umc.cmap.config.BaseException;
import com.umc.cmap.domain.review.dto.ReviewResponse;
import com.umc.cmap.domain.review.repository.ReviewRepository;
import com.umc.cmap.domain.user.entity.User;
import com.umc.cmap.domain.user.login.service.AuthService;
import com.umc.cmap.domain.user.profile.dto.ProfileRequest;
import com.umc.cmap.domain.user.profile.dto.ProfileResponse;
import com.umc.cmap.domain.user.profile.service.ProfileService;
import com.umc.cmap.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@RestController
@RequiredArgsConstructor
public class ProfileController {
    private final UserRepository userRepository;
    private final ProfileService profileService;
    private final AuthService authService;
    private final ReviewRepository reviewService;

    @GetMapping("/users/profile/{userNickname}")
    public ProfileResponse profile(@PathVariable String userNickname) throws BaseException{
        return profileService.getOne(userNickname);
    }

    //@PatchMapping("/users/profile/{userNickname}")
    public String editProfile(@PathVariable String userNickname, @RequestBody ProfileRequest profileRequest) throws BaseException {
        User user = authService.getUser();
        if(user.getNickname().equals(userNickname)){
            ProfileResponse profileResponse = profileService.update(userNickname, profileRequest);
        }

        return "redirect:/users/profile/{userNickname}";
    }

    @PatchMapping("/users/profile/{userNickname}")
    public String editProfileT(@PathVariable String userNickname, @RequestBody ProfileRequest profileRequest) throws BaseException {

        if (profileRequest.getUserNickname().trim().isEmpty()) {
            // 닉네임이 비어있는 경우
            return "닉네임을 입력해주세요.";
        }
        else if(userRepository.findByNickname(profileRequest.getUserNickname()).isPresent()){
            //중복 처리
            return "이미 사용 중인 닉네임입니다.";
        }
        ProfileResponse profileResponse = profileService.update(userNickname, profileRequest);

        return "redirect:/users/profile/" + profileResponse.getUserNickname();
    }

    @GetMapping("/users/profile/{userNickname}/reviews")
    public List<ReviewResponse> userReview() throws BaseException{
        User user = authService.getUser();
        return reviewService.getAllUserReviews(user.getIdx(), @PageableDefault(sort = "createdAt", direction = DESC) Pageable pageable);
    }
}
