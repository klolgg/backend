package site.klol.app.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class LoginReqDto {
    @NotBlank
    @Length(min=6, max=16)
    private String id;
    @NotBlank
    @Length(min=10, max=16)
    private String password;
}