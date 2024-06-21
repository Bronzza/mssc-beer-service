package common.events;

import common.model.BeerDto;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InventoryBeerEvent extends BeerEvent{

    public InventoryBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
