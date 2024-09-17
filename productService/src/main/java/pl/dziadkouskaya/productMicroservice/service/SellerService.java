package pl.dziadkouskaya.graphql.service;

import pl.dziadkouskaya.graphql.entity.Seller;
import pl.dziadkouskaya.graphql.entity.dto.SellerDto;
import pl.dziadkouskaya.graphql.entity.dto.SellerParam;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SellerService {
    List<SellerDto> getAll();

    SellerDto getById(UUID uuid);

    SellerDto createSeller(SellerParam sellerParam);

    SellerDto updateSeller(UUID id, SellerParam sellerParam);

    List<SellerDto> getSellersByName(String string);
}
