package jar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jar.model.ApiKey;
import jar.model.Listing;
import jar.repository.ApiKeyRepository;
import jar.repository.ListingRepository;

@Service
public class ListingService {

    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    private ApiKeyRepository apiKeyRepository;

    
    public boolean isValidApiKey(String originalKey) {
        Optional<ApiKey> apiKey = apiKeyRepository.findByKeyValue(originalKey);
        return apiKey.isPresent() && apiKey.get().isActive();
    }

    
    public Listing createListing(Listing listing) {
        return listingRepository.save(listing);
    }

    
    public List<Listing> getAllListings() {
        return listingRepository.findAll();
    }

   
    public Listing updateListing(String id, Listing updatedListing) {
        return listingRepository.findById(id).map(listing -> {
            listing.setTitle(updatedListing.getTitle());
            listing.setDescription(updatedListing.getDescription());
            listing.setStudentName(updatedListing.getStudentName());
            listing.setContactInfo(updatedListing.getContactInfo());
            listing.setActive(updatedListing.isActive());
            return listingRepository.save(listing);
        }).orElseThrow(() -> new RuntimeException("Listing not found with id " + id));
    }

    public void deleteListing(String id) {
        listingRepository.deleteById(id);
    }
    public List<Listing> searchListings(String query) {
    return listingRepository.findByTitleContainingIgnoreCase(query);
}
}