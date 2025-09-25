package com.jayant.Social_Media_Backend.Dto;

import com.jayant.Social_Media_Backend.entities.Post;
import lombok.Data;

import java.util.List;
@Data
public class PostResponse {

    private Long id;
    private Long userId;
    private String username;
    private String text;
    private String title;
    private List<LikeResponse> postLikes;

    public PostResponse(Post post, List<LikeResponse> likes){
        this.id=post.getId();
        this.userId=post.getUser().getId();
        this.username=post.getUser().getUsername();
        this.title=post.getTitle();
        this.text=post.getText();
        this.postLikes=likes;
    }
}
