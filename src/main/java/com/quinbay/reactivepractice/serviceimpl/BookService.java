package com.quinbay.reactivepractice.serviceimpl;

import com.quinbay.reactivepractice.exception.BookException;
import com.quinbay.reactivepractice.model.Book;
import com.quinbay.reactivepractice.model.BookInfo;
import com.quinbay.reactivepractice.model.Review;
import lombok.extern.slf4j.Slf4j;
import reactor.core.CoreSubscriber;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

import java.time.Duration;

@Slf4j
public class BookService {

    BookInfoService bookInfoService;

    public BookService(BookInfoService bookInfoService, ReviewService reviewService) {
        this.bookInfoService = bookInfoService;
        this.reviewService = reviewService;
    }

    ReviewService reviewService;

    public Flux<Book> getBooks() {
        var allBooks = bookInfoService.getBooks();
        return allBooks.flatMap(bookInfo -> {
            var reviews = reviewService.getReviews(bookInfo.getBookId()).collectList();
            return reviews
                    .map(review -> new Book(bookInfo, review));
        })
        .onErrorMap(throwable -> {
            log.error("Exception is " + throwable);
            return new BookException("Exception occurred while fetching books");
        })
        .log();
    }

    public Mono<Book> getBookById(long bookId) {
        var bookInfo = bookInfoService.getBookById(bookId);
        var reviewInfo = reviewService.getReviews(bookId).collectList();
        return bookInfo
                .zipWith(reviewInfo, (b, r) -> new Book(b, r))
                .log();
    }

    public Flux<Book> getAllBooksMock() {
        var allBooks = bookInfoService.getBooks();
        return allBooks.flatMap(bookInfo -> {
            var reviews = reviewService.getReviews(bookInfo.getBookId()).collectList();
            return reviews
                    .map(review -> new Book(bookInfo, review));
        })
        .onErrorMap(throwable -> {
            log.error("Exception is " + throwable);
            return new BookException("Exception occurred while fetching books");
        })
        .log();
    }

    public Flux<Book> getBooksRetry() {
        var allBooks = bookInfoService.getBooks();
        return allBooks.flatMap(bookInfo -> {
            var reviews = reviewService.getReviews(bookInfo.getBookId()).collectList();
            return reviews
                .map(review -> new Book(bookInfo, review)); })
                .onErrorMap(throwable -> {
                    log.error("Exception is " + throwable);
                    return new BookException("Exception occurred while fetching books");
                })
                .retry(5)
                .log();
    }

    public Flux<Book> getBooksRetryWhen() {
//        var retrySpec = getRetrySpec();
        var allBooks = bookInfoService.getBooks();
        return allBooks.flatMap(bookInfo -> {
            var reviews = reviewService.getReviews(bookInfo.getBookId()).collectList();
            return reviews
                    .map(review -> new Book(bookInfo, review)); })
                .onErrorMap(throwable -> {
                    log.error("Exception is " + throwable);
                    return new BookException("Exception occurred while fetching books");
                })
                .retryWhen(getRetrySpec())
                .log();
    }

    private RetryBackoffSpec getRetrySpec() {
        return Retry.backoff(
                3,
                Duration.ofMillis(1000))
                .filter(throwable -> throwable instanceof BookException)
                .onRetryExhaustedThrow((retryBackoffSpec, retrySignal) ->
                    Exceptions.propagate(retrySignal.failure())
                );
    }
}
