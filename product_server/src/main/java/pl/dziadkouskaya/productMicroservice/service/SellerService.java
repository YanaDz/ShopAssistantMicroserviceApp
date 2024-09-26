package pl.dziadkouskaya.productMicroservice.service;

import pl.dziadkouskaya.productMicroservice.entity.Seller;
import pl.dziadkouskaya.productMicroservice.entity.dto.SellerDto;
import pl.dziadkouskaya.productMicroservice.entity.dto.SellerParam;

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
