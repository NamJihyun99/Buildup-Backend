package buildup.server.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public enum DtoValidationErrorCode {

    BAD_INPUT("입력이 올바르지 않습니다.");

    private final String defaultMessage;
}
