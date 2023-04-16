package dev.szi.danvega.blog.controller;

import dev.szi.danvega.blog.model.Post;
import dev.szi.danvega.blog.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PostControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostRepository postRepository;

    @BeforeEach
    public void setUp() {
        postRepository.deleteAll();

        Post post1 = new Post("Test Post 1", "This is the content of the test post 1.", "test-post-1");
        postRepository.save(post1);

        Post post2 = new Post("Test Post 2", "This is the content of the test post 2.", "test-post-2");
        postRepository.save(post2);
    }

    @Test
    public void getAllPosts() {
        ResponseEntity<Post[]> responseEntity = restTemplate.getForEntity("/api/posts", Post[].class);
        Post[] posts = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(posts).hasSize(2);
        assertThat(posts[0].getTitle()).isEqualTo("Test Post 1");
        assertThat(posts[1].getTitle()).isEqualTo("Test Post 2");
    }

    @Test
    public void getPostById() {
        Post existingPost = postRepository.findAll().get(0);
        Long postId = existingPost.getId();

        ResponseEntity<Post> responseEntity = restTemplate.getForEntity("/api/posts/" + postId, Post.class);
        Post post = responseEntity.getBody();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(post).isNotNull();
        assertThat(post.getTitle()).isEqualTo("Test Post 1");
    }

    @Test
    public void updatePost() {
        Post existingPost = postRepository.findAll().get(0); // get the first post
        Long postId = existingPost.getId();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        String postJson = "{\"title\":\"Updated Post\",\"content\":\"This is the content of the updated post in test.\",\"slug\":\"updated-post\"}";
        HttpEntity<String> entity = new HttpEntity<>(postJson, headers);

        ResponseEntity<Post> responseEntity = restTemplate.exchange("/api/posts/" + postId, HttpMethod.PUT, entity, Post.class);
        Post updatedPost = responseEntity.getBody();

        System.out.println("updated post: " + updatedPost);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(updatedPost).isNotNull();
        assertThat(updatedPost.getTitle()).isEqualTo("Updated Post");
        assertThat(updatedPost.getContent()).isEqualTo("This is the content of the updated post in test.");
        assertThat(updatedPost.getSlug()).isEqualTo("updated-post");
    }

    @Test
    public void deletePost() {
        Post existingPost = postRepository.findAll().get(0);
        Long postId = existingPost.getId();

        ResponseEntity<Void> responseEntity = restTemplate.exchange("/api/posts/" + postId, HttpMethod.DELETE, null, Void.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(postRepository.findById(postId)).isEmpty();
    }

}