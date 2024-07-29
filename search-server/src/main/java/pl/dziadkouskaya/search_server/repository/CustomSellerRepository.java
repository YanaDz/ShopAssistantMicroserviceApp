package pl.dziadkouskaya.search_server.repository;

import pl.dziadkouskaya.search_server.entity.Seller;
import pl.dziadkouskaya.search_server.entity.enums.Location;

import java.util.List;

public interface CustomSellerRepository {
    List<Seller> getSellers(Location location);
}
