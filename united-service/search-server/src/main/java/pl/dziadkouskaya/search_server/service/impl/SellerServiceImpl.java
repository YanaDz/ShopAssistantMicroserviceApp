package pl.dziadkouskaya.search_server.service.impl;

import org.springframework.stereotype.Service;
import pl.dziadkouskaya.search_server.entity.Seller;
import pl.dziadkouskaya.search_server.entity.enums.Location;
import pl.dziadkouskaya.search_server.entity.params.SellerParam;
import pl.dziadkouskaya.search_server.exception.ResourceNotFoundException;
import pl.dziadkouskaya.search_server.mapper.SellerMapper;
import pl.dziadkouskaya.search_server.repository.SellerRepository;
import pl.dziadkouskaya.search_server.service.SellerService;

import java.util.List;
import java.util.UUID;

import static pl.dziadkouskaya.search_server.utils.Constants.RESOURCE_NOT_FOUND;

@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository repository;
    private final SellerMapper sellerMapper;

    public SellerServiceImpl(SellerRepository repository, SellerMapper sellerMapper) {
        this.repository = repository;
        this.sellerMapper = sellerMapper;
    }

    @Override
    public Seller getById(UUID id) {
        var seller = repository.findById(id);
        if (seller.isEmpty()) {
            throw new ResourceNotFoundException(RESOURCE_NOT_FOUND);
        }
        return seller.get();
    }

    @Override
    public List<Seller> getSellers(Location location) {
        return repository.getSellers(location);
    }

    @Override
    public List<Seller> getAll() {
        return repository.findAll();
    }

    @Override
    public Seller createSeller(SellerParam param) {
        var seller = sellerMapper.toEntity(param);
        return repository.save(seller);
    }
}
