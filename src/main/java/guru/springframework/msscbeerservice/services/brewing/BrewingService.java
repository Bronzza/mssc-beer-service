package guru.springframework.msscbeerservice.services.brewing;


import guru.springframework.msscbeerservice.config.JmsConfig;
import guru.springframework.msscbeerservice.domain.Beer;
import common.events.BrewBeerEvent;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import guru.springframework.msscbeerservice.services.inventory.BeerInventoryService;
import guru.springframework.msscbeerservice.web.mappers.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class BrewingService {

    private final BeerRepository beerRepository;
    private final BeerInventoryService inventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;


    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory() {
        List<Beer> allBeers = beerRepository.findAll();

        allBeers.forEach(beer -> {
            Integer onHand = inventoryService.getOnHandInventory(beer.getId());

            log.info("Minimum quantity on hand: {}", beer.getMinOnHand());
            log.info("Actual quantity on hand: {}", onHand);

            if (beer.getMinOnHand() >= onHand) {
                jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
            }
        });
    }

}
