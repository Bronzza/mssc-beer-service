package guru.springframework.msscbeerservice.bootstrap;

import guru.springframework.msscbeerservice.domain.Beer;
import guru.springframework.msscbeerservice.repositories.BeerRepository;
import common.model.BeerStyleEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by jt on 2019-05-17.
 */
@Component
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631234200036";
    public static final String BEER_2_UPC = "0631234300019";
    public static final String BEER_3_UPC = "0083783375213";
    public static final UUID BEER_1_UUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
    public static final UUID BEER_2_UUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
    public static final UUID BEER_3_UUID = UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08");

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (beerRepository.count() == 0) {
            loadBeerObjects();
        }

    }

    private void loadBeerObjects() {
        List<Beer> beersToSave = new ArrayList<Beer>();
        beersToSave.add(Beer.builder()
                .beerName("Mango Bobs")
                .beerStyle(BeerStyleEnum.IPA.name())
                .minOnHand(12)
                .quantityToBrew(200)
                .upc(BEER_1_UPC)
                .price(new BigDecimal("12.95"))
                .version(1L)
                .build());

        beersToSave.add(Beer.builder()
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyleEnum.PALE_ALE.name())
                .minOnHand(12)
                .quantityToBrew(200)
                .upc(BEER_2_UPC)
                .price(new BigDecimal("12.95"))
                .version(1L)
                .build());

        beersToSave.add(Beer.builder()
                .beerName("Pinball Porter")
                .beerStyle(BeerStyleEnum.PORTER.name())
                .minOnHand(12)
                .quantityToBrew(200)
                .upc(BEER_3_UPC)
                .price(new BigDecimal("12.95"))
                .version(1L)
                .build());

        beerRepository.saveAll(beersToSave);
    }
}
