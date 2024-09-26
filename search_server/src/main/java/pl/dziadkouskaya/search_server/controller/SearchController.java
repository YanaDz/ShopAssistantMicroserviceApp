package pl.dziadkouskaya.search_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.dziadkouskaya.search_server.entity.dto.EqualProductNames;
import pl.dziadkouskaya.search_server.entity.dto.SearchResult;
import pl.dziadkouskaya.search_server.entity.params.SearchParam;
import pl.dziadkouskaya.search_server.facade.SearchFacade;
import pl.dziadkouskaya.search_server.service.SearchService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = SearchController.PATH)
public class SearchController {
    public static final String PATH = "/search";

    @Autowired
    private SearchService searchService;

    @Autowired
    private SearchFacade searchFacade;

    @GetMapping
    public List<List<SearchResult>> getSearchResults(@RequestParam String searchRequest) throws IOException, InterruptedException {
        return searchService.getSellerProducts(searchRequest);
    }


    @PostMapping(value = "/seller", produces = "application/json")
    public List<EqualProductNames> getSearchResults(@RequestBody SearchParam param) throws IOException, InterruptedException {
        return searchFacade.findProductsFromSellers(param);
    }

    @GetMapping(value = "/data", produces = "application/json")
    public List<EqualProductNames> getComparedResults(@RequestParam String param) throws IOException, InterruptedException {
        return searchFacade.findResults(param);
    }

}
