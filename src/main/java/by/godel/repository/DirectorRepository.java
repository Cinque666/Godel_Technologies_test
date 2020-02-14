package by.godel.repository;

import by.godel.entities.Director;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DirectorRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public Director getDirectorById(Long id) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Director where id = :id", Director.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<Director> getAllDirectors() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Director", Director.class)
                .list();
    }
}
