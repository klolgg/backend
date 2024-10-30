package site.klol.app.member.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import site.klol.app.common.utils.SHA256;
import site.klol.app.member.entity.Member;

import java.util.Optional;
import java.util.stream.Stream;

@DataJpaTest
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@EnableJpaAuditing
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    static final String DUP_ID = "tmddn645";
    static final String DUP_NICKNAME = "nickname11";
    static final String NOT_DUP_ID = "notDupId";
    static final String NOT_DUP_NICKNAME = "notDupNickname";

    static Stream<Arguments> provideIdAndNicknameAndExpect() {
        return Stream.of(
                Arguments.of(DUP_ID, NOT_DUP_NICKNAME,true),
                Arguments.of(NOT_DUP_ID, DUP_NICKNAME,true),
                Arguments.of(NOT_DUP_ID, NOT_DUP_NICKNAME, false),
                Arguments.of(DUP_ID, DUP_NICKNAME,true)

        );
    }

    @BeforeAll
    void setUp() {
        Member saveMember = Member.builder()
            .id(DUP_ID)
            .password(SHA256.encrypt("testPassword"))
            .nickname(DUP_NICKNAME)
            .build();
        memberRepository.save(saveMember);
    }

    @ParameterizedTest
    @MethodSource("provideIdAndNicknameAndExpect")
    void findByIdOrNickname(String id, String nickname, boolean expect) {
        // given & when
        Optional<Member> target = memberRepository.findByIdOrNickname(id, nickname);
        // then
        Assertions.assertEquals(expect, target.isPresent());
    }
}