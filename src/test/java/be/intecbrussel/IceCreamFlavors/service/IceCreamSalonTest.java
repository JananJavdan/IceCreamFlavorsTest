package be.intecbrussel.IceCreamFlavors.service;

import be.intecbrussel.IceCreamFlavors.eatables.Cone;
import be.intecbrussel.IceCreamFlavors.eatables.IceRocket;
import be.intecbrussel.IceCreamFlavors.eatables.Magnum;
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
        PriceList priceList = new PriceList(2.0, 1.5, 3.0);//creat priclist voor testing
        iceCreamSalon = new IceCreamSalon(priceList);//instantie
    }
    @ParameterizedTest
    @MethodSource("testOrderConeFactory")
    public void testOrderCone(Cone.Flavor[] flavors, double expectedProfit){
        PriceList priceList = new PriceList();
        IceCreamSalon iceCreamSalon = new IceCreamSalon(priceList);
        iceCreamSalon.orderCone(flavors);

        Assertions.assertEquals(expectedProfit, iceCreamSalon.getProfit());

    }
    public static Stream<Arguments> testOrderConeFactory(){
        return Stream.of(
                Arguments.of(new Cone.Flavor[]{Cone.Flavor.CHOCOLATE}, 0.0, 0.2),//profit=0
                Arguments.of(new Cone.Flavor[]{Cone.Flavor.LEMON}, -5, -4.8),//profit=-5
                Arguments.of(new Cone.Flavor[]{Cone.Flavor.STRAWBERRY}, -4, 5.6)
        );
    }
    @ParameterizedTest
    @MethodSource("testOrderIceRocketFactory")
    public void testOrderIceRocket(double profit, double expectedProfit){
        PriceList priceList = new PriceList();
        IceCreamSalon iceCreamSalon = new IceCreamSalon(priceList);
        iceCreamSalon.orderIceRocket();

        Assertions.assertEquals(expectedProfit, iceCreamSalon.getProfit());
    }
    public static Stream<Arguments> testOrderIceRocketFactory(){
        return Stream.of(
                Arguments.of(0.0, 0.06),//profit=0
                Arguments.of(-5, -4.94)//profit=-5
        );
    }
    @ParameterizedTest
    @MethodSource("testOrderMagnumFactory")
    public void testOrderMagnum(Magnum.MagnumType type, double profit, double expectedProfit){
        PriceList priceList = new PriceList();
        IceCreamSalon iceCreamSalon = new IceCreamSalon(priceList);
        iceCreamSalon.orderMagnum(type);

        Assertions.assertEquals(expectedProfit, iceCreamSalon.getProfit());
    }
    public static Stream<Arguments> testOrderMagnumFactory(){
        return Stream.of(
                Arguments.of(Magnum.MagnumType.WHITECHOCOLATE, 0.0, 0.06),
                Arguments.of(Magnum.MagnumType.ROMANTICSTRAWBERRIES, -5, -4.94)
        );
    }

}

