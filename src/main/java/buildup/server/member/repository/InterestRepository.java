package buildup.server.member.repository;

import buildup.server.entity.InterestRemove;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestRepository extends JpaRepository<InterestRemove, Long> {

    List<InterestRemove> findAllByFieldContaining(String keyword);
}
