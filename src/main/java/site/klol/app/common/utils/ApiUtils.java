package site.klol.app.common.utils;

import lombok.*;
import lombok.experimental.UtilityClass;
import site.klol.app.common.constants.ApiResCode;
import site.klol.app.common.constants.ApiResMsg;

@UtilityClass
public class ApiUtils {
    // 2xxx success
    public static ApiResult<Void> success() {
        return new ApiResult<>(ApiResCode.SUCCESS, ApiResMsg.SUCCESS, null);
    }

    // 4xxx general error
    public static ApiResult<Void> generalFail(String code, String msg) {
        return new ApiResult<>(code, msg, null);
        //throw new NullPointerException(ApiResCode.NULL_POINTER_EXCEPTION, ApiResMsg.NULL_POINTER_EXCEPTION);
    }

    // 5xxx unknown error
    public static ApiResult<Void> fail() {
        return new ApiResult<>(ApiResCode.FAIL, ApiResMsg.FAIL, null);
    }

    // 9xxx business error
    public static ApiResult<Void> businessException(String code, String msg) {
        return new ApiResult<>(code, msg, null);
        //throw new BusinessException(ApiResCode.NO_UNIV_AUTHORIZATION, ApiResMsg.NO_UNIV_AUTHORIZATION);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @ToString
    public static class ApiResult<T> {
        private String code;
        private String message;
        private T response;
    }
}
