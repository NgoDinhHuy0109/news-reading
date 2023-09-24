package applications.category;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category implements Serializable {
    private String _id;
    private String categoryName;
    private String description;
    private Long createdAt;
    private Long updatedAt;
    @Builder.Default
    private Boolean isDeleted = false;
}
