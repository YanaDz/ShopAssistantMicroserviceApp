package pl.dziadkouskaya.productMicroservice.repository.sql;

import pl.dziadkouskaya.productMicroservice.entity.Firm;
import pl.dziadkouskaya.productMicroservice.entity.Seller;

import java.util.List;

public interface CustomSellerRepository {
    List<Seller> findSellerByName(String name);
}
