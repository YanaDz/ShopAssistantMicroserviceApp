package pl.dziadkouskaya.graphql.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.dziadkouskaya.graphql.entity.Seller;
import pl.dziadkouskaya.graphql.repository.sql.SellerRepository;
import pl.dziadkouskaya.graphql.service.SellerService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;

    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public List<Seller> getAll() {
        return sellerRepository.findAll();
    }

    @Override
    public Optional<Seller> getById(UUID uuid) {
        return sellerRepository.findById(uuid);
    }
}
