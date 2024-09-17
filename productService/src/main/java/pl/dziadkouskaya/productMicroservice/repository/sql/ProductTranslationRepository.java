package pl.dziadkouskaya.graphql.repository.sql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dziadkouskaya.graphql.entity.Product;
import pl.dziadkouskaya.graphql.entity.ProductTranslation;

import java.util.UUID;

@Repository
public interface ProductTranslationRepository extends JpaRepository<ProductTranslation, UUID>, CustomProductTranslationRepository
{
}
