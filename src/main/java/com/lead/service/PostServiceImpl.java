package com.lead.service;

import com.lead.model.MessagePost;
import com.lead.repository.PostRepository;
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
    public Mono<MessagePost> save(MessagePost messagePost) {
        return postRepository.save(messagePost);
    }

    @Override
    public Mono<MessagePost> update(MessagePost messagePost) {
        return postRepository.findById(messagePost.getId())
                .flatMap(
                        postDB -> postRepository.save(postDB.update(messagePost))
                );
    }

    @Override
    public Mono<MessagePost> findOne(String id) {
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
    public Flux<MessagePost> findAll() {
        return postRepository.findAll();
    }
}
