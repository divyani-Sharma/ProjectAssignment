package com.post.postApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.post.postApp.data.ApiResponse;
import com.post.postApp.data.PostRequest;
import com.post.postApp.entity.Post;
import com.post.postApp.service.PostService;

@RestController
@RequestMapping("/api")
public class Controller {
	
	@Autowired
    private PostService postService;

    @PostMapping("/createNewPost")
    public ResponseEntity<?> createNewPost(@RequestBody PostRequest postRequest) {
        try {
            Post post = postService.createNewPost(postRequest.getPostName(), postRequest.getPostContents());
            Long postId = post.getId();

            // HTTP call
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> httpResponse = restTemplate.getForEntity("http://worldtimeapi.org/api/timezone/Asia/Kolkata", String.class);

            return ResponseEntity.ok(new ApiResponse(postId, httpResponse.getBody()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


}
