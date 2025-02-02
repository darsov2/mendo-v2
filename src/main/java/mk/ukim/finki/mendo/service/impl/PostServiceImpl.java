package mk.ukim.finki.mendo.service.impl;

import mk.ukim.finki.mendo.model.Post;
import mk.ukim.finki.mendo.model.Thread;
import mk.ukim.finki.mendo.repository.PostRepository;
import mk.ukim.finki.mendo.service.MendoUserService;
import mk.ukim.finki.mendo.service.PostService;
import mk.ukim.finki.mendo.service.ThreadService;
import mk.ukim.finki.mendo.web.request.PostRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ThreadService threadService;
    private final MendoUserService user;

    public PostServiceImpl(PostRepository postRepository, ThreadService threadService, MendoUserService user) {
        this.postRepository = postRepository;
        this.threadService = threadService;
        this.user = user;
    }

    @Override
    public Post findById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    @Override
    public Post addPostToThread(Long threadId, PostRequest postRequest) {

        Thread thread = threadService.findById(threadId);
        Post post = postRepository.save(new Post(postRequest.getDescription(), postRequest.getParent(), thread, user.getCurrentUser().orElse(null)));
        return postRepository.save(post);
    }

    @Override
    public Post replyToPost(Long postId, PostRequest postRequest) {
        Post parentPost = postRepository.findById(postId).orElse(null);

        Post replyPost = new Post();
        replyPost.setDescription(postRequest.getDescription());
        replyPost.setUpvote(0);
        replyPost.setTimestamp(LocalDateTime.now());
        replyPost.setParent(parentPost);
        replyPost.setThread(parentPost.getThread());
        replyPost.setMendoUser(user.getCurrentUser().orElse(null));

        return postRepository.save(replyPost);

    }
}
