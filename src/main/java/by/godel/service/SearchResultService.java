package by.godel.service;

import by.godel.entities.Director;
import by.godel.entities.Film;
import by.godel.repository.DirectorRepository;
import by.godel.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SearchResultService {

    @Autowired
    private DirectorRepository directorRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Transactional
    public Director getDirectorById(Long id) {
        return directorRepository.getDirectorById(id);
    }

    @Transactional
    public List<Film> getFilmByDirectorId(Long id) {
        return filmRepository.getFilmByDirectorId(id);
    }

    @Transactional
    public List<Film> getFilmByIdAndByDate(Long valueOf, String date) {
        return filmRepository.getFilmByIdAndByDate(valueOf, date);
    }

    @Transactional
    public List<Film> getFilmsByDate(String date) {
        return filmRepository.getFilmsByDate(date);
    }

    @Transactional
    public List<Director> getAllDirectors() {
        return directorRepository.getAllDirectors();
    }

    @Transactional
    public List<Film> getFilmsByDirectorId(long id) {
        return filmRepository.getFilmsByDirectorId(id);
    }
}
