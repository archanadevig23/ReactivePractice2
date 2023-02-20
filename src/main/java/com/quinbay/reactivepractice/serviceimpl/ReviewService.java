package com.quinbay.reactivepractice.serviceimpl;

import com.quinbay.reactivepractice.model.Review;
import reactor.core.publisher.Flux;

import java.util.List;

public class ReviewService {

    public Flux<Review> getReviews(long bookId) {
        var reviewList = List.of(
                new Review(1, bookId, 7.5, "Good"),
                new Review(2, bookId, 9.5, "Excellent")
        );
        return Flux.fromIterable(reviewList);
    }
}
