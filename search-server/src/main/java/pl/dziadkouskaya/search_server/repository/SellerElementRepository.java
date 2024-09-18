package pl.dziadkouskaya.search_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dziadkouskaya.search_server.entity.SellerElement;

import java.util.UUID;

public interface SellerElementRepository extends JpaRepository<SellerElement, UUID> {
}
