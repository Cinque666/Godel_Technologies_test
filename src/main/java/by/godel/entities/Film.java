package by.godel.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(schema = "godel")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "director_id")
    private int directorId;

    @Column
    private String name;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column
    private String genre;

    public int getDirectorId() {
        return directorId;
    }

    public void setDirectorId(int directorId) {
        this.directorId = directorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Film film = (Film) o;

        if (id != film.id) return false;
        if (directorId != film.directorId) return false;
        if (name != null ? !name.equals(film.name) : film.name != null) return false;
        if (releaseDate != null ? !releaseDate.equals(film.releaseDate) : film.releaseDate != null) return false;
        return genre != null ? genre.equals(film.genre) : film.genre == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + directorId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        return result;
    }
}
