package site.klol.app.member.controller;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import site.klol.app.member.entity.Member;
import site.klol.app.member.service.MemberService;

@WebMvcTest(MemberController.class)
class MemberControllerUnitTest {

    @Autowired
    WebTestClient webTestClient;
    @MockBean
    MemberService memberService;
    String BASE_URL = "/api/v1/member";
    static String wrongId = "!@sdfa!@";
    static String wrongId2 = "short";
    static String wrongPassword = "12";
    static String wrongNickname = "1";
    static String wrongNickname2 = "1123asdf!@";
    static String goodId = "tmddn645";
    static String goodPassword = "qwerasdf12";
    static String goodNickname = "sengwoo12";
    static Stream<Arguments> provideIdAndPasswordAndNicknameExpect() {
        return Stream.of(
            Arguments.of(goodId,goodPassword,goodNickname,true),
            Arguments.of(),
            Arguments.of(),
            Arguments.of(),

        );
    }
    @ParameterizedTest
    @MethodSource("provideIdAndPasswordAndNicknameExpect")
    @DisplayName("회원가입 정보 유효성 검증 테스트")
    void registerUser(String id, String password, String nickname, Boolean expect) {
        // given

        // when
        Member member = Member.builder()
            .id(id)
            .password(password)
            .nickname(nickname)
            .build();

        webTestClient.post()
            .uri(BASE_URL+ "/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(member)
            .exchange()
            .expectStatus().isEqualTo(HttpStatusCode.valueOf())
            .expectHeader().contentType(MediaType.APPLICATION_JSON) // 응답의 Content-Type 검사
            .expectBody() // 응답 본문을 Member 클래스 타입으로 변환
            .consumeWith(response -> {
                Member responseBody = response.getResponseBody();
                assertThat(responseBody).isNotNull();
                assertThat(responseBody.getId()).isEqualTo("user123");
                assertThat(responseBody.getNickname()).isEqualTo("testUser");
            });


        // then
    }
}