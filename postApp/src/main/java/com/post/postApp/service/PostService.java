package com.post.postApp.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.post.postApp.dto.PostRepo;
import com.post.postApp.entity.Post;

import jakarta.transaction.Transactional;

@org.springframework.stereotype.Service
public class PostService {
	
	@Autowired
    private PostRepo postRepository;

    @Transactional
    public Post createNewPost(String name, String contents) {
        Post post = new Post();
        post.setPostName(name);
        post.setPostContent(contents);
        return postRepository.save(post);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }
}
