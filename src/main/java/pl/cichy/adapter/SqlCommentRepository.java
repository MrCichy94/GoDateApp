package pl.cichy.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.cichy.model.Comment;
import pl.cichy.model.CommentRepository;

import java.util.List;

@Repository
public interface SqlCommentRepository extends CommentRepository, JpaRepository<Comment, Integer> {

    @Override
    @Query("select distinct g from Place g join fetch g.comments")
    List<Comment> findAll();
}