package guru.springframework.msscbeerservice.events;


import guru.springframework.msscbeerservice.web.model.BeerDto;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeerEvent implements Serializable {

    private static final long serialVersionUID = 2927752115590204175L;

    private BeerDto beerDto;
}
