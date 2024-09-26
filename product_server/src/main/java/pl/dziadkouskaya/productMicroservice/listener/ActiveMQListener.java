package pl.dziadkouskaya.productMicroservice.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import pl.dziadkouskaya.productMicroservice.service.SellerService;

import static pl.dziadkouskaya.productMicroservice.utils.Constants.ACTIVEMQ_QUEUE_SELLER_CREATED;

@Component
public class ActiveMQListener {
    public final pl.dziadkouskaya.productMicroservice.service.SellerService sellerService;

    public ActiveMQListener(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @JmsListener(destination = ACTIVEMQ_QUEUE_SELLER_CREATED)
    public pl.dziadkouskaya.productMicroservice.entity.dto.SellerDto receiveMessage(pl.dziadkouskaya.productMicroservice.entity.dto.SellerParam sellerParam) {
        return sellerService.createSeller(sellerParam);

    }

}
