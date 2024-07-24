package pl.dziadkouskaya.graphql.repository.sql;

import pl.dziadkouskaya.graphql.entity.Firm;

import java.util.List;

public interface CustomFirmRepository {
    List<Firm> findFirmByName(String name);
}
