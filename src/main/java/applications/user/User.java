package applications.user;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable{
    private String _id;
    private String userName;
    private String email;
    private String description;
    private Long createdAt;
    private Long updatedAt;
    @Builder.Default
    private Boolean isDeleted = false;
}
