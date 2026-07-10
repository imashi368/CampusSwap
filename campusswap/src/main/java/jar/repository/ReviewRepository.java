package jar.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import jar.model.Review;

public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findByListingId(String listingId);
}