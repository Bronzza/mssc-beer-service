package guru.springframework.msscbeerservice.events;

import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InventoryBeerEvent extends BeerEvent{

    public InventoryBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
