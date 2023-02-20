package com.quinbay.reactivepractice.serviceimpl;

import com.quinbay.reactivepractice.exception.BookException;
import com.quinbay.reactivepractice.model.BookInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BookServiceMockTest {

    @Mock
    private BookInfoService bookInfoService;

    @Mock
    private ReviewService reviewService;

    @InjectMocks
    private BookService bookService;

    @Test
    void getAllBooksMock() {

        Mockito.when(bookInfoService.getBooks())
                .thenCallRealMethod();
        Mockito.when(reviewService.getReviews(Mockito.anyLong()))
                .thenCallRealMethod();

        var books = bookService.getBooks();
        StepVerifier.create(books)
                .expectNextCount(3)
                .verifyComplete();

    }

    @Test
    void getAllBooksMockOnError() {

        Mockito.when(bookInfoService.getBooks())
                .thenCallRealMethod();
        Mockito.when(reviewService.getReviews(Mockito.anyLong()))
                .thenThrow(new IllegalStateException("exception using test"));

        var books = bookService.getAllBooksMock();
        StepVerifier.create(books)
                .expectError(BookException.class)
                .verify();

    }

    @Test
    void getAllBooksMockOnErrorRetry() {

        Mockito.when(bookInfoService.getBooks())
                .thenCallRealMethod();
        Mockito.when(reviewService.getReviews(Mockito.anyLong()))
                .thenThrow(new IllegalStateException("exception using test"));

        var books = bookService.getBooksRetry();
        StepVerifier.create(books)
                .expectError(BookException.class)
                .verify();
    }

    @Test
    void getAllBooksMockOnErrorRetryWhen() {

        Mockito.when(bookInfoService.getBooks())
                .thenCallRealMethod();
        Mockito.when(reviewService.getReviews(Mockito.anyLong()))
                .thenThrow(new IllegalStateException("exception using test"));

        var books = bookService.getBooksRetryWhen();
        StepVerifier.create(books)
                .expectError(BookException.class)
                .verify();
    }
}