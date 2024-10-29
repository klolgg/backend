package site.klol.app.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.klol.app.common.entity.BaseEntity;
import site.klol.app.common.utils.SHA256;
import site.klol.app.member.dto.SignUpReqDTO;

@Table(name="member",schema = "klol")
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq_no")
    private Long seqNo;

    @Column(name = "id", nullable = false, unique = true)
    private String id;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    public static Member of(SignUpReqDTO signUpReqDTO) {
        return Member.builder()
                .id(signUpReqDTO.getId())
                .password(SHA256.encrypt(signUpReqDTO.getPassword()))
                .nickname(signUpReqDTO.getNickname())
                .build();
    }
}