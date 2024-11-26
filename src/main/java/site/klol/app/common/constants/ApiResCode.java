package site.klol.app.common.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResCode {
    // 2xxx success
    public static final String SUCCESS = "2000";

    // 4xxx general error
    public static final String NULL_POINTER_EXCEPTION = "4000";
    public static final String METHOD_ARGUMENT_NOT_VALID_EXCEPTION = "4001";

    // 5xxx unknown error
    public static final String FAIL = "5000";

    // 9xxx business error
    public static final String NO_UNIV_AUTHORIZATION = "9000";
}
