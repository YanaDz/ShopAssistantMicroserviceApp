package pl.dziadkouskaya.productMicroservice.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.dziadkouskaya.productMicroservice.entity.Purchase;
import pl.dziadkouskaya.productMicroservice.entity.filters.PurchaseFilter;
import pl.dziadkouskaya.productMicroservice.repository.sql.PurchaseRepository;
import pl.dziadkouskaya.productMicroservice.service.PurchaseService;

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
