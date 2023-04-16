package dev.szi.danvega.blog.controller;

import dev.szi.danvega.blog.model.Post;
import dev.szi.danvega.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/posts")
public class PostController {

    /*@Autowired
    private PostRepository postRepository;  //field level injection  */
    private final PostRepository postRepository;

    //constructor level injection
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // Get all posts
    @GetMapping
    public List<Post> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts;
    }

    // Get a post by ID
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id) {
        Post post = postRepository.findById(id).orElse(null);
        return post;
    }

    // Create a new post
    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postRepository.save(post);
    }

    // Update a post
    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post post) {
        Optional<Post> postOptional  = postRepository.findById(id);
        Post updatedPost = null;
        if (postOptional.isPresent()) {
            Post foundPost = postOptional.get();
            foundPost.setTitle(post.getTitle());
            foundPost.setContent(post.getContent());
            foundPost.setSlug(post.getSlug());
            foundPost.setDateUpdated(post.getDateUpdated());
            updatedPost = postRepository.save(foundPost);
        }
        return updatedPost;
    }

    @DeleteMapping ("/{id}")
    public void deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
    }
}
