package site.klol.app.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResMsg {
    // 2xxx success
    public static final String SUCCESS = "success";

    // 4xxx general error
    public static final String NULL_POINTER_EXCEPTION = "null pointer exception";
    public static final String METHOD_ARGUMENT_NOT_VALID_EXCEPTION = "method argument not valid exception";

    // 5xxx unknown error
    public static final String FAIL = "fail";

    // 9xxx business error
    public static final String NO_UNIV_AUTHORIZATION = "9000";
}
