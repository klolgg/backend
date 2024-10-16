package site.klol.app.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.klol.app.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
