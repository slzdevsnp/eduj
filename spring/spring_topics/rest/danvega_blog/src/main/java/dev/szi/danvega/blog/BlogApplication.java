package dev.szi.danvega.blog;

import dev.szi.danvega.blog.model.Post;
import dev.szi.danvega.blog.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLinerunner(PostRepository postRepository) {
        return args -> {
            Post post = new Post();
            post.setTitle("My first post");
            post.setContent("This is contents of my first post");
			post.setSlug("my-first-post");
            postRepository.save(post);
			System.out.println("Inserted a post: " + post);
        };
    }

}
