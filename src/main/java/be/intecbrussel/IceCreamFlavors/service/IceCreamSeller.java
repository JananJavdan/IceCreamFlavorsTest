package be.intecbrussel.IceCreamFlavors.service;

import be.intecbrussel.IceCreamFlavors.eatables.Cone;
import be.intecbrussel.IceCreamFlavors.eatables.IceRocket;
import be.intecbrussel.IceCreamFlavors.eatables.Magnum;


public interface IceCreamSeller extends Profitable {
    Cone orderCone(Cone.Flavor[] balls);
    IceRocket orderIceRocket();
    Magnum orderMagnum(Magnum.MagnumType magnumType);


}
