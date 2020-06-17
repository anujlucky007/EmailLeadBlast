package com.lead;

import com.lead.model.MessagePost;
import com.lead.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/posts")
public class MessagePostController {

    private final PostService postService;

    @Autowired
    public MessagePostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping(path = "/{id}")
    public Mono<MessagePost> get(@PathVariable String id) {
        return postService.findOne(id);
    }

    @PostMapping
    public Mono<ResponseEntity<MessagePost>> save(@RequestBody MessagePost messagePost) {
        return postService.save(messagePost)
                .map(savedHotel -> new ResponseEntity<>(savedHotel, HttpStatus.CREATED));
    }

    @PutMapping
    public Mono<ResponseEntity<MessagePost>> update(@RequestBody MessagePost messagePost) {
        return postService.update(messagePost)
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
    public Flux<MessagePost> findAll() {
        return postService.findAll();
    }

}
