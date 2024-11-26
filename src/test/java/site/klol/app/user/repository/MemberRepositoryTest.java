package site.klol.app.user.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import site.klol.app.common.utils.SHA256;
import site.klol.app.member.entity.Member;
import site.klol.app.member.repository.MemberRepository;

import java.util.Optional;
import java.util.stream.Stream;

@DataJpaTest
@ActiveProfiles("test")
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    static final String DUP_ID = "tmddn645";
    static final String DUP_NICKNAME = "nickname11";
    static final String NOT_DUP_ID = "notDupId";
    static final String NOT_DUP_NICKNAME = "notDupNickname";


    @BeforeEach
    void setUp(){
        Member saveMember = Member.builder()
                .id(DUP_ID)
                .password(SHA256.encrypt("testPassword"))
                .nickname(DUP_NICKNAME)
                .build();
        memberRepository.save(saveMember);
    }

    static Stream<Arguments> provideIdAndNicknameAndExpect() {
        return Stream.of(
                Arguments.of(DUP_ID, NOT_DUP_NICKNAME,true),
                Arguments.of(NOT_DUP_ID, DUP_NICKNAME,true),
                Arguments.of(NOT_DUP_ID, NOT_DUP_NICKNAME, false),
                Arguments.of(DUP_ID, DUP_NICKNAME,true)

        );
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