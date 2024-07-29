package pl.dziadkouskaya.graphql.repository.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dziadkouskaya.graphql.entity.Purchase;

import java.util.UUID;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, UUID>, CustomPurchaseRepository {

}
