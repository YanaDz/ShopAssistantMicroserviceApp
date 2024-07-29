package pl.dziadkouskaya.search_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.dziadkouskaya.search_server.entity.dto.SearchResult;
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


    @GetMapping
    public List<SearchResult> getSearchResults(@RequestParam String searchRequest) throws IOException {
        return searchService.getSearchResults(searchRequest);
    }


}
