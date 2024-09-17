package pl.dziadkouskaya.graphql.repository.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dziadkouskaya.graphql.entity.Firm;

import java.util.UUID;

public interface FirmRepository extends JpaRepository<Firm, UUID>, CustomFirmRepository {

}
