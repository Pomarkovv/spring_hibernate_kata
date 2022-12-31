package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public void add(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<Car> listCars() {
      TypedQuery<Car> query=sessionFactory.getCurrentSession().createQuery("from Car");
      return query.getResultList();
   }

   public User findUser(String model, int series) {
      TypedQuery<Car> findCar = sessionFactory.getCurrentSession().createQuery("from Car where model =:model and series =:series")
              .setParameter("model", model)
              .setParameter("series", series);
      List<Car> findedCars = findCar.getResultList();
      if (!findedCars.isEmpty()) {
         List<User> users = listUsers();
         User findedUser = users.stream()
                 .filter(user -> user.getCar().equals(findedCars.get(0)))
                 .findAny()
                 .orElse(null);
         return findedUser;
      }
      return null;
   }
}
