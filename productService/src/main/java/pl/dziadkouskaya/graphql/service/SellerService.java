package pl.dziadkouskaya.graphql.service;

import pl.dziadkouskaya.graphql.entity.Seller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SellerService {
    List<Seller> getAll();

    Optional<Seller> getById(UUID uuid);
}
