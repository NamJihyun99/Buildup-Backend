package buildup.server.member.domain;

import buildup.server.entity.Interest;
import buildup.server.entity.InterestConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.EnumSet;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Profile {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long id;

    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String nickname;
    private String email;
    private String school;
    private String major;
    private String grade;

    @Setter
    private String imgUrl;

    private String schoolPublicYn;

    @Convert(converter = InterestConverter.class)
    private EnumSet<Interest> interests;

    @Builder
    public Profile(Member member, String nickname, String email, String school, String major, String grade, String schoolPublicYn, EnumSet<Interest> interests) {
        this.member = member;
        this.nickname = nickname;
        this.email = email;
        this.school = school;
        this.major = major;
        this.grade = grade;
        this.schoolPublicYn = schoolPublicYn;
        this.interests = interests == null ? EnumSet.noneOf(Interest.class) : EnumSet.copyOf(interests);
    }

    public void update(String nickname, String school, String major, String grade, String schoolPublicYn, EnumSet<Interest> interests) {
        this.nickname = nickname;
        this.school = school;
        this.major = major;
        this.grade = grade;
        this.schoolPublicYn = schoolPublicYn;
        this.interests = interests == null ? EnumSet.noneOf(Interest.class) : EnumSet.copyOf(interests);
    }
}
