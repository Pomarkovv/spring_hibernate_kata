package hiber.dao;


import hiber.model.Car;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CarDaoImp implements CarDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public List<Car> listCars() {
        Query query=sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }
}
