package guru.springframework.msscbeerservice.services.inventory;

import guru.springframework.msscbeerservice.services.inventory.model.BeerInventoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Component
@RequiredArgsConstructor
public class InventoryServiceFeignImplFailover implements InventoryServiceFeignClient {

    private final InventoryFailoverFeignClient failoverFeignClient;

    @Override
    public ResponseEntity<List<BeerInventoryDto>> getOnHandInventory(UUID beerId) {
        ArrayList<BeerInventoryDto> result = new ArrayList<>();
        result.add(failoverFeignClient.getOnHandInventory().getBody());
        return ResponseEntity.ok().body(result);
    }
}
