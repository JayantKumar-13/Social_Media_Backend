package com.jayant.Social_Media_Backend.services;

import com.jayant.Social_Media_Backend.Dto.LikeResponse;
import com.jayant.Social_Media_Backend.Repositories.LikeRepository;
import com.jayant.Social_Media_Backend.entities.Like;
import com.jayant.Social_Media_Backend.entities.Post;
import com.jayant.Social_Media_Backend.entities.User;
import com.jayant.Social_Media_Backend.requests.CreateLikeRequest;
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

        return list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());
    }

    public Like getLikeById(Long LikeId){
        return likeRepository.findById(LikeId).orElse(null);
    }

    public Like createLike(CreateLikeRequest createLikeRequest){
        User user = userService.getUserById(createLikeRequest.getUserId());
        Post post = postService.getPostById(createLikeRequest.getUserId());

        if(user != null && post != null){
            Like like = new Like();
            like.setId(createLikeRequest.getId());
            like.setPost(post);
            like.setUser(user);
            return likeRepository.save(like);
        }
        else return null;
    }

    public void deleteLikeById(Long likeId){
        likeRepository.deleteById(likeId);
    }

}
