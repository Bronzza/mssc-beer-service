package guru.springframework.msscbeerservice.services.inventory;


import guru.springframework.msscbeerservice.services.inventory.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Profile("local-discovery")
@Slf4j
@Service
@RequiredArgsConstructor
public class BeerInventoryServiceFeign implements BeerInventoryService {

    private final InventoryServiceFeignClient serviceFeign;


    @Override
    public Integer getOnHandInventory(UUID beerId) {
        log.info("Calling Inventory Service (using feign)");

        ResponseEntity<List<BeerInventoryDto>> responseEntity = serviceFeign.getOnHandInventory(beerId);

        //sum from inventory list
        Integer onHand = Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();

        return onHand;
    }
}
