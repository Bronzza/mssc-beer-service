package guru.springframework.msscbeerservice.services.beerorder;


import common.model.BeerOrderDto;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderValidator {

    private final BeerRepository repository;

    public Boolean validateBeerOrderByOrderLinesUpc(BeerOrderDto beerOrder) {
        return beerOrder.getBeerOrderLines()
                .stream()
                .map(orderLine -> {
                    String upc = orderLine.getUpc();
                    return repository.findByUpc(upc);
                }).noneMatch(Optional::isEmpty);
    }
}
