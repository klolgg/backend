package site.klol.app.common.utils;

import lombok.*;
import lombok.experimental.UtilityClass;
import site.klol.app.common.constants.ApiResCode;
import site.klol.app.common.constants.ApiResMsg;

@UtilityClass
public class ApiUtils {

    public static ApiResult<Void> success() {
        return new ApiResult<>(ApiResCode.SUCCESS, ApiResMsg.SUCCESS, null);
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
