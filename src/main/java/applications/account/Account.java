package applications.account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account implements Serializable{
    private String _id;
    private String user_id;
    private String account;
    private String password;
    private Long createdAt;
    private Long updatedAt;
    @Builder.Default
    private Boolean isDeleted = false;
}
