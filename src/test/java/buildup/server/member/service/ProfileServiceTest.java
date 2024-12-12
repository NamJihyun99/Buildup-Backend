package buildup.server.member.service;

import buildup.server.common.DummyObject;
import buildup.server.member.domain.Member;
import buildup.server.member.domain.Profile;
import buildup.server.member.dto.LocalJoinRequest;
import buildup.server.member.dto.ProfileRequest;
import buildup.server.member.repository.ProfileRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProfileServiceTest extends DummyObject {

    @InjectMocks
    private ProfileService profileService;
    @Mock
    private ProfileRepository profileRepository;
//    @Mock
//    private InterestRepository interestRepository;
    @Spy
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void 프로필생성_test() throws Exception {
        // given
        ProfileRequest profileRequest = new ProfileRequest("nickname", "username@naver.com",
                "Sookmyung Women's University",
                "Computer Science", "4", "N",
                List.of("연구/R&D", "디자인")
        );
        LocalJoinRequest loginRequest = new LocalJoinRequest(
                "username",
                "password4321",
                profileRequest,
                "Y"
        );

        Member member = newMember(loginRequest);
        Profile profile = profileRequest.toProfile(member);

        // when
        Mockito.when(profileRepository.save(ArgumentMatchers.any())).thenReturn(profile);
        Long profileId = profileService.saveProfile(profileRequest, member);
        // then
        Assertions.assertThat(profileId).isEqualTo(member.getId());
        Assertions.assertThat(profile.getEmail()).isEqualTo("username@naver.com");

    }

}