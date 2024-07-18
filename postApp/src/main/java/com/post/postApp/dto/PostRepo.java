package com.post.postApp.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.post.postApp.entity.Post;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

}
