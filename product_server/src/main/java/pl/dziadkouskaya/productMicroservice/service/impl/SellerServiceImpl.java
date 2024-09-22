package pl.dziadkouskaya.graphql.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.dziadkouskaya.graphql.entity.dto.SellerDto;
import pl.dziadkouskaya.graphql.entity.dto.SellerParam;
import pl.dziadkouskaya.graphql.exception.ResourceNotFoundException;
import pl.dziadkouskaya.graphql.mapper.SellerMapper;
import pl.dziadkouskaya.graphql.repository.sql.SellerRepository;
import pl.dziadkouskaya.graphql.service.SellerService;

import java.util.List;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Slf4j
@Service
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;
    private final SellerMapper mapper;

    public SellerServiceImpl(SellerRepository sellerRepository, SellerMapper mapper) {
        this.sellerRepository = sellerRepository;
        this.mapper = mapper;
    }

    @Override
    public List<SellerDto> getAll() {
        var sellers = sellerRepository.findAll();
        log.info("Find {} sellers.", sellers.size());
        return sellers.stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public SellerDto getById(UUID uuid) {
        log.info("Get seller with id {}.", uuid);
        var seller = sellerRepository.findById(uuid);
        if (seller.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Seller with id {} is not found.", uuid));
        }
        return mapper.toDto(seller.get());
    }

    @Override
    public SellerDto createSeller(SellerParam sellerParam) {
        log.info("Create seller with name {}.", sellerParam.getName());
        var seller = sellerRepository.save(mapper.toEntity(sellerParam));
        log.info("Seller with id {} was created", seller.getId());
        return mapper.toDto(seller);
    }

    @Override
    public SellerDto updateSeller(UUID id, SellerParam sellerParam) {
        log.info("Get seller with id {}.", id);
        var initialSeller = sellerRepository.findById(id);
        if (initialSeller.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Seller with id {} is not found.", id));
        }
        if (nonNull(sellerParam.getName())) {
            initialSeller.get().setName(sellerParam.getName());
        }
        if (nonNull(sellerParam.getSearchUrl())) {
            initialSeller.get().setSearchUrl(sellerParam.getSearchUrl());
        }
        var seller = sellerRepository.save(initialSeller.get());
        log.info("Seller with id {} was updated.", id);
        return mapper.toDto(seller);
    }

    @Override
    public List<SellerDto> getSellersByName(String name) {
        var sellers = sellerRepository.findSellerByName(name);
        log.info("Found {} firms with name {}.", sellers.size(), name);
        return sellers.stream()
                .map(mapper::toDto)
                .toList();
    }
}
