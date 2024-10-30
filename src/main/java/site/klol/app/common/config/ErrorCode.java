package site.klol.app.common.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "9010", "존재하지 않는 사용자 입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
