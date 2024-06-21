package common.events;


import common.model.BeerOrderDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateBeerOrderRequest implements Serializable {

    private static final long serialVersionUID = -5421573306387090420L;

    private BeerOrderDto beerOrder;
}
