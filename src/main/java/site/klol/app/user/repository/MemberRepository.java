package site.klol.app.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import site.klol.app.user.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m from Member m where m.id = :id or m.nickname = :nickname")
    Optional<Member> findByIdOrNickname(@Param("id") String id, @Param("nickname") String nickname);
}
