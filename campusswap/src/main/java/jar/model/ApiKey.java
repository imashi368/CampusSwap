package jar.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "api_keys")
public class ApiKey {
    @Id
    private String id;
    private String keyValue;
    private String clientName;
    private boolean active;
}
