package pl.dziadkouskaya.productMicroservice.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import pl.dziadkouskaya.graphql.service.SellerService;

import static pl.dziadkouskaya.graphql.utils.Constants.ACTIVEMQ_QUEUE_SELLER_CREATED;

@Component
public class ActiveMQListener {
    public final pl.dziadkouskaya.graphql.service.SellerService sellerService;

    public ActiveMQListener(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @JmsListener(destination = ACTIVEMQ_QUEUE_SELLER_CREATED)
    public pl.dziadkouskaya.graphql.entity.dto.SellerDto receiveMessage(pl.dziadkouskaya.graphql.entity.dto.SellerParam sellerParam) {
        return sellerService.createSeller(sellerParam);

    }

}
