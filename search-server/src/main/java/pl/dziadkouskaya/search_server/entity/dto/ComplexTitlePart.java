package pl.dziadkouskaya.search_server.entity.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.dziadkouskaya.search_server.entity.enums.SellerElementPriority;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComplexTitlePart {
    private String titlePart;
    private SellerElementPriority priority;
}
