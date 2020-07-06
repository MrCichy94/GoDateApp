package pl.cichy.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    List<Comment> findAll();

    Optional<Comment> findById(Integer id);

    Page<Comment> findAll(Pageable page);

    void deleteById(Integer id);

    List<Comment> findCommentsByPlaceId();

    Comment save (Comment entity);

    boolean existsById(Integer id);

}
