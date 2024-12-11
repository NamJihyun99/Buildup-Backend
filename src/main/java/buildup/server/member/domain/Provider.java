package buildup.server.member.domain;

import buildup.server.auth.exception.AuthErrorCode;
import buildup.server.auth.exception.AuthException;
import buildup.server.member.exception.MemberErrorCode;
import buildup.server.member.exception.MemberException;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Provider {
    LOCAL("LOCAL"),
    GOOGLE("GOOGLE"),
    KAKAO("KAKAO"),
    NAVER("NAVER");

    @JsonValue
    private final String title;

    public static Provider toProvider(String str) {
        return Arrays.stream(Provider.values())
                .filter(provider -> provider.title.equals(str))
                .findAny()
                .orElseThrow(() -> new MemberException(MemberErrorCode.AUTH_PROVIDER_MISS_MATCH));
    }

}
