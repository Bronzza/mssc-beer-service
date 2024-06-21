package guru.springframework.msscbeerservice.services.beerorder;

import common.events.ValidateBeerOrderRequest;
import common.events.ValidateBeerOrderResponse;
import common.model.BeerOrderDto;
import guru.springframework.msscbeerservice.config.JmsConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BeerOrderValidationListener {

    private final JmsTemplate jmsTemplate;
    private final BeerOrderValidator beerOrderValidator;

    @Transactional
    @JmsListener(destination = JmsConfig.BEER_ORDER_VALIDATION_REQUEST)
    public void listen(ValidateBeerOrderRequest event){
        BeerOrderDto beerOrder = event.getBeerOrder();
        Boolean isValid = beerOrderValidator.validateBeerOrderByOrderLinesUpc(beerOrder);

        ValidateBeerOrderResponse validateBeerOrderResponse = new ValidateBeerOrderResponse(beerOrder.getId(), isValid);
        log.info("Validate beer order response, is order valid {}", isValid);
        jmsTemplate.convertAndSend(JmsConfig.BEER_ORDER_VALIDATION_RESPONSE, validateBeerOrderResponse);
    }
}
