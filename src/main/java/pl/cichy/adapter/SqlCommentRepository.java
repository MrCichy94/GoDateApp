package pl.cichy.adapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.cichy.model.Comment;
import pl.cichy.model.CommentRepository;

@Repository
public interface SqlCommentRepository extends CommentRepository, JpaRepository<Comment, Integer> {

}
