package pl.dziadkouskaya.search_server.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.dziadkouskaya.search_server.entity.Seller;
import pl.dziadkouskaya.search_server.entity.dto.SellerDto;
import pl.dziadkouskaya.search_server.entity.dto.SellerElementDto;
import pl.dziadkouskaya.search_server.entity.enums.Location;
import pl.dziadkouskaya.search_server.entity.params.SellerParam;
import pl.dziadkouskaya.search_server.exception.ResourceExistedException;
import pl.dziadkouskaya.search_server.exception.ResourceNotFoundException;
import pl.dziadkouskaya.search_server.mapper.SellerMapper;
import pl.dziadkouskaya.search_server.repository.SellerElementRepository;
import pl.dziadkouskaya.search_server.repository.SellerRepository;
import pl.dziadkouskaya.search_server.service.SellerService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static pl.dziadkouskaya.search_server.utils.Constants.ERROR_SELLER_EXISTED;
import static pl.dziadkouskaya.search_server.utils.Constants.RESOURCE_NOT_FOUND;

@Service
@Slf4j
public class SellerServiceImpl implements SellerService {

    private final SellerRepository repository;
    private final SellerMapper sellerMapper;

    private final SellerElementRepository elementRepository;

    public SellerServiceImpl(SellerRepository repository, SellerMapper sellerMapper, SellerElementRepository elementRepository) {
        this.repository = repository;
        this.sellerMapper = sellerMapper;
        this.elementRepository = elementRepository;
    }

    @Override
    public SellerDto getById(UUID id) {
        var seller = repository.findById(id);
        if (seller.isEmpty()) {
            throw new ResourceNotFoundException(RESOURCE_NOT_FOUND);
        }
        return sellerMapper.toDto(seller.get());
    }

    @Override
    public List<SellerDto> getSellers(Location location) {
        var sellers = repository.getSellers(location);
        log.info("Find {} sellers from location {}.", sellers.size(), location);
        return sellers.stream()
            .map(sellerMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public List<SellerDto> getAll() {
        var sellers = repository.findAll();
        log.info("Find {} sellers.", sellers.size());
        return sellers.stream()
            .map(sellerMapper::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public SellerDto createSeller(SellerParam param) {
        verifyExestedSeller(param);
        var seller = sellerMapper.toEntity(param);
        seller.getTitleProductElements()
            .forEach(title -> title.setSeller(seller));
        seller.getPrices()
            .forEach(price -> price.setSeller(seller));
        var savedSeller = repository.save(seller);
        log.info("Seller with id {} and name {} was created.", savedSeller.getId(), savedSeller.getName());
        return sellerMapper.toDto(savedSeller);
    }

    @Override
    public void verifyExestedSeller(SellerParam param) {
        var sellers = repository.getSellersByNameOrSearchUrl(param.getName(), param.getSearchUrl());
        if (!sellers.isEmpty()) {
            throw new ResourceExistedException(String.format(ERROR_SELLER_EXISTED, param.getName(), param.getSearchUrl()));
        }
    }
}
