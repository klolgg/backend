package site.klol.app.member.controller;

import jakarta.persistence.EntityExistsException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.klol.app.common.utils.ApiUtils;
import site.klol.app.common.utils.ApiUtils.ApiResult;
import site.klol.app.member.dto.SignUpReqDTO;
import site.klol.app.member.service.MemberService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResult<Void>> registerUser(@Valid @RequestBody SignUpReqDTO signUpReqDTO) {
        memberService.createUser(signUpReqDTO);
        return ResponseEntity.ok(ApiUtils.success());
    }
    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ApiResult<Void>> handleEntityExistsException(EntityExistsException e) {
        log.error(e.getMessage(), e);

        return ResponseEntity.ok(ApiUtils.fail());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResult<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiUtils.fail());
    }
}
