package buildup.server.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorEntity {
    private String status;
    private String errorMessage;
    private Map<String, String> errors;

    public ErrorEntity(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorEntity(String status, String errorMessage) {
        this.status = status;
        this.errorMessage = errorMessage;
    }
}
