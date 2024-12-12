package site.klol.app.auth.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import site.klol.app.member.entity.Member;

@Getter
@Setter
public class CustomUser extends User {
    private Member member;

    public CustomUser(Member member) {
        super(member.getNickname(), member.getPassword(), AuthorityUtils.NO_AUTHORITIES);
//        super(member.getNickname(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole().toString()));
        this.member = member;
    }
}
