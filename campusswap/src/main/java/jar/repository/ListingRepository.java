package jar.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import jar.model.Listing;

@Repository
public interface ListingRepository extends MongoRepository<Listing, String> {
    List<Listing> findByTitleContainingIgnoreCase(String title);
}