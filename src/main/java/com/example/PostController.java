package com.example;

import com.example.model.Post;
import com.example.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(path = "/{id}")
    public Mono<Post> get(@PathVariable String id) {
        return postService.findOne(id);
    }

    @PostMapping
    public Mono<ResponseEntity<Post>> save(@RequestBody Post post) {
        return postService.save(post)
                .map(savedHotel -> new ResponseEntity<>(savedHotel, HttpStatus.CREATED));
    }

    @PutMapping
    public Mono<ResponseEntity<Post>> update(@RequestBody Post post) {
        return postService.update(post)
                .map(savedPost -> new ResponseEntity<>(savedPost, HttpStatus.CREATED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/{id}")
    public Mono<ResponseEntity<String>> delete(@PathVariable String id) {
        return postService.delete(id)
                .filter(delete -> delete)
                .map(delete -> new ResponseEntity<>("Deleted", HttpStatus.ACCEPTED))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/")
    public Flux<Post> findAll() {
        return postService.findAll();
    }

}
