package buildup.server.member.repository;

import buildup.server.member.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query(value = "select * from profile p where p.interests like '%:keyword%'", nativeQuery = true)
    List<Profile> searchByInterestsContains(@RequestParam("keyword") String keyword);
}
