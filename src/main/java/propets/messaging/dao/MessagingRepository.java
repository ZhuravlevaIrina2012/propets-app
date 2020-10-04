package propets.messaging.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import propets.messaging.model.Post;

public interface MessagingRepository extends MongoRepository<Post, String> {

}
