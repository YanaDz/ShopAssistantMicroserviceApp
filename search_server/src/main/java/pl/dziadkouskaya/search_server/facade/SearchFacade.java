package pl.dziadkouskaya.search_server.facade;

import pl.dziadkouskaya.search_server.entity.dto.EqualProductNames;
import pl.dziadkouskaya.search_server.entity.params.SearchParam;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface SearchFacade {
    List<EqualProductNames> findResults(String param) throws IOException, InterruptedException, ExecutionException;
    List<EqualProductNames> findProductsFromSellers(SearchParam param) throws IOException, InterruptedException, ExecutionException;
}
