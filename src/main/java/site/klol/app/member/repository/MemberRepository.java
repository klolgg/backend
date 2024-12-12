package site.klol.app.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import site.klol.app.member.entity.Member;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m from Member m where m.id = :id or m.nickname = :nickname")
    Optional<Member> findByIdOrNickname(@Param("id") String id, @Param("nickname") String nickname);

    @Query("select m from Member m where m.id = :id")
    Optional<Member> findById(@Param("id") String id);

    @Query("select m from Member m where m.id = :id")
    Optional<Member> findByUsername(String id);
}
