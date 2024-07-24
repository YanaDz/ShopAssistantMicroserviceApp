package pl.dziadkouskaya.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dziadkouskaya.graphql.entity.Seller;

import java.util.UUID;

public interface FirmRepository extends JpaRepository<Seller, UUID> {
}
