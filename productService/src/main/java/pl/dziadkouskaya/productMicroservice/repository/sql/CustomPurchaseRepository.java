package pl.dziadkouskaya.graphql.repository.sql;

import pl.dziadkouskaya.graphql.entity.Purchase;
import pl.dziadkouskaya.graphql.entity.filters.PurchaseFilter;

import java.util.List;

public interface CustomPurchaseRepository {
    List<Purchase> findByPurchaseFilter(PurchaseFilter purchaseFilter);

}
