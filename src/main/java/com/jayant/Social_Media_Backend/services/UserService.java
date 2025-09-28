package com.jayant.Social_Media_Backend.services;

import com.jayant.Social_Media_Backend.Repositories.CommentRepository;
import com.jayant.Social_Media_Backend.Repositories.LikeRepository;
import com.jayant.Social_Media_Backend.Repositories.PostRepository;
import com.jayant.Social_Media_Backend.Repositories.UserRepository;
import com.jayant.Social_Media_Backend.entities.User;
import com.jayant.Social_Media_Backend.requests.UserRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;
    private LikeRepository likeRepository;
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public UserService(UserRepository userRepository, LikeRepository likeRepository, CommentRepository commentRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId).orElse(null);
    }

    public User updateUserById(Long userId , User newUser){
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            User foundUser = user.get();
            foundUser.setUsername(newUser.getUsername());
            foundUser.setPassword(newUser.getPassword());
            foundUser.setImage(newUser.getImage());
            userRepository.save(foundUser);
            return foundUser;
        }
        else return null;
    }
    public void deleteUserById(Long userId){
        userRepository.deleteById(userId);
    }
    public List<Object> getUserActivityById(Long userId){
        List<Long> postIds = postRepository.findTopByUserId(userId);
        if(postIds.isEmpty()){
            return null;
        }
        List<Object> comments = commentRepository.findUserCommentsByPostId(postIds);
        List<Object> likes = likeRepository.findUserLikesByPostId(postIds);
        List<Object> results = new ArrayList<>();
        results.addAll(comments);
        results.addAll(likes);
        return results;

    }

}
