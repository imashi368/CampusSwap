package jar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jar.model.Listing;
import jar.service.ListingService;

@RestController
@RequestMapping("/api/listings")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {})
public class ListingController {

    @Autowired
    private ListingService listingService;
    @Value("${SECRET_API_KEY:MySuperSecretKey123}") 
    private String secretApiKey;

    
    private boolean isAuthorized(String apiKey) {
        return secretApiKey.equals(apiKey);
}
    
    @PostMapping
    public ResponseEntity<?> createListing(
            @RequestHeader(value = "SECRET_API_KEY", required = false) String apiKey,
            @RequestBody Listing listing) {
        
        if (!isAuthorized(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or Missing API Key!");
        }
        Listing savedListing = listingService.createListing(listing);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedListing);
    }

    
    @GetMapping
    public ResponseEntity<?> getAllListings(
            @RequestHeader(value = "SECRET_API_KEY", required = false) String apiKey) {
        
        if (!isAuthorized(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or Missing API Key!");
        }
        List<Listing> listings = listingService.getAllListings();
        return ResponseEntity.ok(listings);
    }

    
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateListing(
        @RequestHeader(value = "SECRET_API_KEY", required = false) String apiKey,
        @PathVariable String id,
        @RequestBody Listing listingDetails) {

//
        if (!isAuthorized(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or Missing API Key!");
        }
        

        Listing updatedListing = listingService.updateListing(id, listingDetails);
        if (updatedListing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Listing not found with id: " + id);
        }

        return ResponseEntity.ok(updatedListing);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteListing(
    @RequestHeader(value = "SECRET_API_KEY", required = false) String apiKey,
    @PathVariable String id) {

        if (!isAuthorized(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or Missing API Key!");
        }

        try {
            
            listingService.deleteListing(id); 
            
            return ResponseEntity.ok("Listing deleted successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/search")
public ResponseEntity<?> searchListings(
        @RequestHeader(value = "SECRET_API_KEY", required = false) String apiKey,
        @RequestParam String query) {

    
    if (!isAuthorized(apiKey)) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or Missing API Key!");
    }

    
    List<Listing> listings = listingService.searchListings(query);
    
    return ResponseEntity.ok(listings);
}
    

} 
