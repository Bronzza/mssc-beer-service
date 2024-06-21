package common.events;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateBeerOrderResponse implements Serializable {

    private static final long serialVersionUID = 3665536387829235869L;
    private UUID orderId;
    private Boolean isValid;
}
