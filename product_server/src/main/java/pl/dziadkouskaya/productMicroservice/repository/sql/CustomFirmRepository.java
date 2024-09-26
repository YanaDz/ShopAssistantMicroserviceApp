package pl.dziadkouskaya.productMicroservice.repository.sql;

import pl.dziadkouskaya.productMicroservice.entity.Firm;

import java.util.List;

public interface CustomFirmRepository {
    List<Firm> findFirmByName(String name);
}
