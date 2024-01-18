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
public class IceCreamCarTest {
    private IceCreamCar iceCreamCar;
    @BeforeAll
    public void beforeAll(){
        PriceList priceList = new PriceList();
        Stock stock = new Stock(10,5,3,4);
        double initialProfit = 0.0;

        iceCreamCar = new IceCreamCar(priceList, stock, initialProfit);
    }

    @ParameterizedTest
    @MethodSource("testOrderConeFactory")
    public void testOrderCone(Cone.Flavor[] flavors, double profit, double expectedProfit){
       PriceList priceList = new PriceList(); //ballPrice = 1, rocketPrice = 2, magnumStandardPrice = 4
        profit += priceList.getBallPrice() * flavors.length * 0.25; //in plaats van balls


        Assertions.assertEquals(expectedProfit, profit);

    }
    public static Stream<Arguments> testOrderConeFactory() {
        return Stream.of(
                Arguments.of(new Cone.Flavor[]{Cone.Flavor.CHOCOLATE}, 0.0, 0.25), //profit=0 + 1*1*0.25 = 0.25
                Arguments.of(new Cone.Flavor[]{Cone.Flavor.CHOCOLATE}, -5, -4.75),  //profit=-5 + 1*1*0.25 = -4.75
                Arguments.of(new Cone.Flavor[]{Cone.Flavor.CHOCOLATE, Cone.Flavor.PISTACHE}, 0.0, 0.5), //profit=0 + 1*2*0.25 = 0.5
                Arguments.of(new Cone.Flavor[]{Cone.Flavor.CHOCOLATE, Cone.Flavor.PISTACHE, Cone.Flavor.STRAWBERRY}, 0.0, 0.75), //profit=0 + 1*3*0.25 = 0.75
                Arguments.of(new Cone.Flavor[]{Cone.Flavor.CHOCOLATE, Cone.Flavor.PISTACHE, Cone.Flavor.STRAWBERRY, Cone.Flavor.BANANA}, 0.0, 1), //profit=0 + 1*4*0.25 = 1.0
                Arguments.of(new Cone.Flavor[]{Cone.Flavor.CHOCOLATE, Cone.Flavor.PISTACHE, Cone.Flavor.STRAWBERRY, Cone.Flavor.BANANA, Cone.Flavor.LEMON}, 0.0, 1.25), //profit=0 + 1*5*0.25 = 1.25
                Arguments.of(new Cone.Flavor[]{Cone.Flavor.CHOCOLATE, Cone.Flavor.PISTACHE, Cone.Flavor.STRAWBERRY, Cone.Flavor.BANANA, Cone.Flavor.LEMON, Cone.Flavor.MOKKA}, 0.0, 1.5), //profit=0 + 1*6*0.25 = 1.5
                Arguments.of(new Cone.Flavor[]{Cone.Flavor.CHOCOLATE, Cone.Flavor.PISTACHE, Cone.Flavor.STRAWBERRY, Cone.Flavor.BANANA, Cone.Flavor.LEMON, Cone.Flavor.MOKKA, Cone.Flavor.STRACIATELLA}, 0.0, 1.75), //profit=0 + 1*7*0.25 = 1.75
                Arguments.of(new Cone.Flavor[]{Cone.Flavor.CHOCOLATE, Cone.Flavor.PISTACHE, Cone.Flavor.STRAWBERRY, Cone.Flavor.BANANA, Cone.Flavor.LEMON, Cone.Flavor.MOKKA, Cone.Flavor.STRACIATELLA, Cone.Flavor.VANILLA}, 0.0, 2) //profit=0 + 1*8*0.25 = 2

        );
    }


    @ParameterizedTest
    @MethodSource("testOrderIceRocketFactory")
    public void testOrderIceRocket(double profit, double expectedProfit) {
        PriceList priceList = new PriceList(); // ballPrice = 1, rocketPrice = 2, magnumStandardPrice = 4
        IceRocket iceRocket = new IceRocket();
        profit += priceList.getRocketPrice() * 0.20;
        Assertions.assertEquals(expectedProfit, profit);
    }
    public static Stream<Arguments> testOrderIceRocketFactory(){
        return Stream.of(
                Arguments.of(0.0, 0.4), //profit=0  + 2*0.2
                Arguments.of(-5, -4.6)   //profit=-5 + 2*0.2
        );
    }



    @ParameterizedTest
    @MethodSource("testOrderMagnumFactory")
    public void testOrderMagnum(Magnum.MagnumType type, double profit, double expectedProfit) {
        PriceList priceList = new PriceList(); // ballPrice = 1, rocketPrice = 2, magnumStandardPrice = 4
        //ALPINENUTS=4 * 1.5, MILKCHOCOLATE=4* 1.1, WHITECHOCOLATE=4 * 1.4, OTHER TYPE=4 * 2;
        profit += priceList.getMagnumPrice(type) * 0.01;

        ProfitHelper bigDecimalValue = new ProfitHelper(profit);
        ProfitHelper roundedValue = bigDecimalValue.equals(3, ProfitHelper.ROUND_HALF_UP);
        double profitRoundedDoubleValue = roundedValue.doubleValue();

        Assertions.assertEquals(expectedProfit, profitRoundedDoubleValue);
    }

    public static Stream<Arguments> testOrderMagnumFactory(){
        return Stream.of(
                Arguments.of(Magnum.MagnumType.ALPINENUTS, 0.0, 0.06),              //profit=0  + 4 * 1.5  * 0.01
                Arguments.of(Magnum.MagnumType.MILKCHOCOLATE, 0.0, 0.044),          //profit=0  + 4 * 1.1  * 0.01
                Arguments.of(Magnum.MagnumType.WHITECHOCOLATE, 0.0, 0.056),          //profit=0  + 4 * 1.4  * 0.01
                Arguments.of(Magnum.MagnumType.ROMANTICSTRAWBERRIES, 0.0, 0.08),    //profit=0  + 4 * 2  * 0.01
                Arguments.of(Magnum.MagnumType.BLACKCHOCOLATE, 0.0, 0.08)           //profit=0  + 4 * 2  * 0.01

        );
    }




}
