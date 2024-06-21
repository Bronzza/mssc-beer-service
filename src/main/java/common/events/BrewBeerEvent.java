package common.events;

import common.events.BeerEvent;
import common.model.BeerDto;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
