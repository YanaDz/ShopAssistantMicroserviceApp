package pl.dziadkouskaya.productMicroservice.repository.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dziadkouskaya.productMicroservice.entity.Firm;

import java.util.UUID;

public interface FirmRepository extends JpaRepository<Firm, UUID>, CustomFirmRepository {

}
