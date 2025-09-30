package com.jayant.Social_Media_Backend.services;

import com.jayant.Social_Media_Backend.Dto.LikeResponse;
import com.jayant.Social_Media_Backend.Repositories.LikeRepository;
import com.jayant.Social_Media_Backend.entities.Like;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {

    private LikeRepository likeRepository;
    private UserService userService;
    private PostService postService;

    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public List<LikeResponse> getAllLikes(Optional<Long> userId, Optional<Long> postId) {
        List<Like> list;
        if(userId.isPresent() && postId.isPresent()){
            list = likeRepository.findByUserIdAndPostId(userId.get() , postId.get());
        }
        else if(userId.isPresent()){
            list = likeRepository.findByUserId(userId.get());
        }
        else list = likeRepository.findAll();

        return list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());1
    }
}
