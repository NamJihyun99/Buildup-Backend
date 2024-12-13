package buildup.server.common;

import buildup.server.common.response.ErrorEntity;
import buildup.server.common.response.StringResponse;
import buildup.server.member.service.MemberService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HomeController {

    private final Environment env;
    private final MemberService memberService;

    @GetMapping("/health")
    public StringResponse healthCheck() {return new StringResponse("HealthCheck!!");}

    @GetMapping("/test")
    public StringResponse test() {
        return new StringResponse(memberService.test());
    }

    @GetMapping("/home/entrypoint")
    public ErrorEntity authEntryPoint(HttpServletResponse response) {
        return new ErrorEntity(response.getHeader("error"));
    }
}
