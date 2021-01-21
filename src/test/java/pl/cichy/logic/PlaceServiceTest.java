package pl.cichy.logic;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.cichy.model.Comment;
import pl.cichy.model.Place;
import pl.cichy.model.repository.CommentRepository;
import pl.cichy.model.repository.PlaceRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PlaceServiceTest {

    @Test
    @DisplayName("Should throw ResourceNotFoundException when we dont got place with given place ID")
    void createCommentForPlaceById_ThrowResourceNotFoundException() {

        //given
        var mockPlaceRepository = mock(PlaceRepository.class);
        when(mockPlaceRepository.existsById(1)).thenReturn(false);

        //and
        var mockCommentRepository = mock(CommentRepository.class);

        //and
        var mockComment = mock(Comment.class);

        //system under test
        var toTest = new PlaceService(mockCommentRepository, mockPlaceRepository);

        //when
        var exception = catchThrowable(()->toTest.createCommentForPlaceById(1, mockComment));

        //than
        assertThat(exception)
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("No place found");
    }
}
