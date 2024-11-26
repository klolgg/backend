package site.klol.app.auth.dto;

import lombok.*;
import site.klol.app.member.dto.MemberDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomUserInfoDto extends MemberDto {
    private Long seqNo;

    private String id;

    private String password;

    private String nickname;

    private String role;

}