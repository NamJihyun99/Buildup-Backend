package buildup.server.auth.exception;

import buildup.server.common.response.BasicResponse;
import buildup.server.common.response.ErrorEntity;
import buildup.server.common.response.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        sendError(response, exception);
    }


    private void sendError(HttpServletResponse response, AuthenticationException e) {
        ObjectMapper objectMapper = new ObjectMapper();
        BasicResponse<Object> basicResponse = ResponseUtil.error(
                new ErrorEntity(e.getMessage(), e.getMessage())
        );
        response.setCharacterEncoding("utf-8");
        response.setStatus(401);
        response.setContentType("application/json");
        try{
            response.getWriter().write(objectMapper.writeValueAsString(basicResponse));
        }catch (IOException ex){
            log.error(ex.getMessage());
        }
    }
}
