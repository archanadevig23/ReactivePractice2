package com.quinbay.reactivepractice.serviceimpl;

import com.quinbay.reactivepractice.model.BookInfo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class BookInfoService {

    public Flux<BookInfo> getBooks() {
        var books = List.of(
                new BookInfo(1, "B1", "A1", "123456789"),
                new BookInfo(2, "B2", "A2", "123456789"),
                new BookInfo(3, "B3", "A3", "123456789")
        );

        return Flux.fromIterable(books);
    }

    public Mono<BookInfo> getBookById(long bookId) {
        var book = new BookInfo(1, "B1", "A1", "123456789");
        return Mono.just(book);
    }
}
