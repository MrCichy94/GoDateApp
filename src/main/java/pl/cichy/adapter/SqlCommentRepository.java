package pl.cichy.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.cichy.model.Comment;
import pl.cichy.model.CommentRepository;

import java.util.List;
import java.util.Set;

@Repository
public interface SqlCommentRepository extends CommentRepository, JpaRepository<Comment, Integer> {

    @Override
    @Query("select distinct g from Place g join fetch g.comments")
    List<Comment> findAll();

}
