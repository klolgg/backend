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
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import site.klol.app.common.constants.ApiResCode;
import site.klol.app.common.utils.ApiUtils;
import site.klol.app.common.utils.ApiUtils.ApiResult;
import site.klol.app.member.dto.SignUpReqDTO;
import site.klol.app.member.entity.Member;
import site.klol.app.member.service.MemberService;

import static org.assertj.core.api.Assertions.assertThat;

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

    static Stream<Arguments> provideIdAndPasswordAndNicknameExpectStatusAndExpectCode() {
        return Stream.of(
                Arguments.of(goodId,goodPassword,goodNickname, HttpStatus.OK, ApiResCode.SUCCESS),
                Arguments.of(goodId,wrongPassword,wrongNickname, HttpStatus.BAD_REQUEST, ApiResCode.FAIL),
                Arguments.of(wrongId,goodPassword,wrongNickname2, HttpStatus.BAD_REQUEST, ApiResCode.FAIL),
                Arguments.of(wrongId2,wrongPassword,goodNickname, HttpStatus.BAD_REQUEST, ApiResCode.FAIL),
                Arguments.of(wrongId2,wrongPassword,wrongNickname2, HttpStatus.BAD_REQUEST, ApiResCode.FAIL)
        );
    }
    @ParameterizedTest
    @MethodSource("provideIdAndPasswordAndNicknameExpectStatusAndExpectCode")
    @DisplayName("회원가입 정보 유효성 검증 테스트")
    void registerUser(String id, String password, String nickname, HttpStatus expect, String expectCode) {
        // when
        SignUpReqDTO signUpReqDTO = new SignUpReqDTO();
        signUpReqDTO.setId(id);
        signUpReqDTO.setPassword(password);
        signUpReqDTO.setNickname(nickname);

        webTestClient.post()
            .uri(BASE_URL+ "/signup")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .bodyValue(signUpReqDTO)
            .exchange()
            .expectStatus().isEqualTo(expect)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody(ApiResult.class)
            .consumeWith(response -> {
                ApiResult responseBody = response.getResponseBody();
                assertThat(responseBody).isNotNull();
                assertThat(responseBody.getResponse()).isNull();
                assertThat(responseBody.getCode()).isEqualTo(expectCode);
            });
    }
}