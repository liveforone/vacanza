package vacanza.vacanza.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import vacanza.vacanza.domain.Role;
import vacanza.vacanza.domain.Users;

@Data
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String email;
    private String password;
    private Role auth;

    //==dto -> entity==//
    public Users toEntity() {
        return Users.builder()
                .id(id)
                .email(email)
                .password(password)
                .auth(auth)
                .build();
    }
}
