package org.study.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

/**
 * @author lipo
 * @version v1.0
 * @date 2019-12-20 11:47
 */
public class CarSalon {
    public static void main(String[] args) {
        Car car = new Car();
        car.startCar();
    }
}

@Configurable(preConstruction = true)
@Component
class Car {
    @Autowired
    private Engine engine;
    @Autowired
    private Transmission transmission;

    public void startCar() {
        transmission.setGear(1);
        engine.engineOn();
        System.out.println("Car started");
    }
}

@Component
class Engine {
    public void engineOn() {

    }
//...
}

@Component
class Transmission {
    public void setGear(int i) {
    }

//...
}
