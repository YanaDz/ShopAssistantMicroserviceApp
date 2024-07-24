package pl.dziadkouskaya.graphql.repository.sql;

import pl.dziadkouskaya.graphql.entity.Firm;
import pl.dziadkouskaya.graphql.entity.Seller;

import java.util.List;

public interface CustomSellerRepository {
    List<Seller> findSellerByName(String name);
}
