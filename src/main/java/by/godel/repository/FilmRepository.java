package by.godel.repository;

import by.godel.entities.Film;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class FilmRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Film> getFilmByDirectorId(Long id) {
        return sessionFactory.getCurrentSession()
                    .createQuery("from Film where director_id = :director_id", Film.class)
                    .setParameter("director_id", id)
                    .list();
    }

    public List<Film> getFilmByIdAndByDate(Long valueOf, String date) {
        Timestamp timestamp = Timestamp.valueOf(date + " 00:00:01.0");

        return sessionFactory.getCurrentSession()
                .createQuery("from Film where director_id = :director_id and release_date >= :date", Film.class)
                .setParameter("director_id", valueOf)
                .setParameter("date", timestamp)
                .list();
    }

    public List<Film> getFilmsByDate(String date) {
        Timestamp timestamp = Timestamp.valueOf(date + " 00:00:01.0");

        return sessionFactory.getCurrentSession()
                .createQuery("from Film where release_date >= :date", Film.class)
                .setParameter("date", timestamp)
                .list();
    }

    public List<Film> getFilmsByDirectorId(long id) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Film where director_id = :id", Film.class)
                .setParameter("id", id)
                .list();
    }
}
