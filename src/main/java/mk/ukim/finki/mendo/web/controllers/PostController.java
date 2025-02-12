package mk.ukim.finki.mendo.web.controllers;

import mk.ukim.finki.mendo.model.Post;
import mk.ukim.finki.mendo.service.AuthorizationService;
import mk.ukim.finki.mendo.service.PostService;
import mk.ukim.finki.mendo.service.ThreadService;
import mk.ukim.finki.mendo.web.request.PostRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final ThreadService threadService;
    private final AuthorizationService authorizationService;

    public PostController(PostService postService, ThreadService threadService, AuthorizationService authorizationService) {
        this.postService = postService;
        this.threadService = threadService;
        this.authorizationService = authorizationService;
    }

    @PostMapping("/{threadId}")
    public String addPostToThread( @PathVariable Long threadId, PostRequest postRequest, Model model) {
        Post post = postService.addPostToThread(threadId, postRequest);
        model.addAttribute("post", post);
        model.addAttribute("thread", post.getThread());
        return "redirect:/activities/tasks/" + threadId;
    }

    @PostMapping("/reply/{postId}")
    public String createReply(@PathVariable Long postId,
                              PostRequest postRequest,
                              Model model) {
        Post parentPost = postService.findById(postId);
        Post replyToPost = postService.replyToPost(postId, postRequest);

        model.addAttribute("post", replyToPost);
        model.addAttribute("parentPost", parentPost);
        return "master";
    }


}