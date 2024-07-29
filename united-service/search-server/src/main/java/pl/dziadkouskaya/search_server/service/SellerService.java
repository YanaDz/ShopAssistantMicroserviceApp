package pl.dziadkouskaya.search_server.service;

import pl.dziadkouskaya.search_server.entity.Seller;
import pl.dziadkouskaya.search_server.entity.enums.Location;
import pl.dziadkouskaya.search_server.entity.params.SellerParam;

import java.util.List;
import java.util.UUID;

public interface SellerService {
    Seller getById(UUID id);
    List<Seller> getSellers(Location location);
    List<Seller> getAll();
    Seller createSeller(SellerParam param);


}
