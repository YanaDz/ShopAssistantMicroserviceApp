package pl.dziadkouskaya.search_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dziadkouskaya.search_server.entity.Seller;

import java.util.UUID;

@Repository
public interface SellerRepository extends JpaRepository<Seller, UUID>, CustomSellerRepository {
}
