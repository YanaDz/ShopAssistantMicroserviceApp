package pl.dziadkouskaya.search_server.service;

import pl.dziadkouskaya.search_server.entity.Seller;
import pl.dziadkouskaya.search_server.entity.dto.SellerDto;
import pl.dziadkouskaya.search_server.entity.enums.Location;
import pl.dziadkouskaya.search_server.entity.params.SellerParam;

import java.util.List;
import java.util.UUID;

public interface SellerService {
    SellerDto getById(UUID id);
    Seller getSellerById(UUID id);
    List<SellerDto> getSellers(Location location);
    List<SellerDto> getAll();
    List<Seller> getAllSellers();
    SellerDto createSeller(SellerParam param);

    void verifyExestedSeller(SellerParam param);



}
