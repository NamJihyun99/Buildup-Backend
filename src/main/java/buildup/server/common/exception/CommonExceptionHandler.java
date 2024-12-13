package buildup.server.common.exception;

import buildup.server.activity.exception.ActivityException;
import buildup.server.category.exception.CategoryException;
import buildup.server.common.response.ErrorEntity;
import buildup.server.member.exception.MemberException;
import buildup.server.record.exception.RecordException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorEntity dtoValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        log.error("Dto Validation Exception({}): {}", DtoValidationErrorCode.BAD_INPUT, errors);
        return new ErrorEntity(DtoValidationErrorCode.BAD_INPUT.toString(),
                DtoValidationErrorCode.BAD_INPUT.getDefaultMessage(),
                errors);
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorEntity handleAuthException(AuthenticationException e) {
        log.error("Authentication Exception={}", e.getMessage());
        return new ErrorEntity(e.getMessage());
    }

    @ExceptionHandler(S3Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorEntity s3Exception(S3Exception ex) {
        log.error("S3 Image Exception[{}]: {}", ex.getErrorCode().toString(), ex.getErrorMessage());
        return new ErrorEntity(ex.getErrorCode().toString(), ex.getErrorMessage());
    }

    @ExceptionHandler(MemberException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorEntity handleMemberException(MemberException e) {
        log.error("Member Exception({})={}", e.getErrorCode(), e.getErrorMessage());
        return new ErrorEntity(e.getErrorCode().toString(), e.getErrorMessage());
    }

    @ExceptionHandler(ActivityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorEntity handleActivityException(ActivityException ex) {
        log.error("Activity Exception[{}]: {}", ex.getErrorCode().toString(), ex.getErrorMessage());
        return new ErrorEntity(ex.getErrorCode().toString(), ex.getErrorMessage());
    }

    @ExceptionHandler(CategoryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorEntity handleCategoryException(CategoryException ex) {
        log.error("Category Exception[{}]: {}", ex.getErrorCode().toString(), ex.getErrorMessage());
        return new ErrorEntity(ex.getErrorCode().toString(), ex.getErrorMessage());
    }

    @ExceptionHandler(RecordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorEntity handleRecordException(RecordException e) {
        log.error("Record Exception({})={}", e.getErrorCode(), e.getErrorMessage());
        return new ErrorEntity(e.getErrorCode().toString(), e.getErrorMessage());
    }
}
