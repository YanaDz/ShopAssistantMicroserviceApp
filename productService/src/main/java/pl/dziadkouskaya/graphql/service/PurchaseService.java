package pl.dziadkouskaya.graphql.service;

import pl.dziadkouskaya.graphql.entity.Purchase;
import pl.dziadkouskaya.graphql.entity.filters.PurchaseFilter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PurchaseService {
    List<Purchase> getAllPurchases();

    Optional<Purchase> getPurchaseById(UUID id);

    List<Purchase> getPurchasesByFilter(PurchaseFilter purchaseFilter);



}
