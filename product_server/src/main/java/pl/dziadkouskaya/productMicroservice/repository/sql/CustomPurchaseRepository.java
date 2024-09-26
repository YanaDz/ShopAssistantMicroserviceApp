package pl.dziadkouskaya.productMicroservice.repository.sql;

import pl.dziadkouskaya.productMicroservice.entity.Purchase;
import pl.dziadkouskaya.productMicroservice.entity.filters.PurchaseFilter;

import java.util.List;

public interface CustomPurchaseRepository {
    List<Purchase> findByPurchaseFilter(PurchaseFilter purchaseFilter);

}
