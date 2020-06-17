package com.lead.repository;

import com.lead.model.MessagePost;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends ReactiveMongoRepository<MessagePost, String> {

}