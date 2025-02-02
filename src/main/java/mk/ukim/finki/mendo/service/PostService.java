package mk.ukim.finki.mendo.service;

import mk.ukim.finki.mendo.model.Post;
import mk.ukim.finki.mendo.model.Thread;
import mk.ukim.finki.mendo.web.request.PostRequest;

public interface PostService {

    public Post findById(Long postId);
    public Post addPostToThread(Long threadId, PostRequest postRequest);
    public Post replyToPost(Long postId, PostRequest postRequest);

}
