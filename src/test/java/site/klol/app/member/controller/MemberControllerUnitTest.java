package site.klol.app.member.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import site.klol.app.member.service.MemberService;

@WebMvcTest(MemberController.class)
class MemberControllerUnitTest {

    @Autowired
    WebTestClient webTestClient;
    @MockBean
    MemberService memberService;
    @Test
    void test() {
        webTestClient.
    }
}