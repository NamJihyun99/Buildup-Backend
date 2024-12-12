package buildup.server.record.repository;

import buildup.server.record.domain.Record;
import buildup.server.record.domain.RecordImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordImageRepository extends JpaRepository<RecordImage, Long> {

    List<RecordImage> findAllByRecord(Record record);

    List<RecordImage> findByRecordId(Long recordId);
}
