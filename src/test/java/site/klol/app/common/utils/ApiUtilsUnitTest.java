package site.klol.app.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site.klol.app.common.utils.ApiUtils.ApiResult;

class ApiUtilsUnitTest {

    @Test
    @DisplayName("ApiResult 클래스를 json으로 변경하면 code, message, response 공통 포맷이 들어있는지 확인.")
    void success() throws JsonProcessingException {
        // given
        String regex = ".*\"code\".*\"message\".*\"response\".*";
        ApiResult<Void> success = ApiUtils.success();
        ObjectMapper objectMapper = new ObjectMapper();
        // when
        String jsonSuccess = objectMapper.writeValueAsString(success);
        // then
        Assertions.assertThat(jsonSuccess.matches(regex)).isTrue();
    }
}