package be.intecbrussel.IceCreamFlavors.service;

import be.intecbrussel.IceCreamFlavors.eatables.Cone;
import be.intecbrussel.IceCreamFlavors.eatables.IceRocket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IceCreamSalonTest {
    private IceCreamSalon iceCreamSalon;
    @BeforeAll
    public void beforeAll(){
        PriceList priceList = new PriceList(2.0, 1.5, 3.0);
        iceCreamSalon = new IceCreamSalon(priceList);
    }
    @ParameterizedTest
    @MethodSource("provideOrderConeData")
    public void testOrderCone(){
        Cone.Flavor[] flavors = {Cone.Flavor.CHOCOLATE, Cone.Flavor.VANILLA};
        Cone cone = iceCreamSalon.orderCone(flavors);

        Assertions.assertEquals(2, cone.getBalls().length);
        Assertions.assertEquals(0.5, iceCreamSalon.getProfit());
    }
    public Stream<Arguments> provideOrderConeData(){
        return Stream.of(
                Arguments.of(2,6)
        );
    }
    @ParameterizedTest
    @MethodSource("provideOrderIceRocketData")
    public void testOrderIceRocket(){
        IceRocket iceRocket = iceCreamSalon.orderIceRocket();
        Assertions.assertEquals(0.6, iceCreamSalon.getProfit());
    }

}

