package pl.cichy.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface CommentRepository {

    Optional<Comment> findById(Integer id);

    Page<Comment> findAll(Pageable page);

    void deleteById(Integer id);

    Comment save (Comment entity);

    boolean existsById(Integer id);

}
