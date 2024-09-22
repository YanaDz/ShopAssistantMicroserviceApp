package pl.dziadkouskaya.graphql.repository.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;
import pl.dziadkouskaya.graphql.entity.Product;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>, CustomProductRepository {


}
