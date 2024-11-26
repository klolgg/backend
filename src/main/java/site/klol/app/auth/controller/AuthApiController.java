package site.klol.app.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.klol.app.auth.service.AuthService;
import site.klol.app.member.dto.LoginReqDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthApiController {

    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<String> getMemberProfile(
            @Valid @RequestBody LoginReqDto request
    ) {
        String token = this.authService.login(request);
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}

