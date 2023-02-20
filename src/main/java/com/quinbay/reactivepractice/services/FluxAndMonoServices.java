package com.quinbay.reactivepractice.services;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMonoServices {
    public Flux<String> usersFlux() {
        return Flux.fromIterable(List.of("Archana", "Vasunthra", "Kowreesh")).log();
    }

    public Flux<String> usersFluxMap() {
        return Flux.fromIterable(List.of("Archana", "Vasunthra", "Kowreesh"))
                .log()
                .map(String::toUpperCase)
                .log();
    }

    public Flux<String> usersFluxFilter(int num) {
        return Flux.fromIterable(List.of("Archana", "Vasunthra", "Kowreesh"))
                .filter(s -> s.length()>num);
    }

    public Flux<String> usersFluxFilterAndMap(int num) {
        return Flux.fromIterable(List.of("Archana", "Vasunthra", "Kowreesh"))
                .filter(s -> s.length()>num)
                .map(s -> s.toUpperCase());
    }

    public Flux<String> usersFluxFlatMap() {
        return Flux.fromIterable(List.of("Archana", "Vasunthra", "Kowreesh"))
                .flatMap(s -> Flux.just(s.split("")))
                .log();
    }

    public Flux<String> usersFluxFlatMapAsync() {
        return Flux.fromIterable(List.of("Archana", "Vasunthra", "Kowreesh"))
                .flatMap(s -> Flux.just(s.split(""))
                .delayElements(Duration.ofMillis(
                        new Random().nextInt(1000)
                )))
                .log();
    }

    public Flux<String> usersFluxConcatMap() {
        return Flux.fromIterable(List.of("Archana", "Vasunthra", "Kowreesh"))
                .concatMap(s -> Flux.just(s.split(""))
                        .delayElements(Duration.ofMillis(
                                new Random().nextInt(1000)
                        )))
                .log();
    }

    public Flux<String> usersFluxTransform() {
        Function<Flux<String>, Flux<String>> filterData
                = data -> data.filter(s -> s.length()>7);
        return Flux.fromIterable(List.of("Archana", "Vasunthra", "Kowreesh"))
                .transform(filterData);
    }

    public Flux<String> usersFluxTransformDefIfEmpty() {
        Function<Flux<String>, Flux<String>> filterData
                = data -> data.filter(s -> s.length()>20);
        return Flux.fromIterable(List.of("Archana", "Vasunthra", "Kowreesh"))
                .log()
                .transform(filterData)
                .defaultIfEmpty("Default");
    }

    public Flux<String> usersFluxTransformSwitchIfEmpty() {
        Function<Flux<String>, Flux<String>> filterData
                = data -> data.filter(s -> s.length()>10);
        return Flux.fromIterable(List.of("Archana", "Vasunthra", "Kowreesh"))
                .transform(filterData)
                .switchIfEmpty(Flux.just("Jaisri Velusamy", "Vikashini"))
                .transform(filterData)
                .log();
    }

    public Flux<String> usersFluxConcat() {
        var userList1 = Flux.just("Archana", "Vasunthra");
        var userList2 = Flux.just("Kowreesh", "Jaisri");
        return Flux.concat(userList1, userList2);
    }

    public Flux<String> usersFluxConcatWith() {
        var userList1 = Flux.just("Archana", "Vasunthra");
        var userList2 = Flux.just("Kowreesh", "Jaisri");
        return userList1.concatWith(userList2);
    }

    public Flux<String> usersMonoConcat() {
        var user1 = Mono.just("Archana");
        var user2 = Mono.just("Kowreesh");
        return Flux.concat(user1, user2).log();
    }

    public Flux<String> usersMonoConcatWith() {
        var user1 = Mono.just("Archana");
        var user2 = Mono.just("Kowreesh");
        return user1.concatWith(user2).log();
    }

    public Flux<String> usersFluxMerge() {
        var userList1 = Flux.just("Archana", "Vasunthra")
                .delayElements(Duration.ofMillis(50));
        var userList2 = Flux.just("Kowreesh", "Jaisri")
                        .delayElements(Duration.ofMillis(100));
        return Flux.merge(userList1, userList2);
    }

    public Flux<String> usersFluxMergeWith() {
        var userList1 = Flux.just("Archana", "Vasunthra")
                .delayElements(Duration.ofMillis(50));
        var userList2 = Flux.just("Kowreesh", "Jaisri")
                .delayElements(Duration.ofMillis(100));
        return userList1.mergeWith(userList2);
    }

    public Flux<String> usersFluxMergeWithSequential() {
        var userList1 = Flux.just("Archana", "Vasunthra")
                .delayElements(Duration.ofMillis(50));
        var userList2 = Flux.just("Kowreesh", "Jaisri")
                .delayElements(Duration.ofMillis(100));
        return Flux.mergeSequential(userList1, userList2);
    }

    public Flux<String> usersFluxZip() {
        var userList1 = Flux.just("Archana", "Vasunthra")
                .delayElements(Duration.ofMillis(50));
        var userList2 = Flux.just("Kowreesh", "Jaisri")
                .delayElements(Duration.ofMillis(100));
        return Flux.zip(userList1, userList2,
                (first, second) -> first+second).log();
    }

    public Flux<String> usersFluxZipWith() {
        var userList1 = Flux.just("Archana", "Vasunthra")
                .delayElements(Duration.ofMillis(50));
        var userList2 = Flux.just("Kowreesh", "Jaisri")
                .delayElements(Duration.ofMillis(100));
        return userList1.zipWith(userList2, (first, second) -> first+second);
    }

    public Flux<String> usersFluxZipTuple() {
        var userList1 = Flux.just("Archana", "Vasunthra")
                .delayElements(Duration.ofMillis(50));
        var userList2 = Flux.just("Kowreesh", "Jaisri")
                .delayElements(Duration.ofMillis(100));
        var userList3 = Flux.just("Harish", "Ruthsan")
                .delayElements(Duration.ofMillis(35));
        return Flux.zip(userList1, userList2, userList3).map(obj -> obj.getT1() + obj.getT2() + obj.getT3());
    }

    public Flux<String> usersFluxDoOn() {
        Function<Flux<String>, Flux<String>> filterData
                = data -> data.filter(s -> s.length()>7)
                .doOnNext(s -> {
                    System.out.println("s - " + s);
                })
                .doOnSubscribe(subscription -> {
                    System.out.println("Subscription.toString() = " + subscription.toString());
                })
                .doOnComplete(() -> System.out.println("Completed!"));
        return Flux.fromIterable(List.of("Archana", "Vasunthra", "Kowreesh"))
                .transform(filterData);
    }

    public Mono<Integer> phoneNoMono() {
        return Mono.just(63764).log();
    }

    public Mono<Integer> phoneNoMonoFlatMap() {
        return Mono.just(63764)
                .flatMap(s -> Mono.just(84754))
                .log();
    }

    public Flux<String> phoneNoMonoFlatMapMany() {
        return Mono.just("Archana")
                .flatMapMany(s -> Flux.just(s.split("")))
                .log();
    }

    public Mono<String> phoneNoMonoZipWith() {
        var user1 = Mono.just("Archana");
        var user2 = Mono.just("Vasunthra");
        return user1.zipWith(user2, (name1, name2) -> name1+name2).log();
    }

    public Flux<String> usersFluxOnErrorReturn() {
        return Flux.just("Archana", "Vasunthra")
                .concatWith(Flux.error(new RuntimeException("Exception occurred")))
                .onErrorReturn("Username");
    }

    public Flux<String> usersFluxOnErrorContinue() {
        return Flux.just("Archana", "Vasunthra")
                .map(s -> {
                    if(s.equalsIgnoreCase("Archana"))
                        throw new RuntimeException("Exception Occurred");
                    else return s.toUpperCase();
                })
                .onErrorContinue((e, item) -> System.out.println(item))
                .log();
    }

    public Flux<String> usersFluxOnErrorMap() {
        return Flux.just("Archana", "Vasunthra")
                .map(s -> {
                    if(s.equalsIgnoreCase("Archana"))
                        throw new RuntimeException("Exception Occurred");
                    else return s.toUpperCase();
                })
                .onErrorMap(throwable -> {
                    System.out.println("Throwable " + throwable);
                    return new IllegalStateException("From onErrorMap");
                })
                .log();
    }

    public Flux<String> usersFluxOnError() {
        return Flux.just("Archana", "Vasunthra")
                .map(s -> {
                    if(s.equalsIgnoreCase("Archana"))
                        throw new RuntimeException("Exception Occurred");
                    else return s.toUpperCase();
                })
                .doOnError(throwable -> {
                    System.out.println("Throwable " + throwable);
                })
                .log();
    }



    public static void main(String[] args) {
        FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();

//        fluxAndMonoServices.usersFlux().subscribe(System.out::println);
//
//        fluxAndMonoServices.phoneNoMono().subscribe(System.out::println);
    }
}
