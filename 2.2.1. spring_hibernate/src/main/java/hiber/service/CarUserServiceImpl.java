package hiber.service;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarUserServiceImpl implements CarUserService {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public User getUserForCarDetails(String model, int series) {
        Query<User> query = sessionFactory.getCurrentSession().createQuery("from User where car.model = :model and car.series = :series", User.class);
        query.setParameter("model", model);
        query.setParameter("series", series);
        return query.getResultList().get(0);
    }

}
