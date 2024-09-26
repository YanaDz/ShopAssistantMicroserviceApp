package pl.dziadkouskaya.productMicroservice.service;

import pl.dziadkouskaya.productMicroservice.entity.Purchase;
import pl.dziadkouskaya.productMicroservice.entity.filters.PurchaseFilter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PurchaseService {
    List<Purchase> getAllPurchases();

    Optional<Purchase> getPurchaseById(UUID id);

    List<Purchase> getPurchasesByFilter(PurchaseFilter purchaseFilter);



}
