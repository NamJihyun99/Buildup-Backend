package buildup.server.activity.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ActivityResponse {

    private Long activityId;

    private String categoryName;

    private String activityName;

    private String hostName;

    // TODO: 프론트랑 얘기해보기
    private String activityimg;

    private String roleName;

    private LocalDate startDate;

    private LocalDate endDate;
    private String urlName;

//    private Integer percentage;

}
