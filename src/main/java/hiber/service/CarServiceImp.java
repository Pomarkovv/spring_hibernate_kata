package hiber.service;

import hiber.dao.CarDao;
import hiber.model.Car;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImp implements CarService{


    private final CarDao carDao;

    @Autowired
    public CarServiceImp(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    @Transactional
    public void add(Car car) {
        carDao.add(car);
    }

    @Override
    @Transactional
    public List<Car> listCars() {
        return carDao.listCars();
    }
}
