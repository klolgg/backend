package site.klol.app.common.exception;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BusinessException extends RuntimeException {
    private String apiResCode;
    private String apiResMsg;
}