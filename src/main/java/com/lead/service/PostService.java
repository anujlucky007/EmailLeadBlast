package com.lead.service;

import com.lead.model.MessagePost;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostService {

    Mono<MessagePost> save(MessagePost messagePost);

    Mono<MessagePost> update(MessagePost messagePost);

    Mono<MessagePost> findOne(String id);

    Mono<Boolean> delete(String id);

    Flux<MessagePost> findAll();

}
