package site.klol.app.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class MemberDto {
    @NotBlank
    @Length(min=6, max=16)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "아이디는 알파벳과 숫자만 포함할 수 있습니다.")
    private String id;
    @NotBlank
    @Length(min=10, max=16)
    private String password;
    @NotBlank
    @Length(min=3, max=16)
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "닉네임은 알파벳과 숫자만 포함할 수 있습니다.")
    private String nickname;
}
