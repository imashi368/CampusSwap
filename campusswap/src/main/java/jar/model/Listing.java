package jar.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "listings")
public class Listing {
    @Id
    private String id;
    private String title;
    private String description;
    private String studentName;
    private String contactInfo;
    private boolean active = true;
    private double price;
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}