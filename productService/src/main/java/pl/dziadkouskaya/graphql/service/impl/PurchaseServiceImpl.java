package pl.dziadkouskaya.graphql.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.dziadkouskaya.graphql.entity.Purchase;
import pl.dziadkouskaya.graphql.entity.filters.PurchaseFilter;
import pl.dziadkouskaya.graphql.repository.sql.PurchaseRepository;
import pl.dziadkouskaya.graphql.service.PurchaseService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class PurchaseServiceImpl implements PurchaseService {
    private final PurchaseRepository purchaseRepository;

    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }


    @Override
    public List<Purchase> getAllPurchases() {
        log.info("Get all purchases.");
        var purchases =  purchaseRepository.findAll();
        log.info("Returned {} purchases. ", purchases.size());
        return purchases;
    }

    @Override
    public Optional<Purchase> getPurchaseById(UUID id) {
        log.info("Get purchase by ID: {}.", id);
        return purchaseRepository.findById(id);
    }

    @Override
    public List<Purchase> getPurchasesByFilter(PurchaseFilter purchaseFilter) {
        log.info("Get puechases by params: {}.", purchaseFilter);
        var purchases =  purchaseRepository.findByPurchaseFilter(purchaseFilter);
        log.info("Returned {} purchases. ", purchases.size());
        return purchases;
    }
}
