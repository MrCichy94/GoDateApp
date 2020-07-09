package pl.cichy.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CommentRepository {

    List<Comment> findAll();

    boolean existsByPlaceId(Integer id);

    Optional<Comment> findById(Integer id);

    Page<Comment> findAll(Pageable page);

    void deleteById(Integer id);

    Comment save (Comment entity);

    boolean existsById(Integer id);

}
