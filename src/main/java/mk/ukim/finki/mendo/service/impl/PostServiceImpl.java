package mk.ukim.finki.mendo.service.impl;

import mk.ukim.finki.mendo.model.Post;
import mk.ukim.finki.mendo.model.Thread;
import mk.ukim.finki.mendo.repository.PostRepository;
import mk.ukim.finki.mendo.service.AuthorizationService;
import mk.ukim.finki.mendo.service.MendoUserService;
import mk.ukim.finki.mendo.service.PostService;
import mk.ukim.finki.mendo.service.ThreadService;
import mk.ukim.finki.mendo.web.request.PostRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ThreadService threadService;
    private final MendoUserService user;
    private final AuthorizationService authorizationService;

    public PostServiceImpl(PostRepository postRepository, ThreadService threadService, MendoUserService user, AuthorizationService authorizationService) {
        this.postRepository = postRepository;
        this.threadService = threadService;
        this.user = user;
        this.authorizationService = authorizationService;
    }

    @Override
    public Post findById(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    @Override
    public Post addPostToThread(Long threadId, PostRequest postRequest) {
        authorizationService.isAuthenticated();

        Thread thread = threadService.findById(threadId);
        Post post = postRepository.save(new Post(postRequest.getDescription(), postRequest.getParent(), thread, user.getCurrentUser().orElse(null)));
        return postRepository.save(post);
    }

    @Override
    public Post replyToPost(Long postId, PostRequest postRequest) {
        authorizationService.isAuthenticated();
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

    @Override
    public List<Post> findAllPostsByThreadId(Long id) {
        return postRepository.findAllByThread_Id(id);
    }
}
