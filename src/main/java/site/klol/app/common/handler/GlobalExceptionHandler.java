package site.klol.app.common.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import site.klol.app.common.constants.ApiResCode;
import site.klol.app.common.constants.ApiResMsg;
import site.klol.app.common.exception.BusinessException;
import site.klol.app.common.utils.ApiUtils;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 5xxx unknown error
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiUtils.ApiResult<Void>> handleAllException(Exception e){
        log.error(e.getMessage(), e);
        return ResponseEntity.ok(ApiUtils.fail());
    }

    // 9xxx business error
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ApiUtils.ApiResult<Void>> handleBusinessException(BusinessException e){
        log.error(e.getMessage(), e);
        return ResponseEntity.ok(ApiUtils.businessException(e.getApiResCode(), e.getApiResMsg()));
    }

    //4xxx general error
    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<ApiUtils.ApiResult<Void>> handleNullPointerException(NullPointerException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.ok(ApiUtils.generalFail(ApiResCode.NULL_POINTER_EXCEPTION,ApiResMsg.NULL_POINTER_EXCEPTION));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiUtils.ApiResult<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.ok(ApiUtils.generalFail(ApiResCode.METHOD_ARGUMENT_NOT_VALID_EXCEPTION, ApiResMsg.METHOD_ARGUMENT_NOT_VALID_EXCEPTION));
    }


}
