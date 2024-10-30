package site.klol.app.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import site.klol.app.common.config.ErrorCode;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    final ErrorCode errorCode;
}