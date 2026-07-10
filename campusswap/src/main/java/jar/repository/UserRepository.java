package jar.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import jar.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    
User findFirstByEmail(String email);
}