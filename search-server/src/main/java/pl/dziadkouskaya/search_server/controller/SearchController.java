package pl.dziadkouskaya.search_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.dziadkouskaya.search_server.entity.dto.SearchResult;
import pl.dziadkouskaya.search_server.entity.params.SearchParam;
import pl.dziadkouskaya.search_server.entity.params.SellerParam;
import pl.dziadkouskaya.search_server.service.SearchService;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = SearchController.PATH)
public class SearchController {
    public static final String PATH = "/search";

    @Autowired
    private SearchService searchService;


    @GetMapping
    public List<SearchResult> getSearchResults(@RequestParam String searchRequest) throws IOException, InterruptedException {
        return searchService.getSellerProducts(searchRequest);
    }


    @PostMapping(value = "/seller", produces = "application/json")
    public List<SearchResult> getSearchResults(@RequestBody SearchParam param) throws IOException, InterruptedException {
        return searchService.getSearchResults(param);
    }

}
