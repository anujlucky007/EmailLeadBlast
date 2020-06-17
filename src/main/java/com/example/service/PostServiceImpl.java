package com.example.service;

import com.example.model.Post;
import com.example.repository.PostRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Mono<Post> save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Mono<Post> update(Post post) {
        return postRepository.findById(post.getId())
                .flatMap(
                        postDB -> postRepository.save(postDB.update(post))
                );
    }

    @Override
    public Mono<Post> findOne(String id) {
        return postRepository.findById(id);
    }

    @Override
    public Mono<Boolean> delete(String id) {
        return postRepository.findById(id)
                .flatMap(
                        post -> postRepository.delete(post).then(Mono.just(Boolean.TRUE))
                )
                .defaultIfEmpty(Boolean.FALSE);
    }

    @Override
    public Flux<Post> findAll() {
        return postRepository.findAll();
    }
}
