package dev.szi.danvega.blog.repository;

import dev.szi.danvega.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    // No additional methods are required since JpaRepository provides basic CRUD operations
    // save(), findAll(), findById(), deleteById() are already implemented
}