package site.klol.app.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.klol.app.common.utils.ApiUtils;
import site.klol.app.common.utils.ApiUtils.ApiResult;
import site.klol.app.member.dto.LoginReqDto;
import site.klol.app.member.dto.LogoutReqDto;
import site.klol.app.member.dto.SignUpReqDTO;
import site.klol.app.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<ApiUtils.ApiResult<Void>> registerUser(@Valid @RequestBody SignUpReqDTO signUpReqDTO) {
        memberService.createUser(signUpReqDTO);
        return ResponseEntity.ok(ApiUtils.success());
    }


    @PostMapping("/login")
    public ResponseEntity<ApiUtils.ApiResult<Void>> loginUser(@Valid @RequestBody LoginReqDto loginReqDto){
        memberService.login(loginReqDto);
        return ResponseEntity.ok(ApiUtils.success());
    }


    @PostMapping("/logout")
    public ResponseEntity<ApiResult<Void>> logout(@Valid @RequestBody LogoutReqDto logoutReqDto){
        memberService.logout(logoutReqDto);
        return ResponseEntity.ok(ApiUtils.success());
    }
}
