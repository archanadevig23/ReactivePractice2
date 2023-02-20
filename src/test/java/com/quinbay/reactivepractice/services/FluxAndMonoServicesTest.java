package com.quinbay.reactivepractice.services;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoServicesTest {

    FluxAndMonoServices fluxAndMonoServices = new FluxAndMonoServices();

    @Test
    void usersFlux() {
        var usersFlux = fluxAndMonoServices.usersFlux();
        StepVerifier.create(usersFlux)
        .expectNext("Archana", "Vasunthra", "Kowreesh")
        .verifyComplete();
    }

    @Test
    void phoneNoMono() {
        var phoneNoMono = fluxAndMonoServices.phoneNoMono();
        StepVerifier.create(phoneNoMono)
                .expectNext(63764)
                .verifyComplete();
    }

    @Test
    void usersFluxMap() {
        var usersFluxMap = fluxAndMonoServices.usersFluxMap();
        StepVerifier.create(usersFluxMap)
                .expectNext("ARCHANA", "VASUNTHRA", "KOWREESH")
                .verifyComplete();
    }

    @Test
    void usersFluxFilter() {
        var usersFluxFilter = fluxAndMonoServices.usersFluxFilter(7).log();
        StepVerifier.create(usersFluxFilter)
                .expectNext("Vasunthra", "Kowreesh")
                .verifyComplete();
    }

    @Test
    void usersFluxFilterAndMap() {
        var usersFluxFilterAndMap = fluxAndMonoServices.usersFluxFilterAndMap(7).log();
        StepVerifier.create(usersFluxFilterAndMap)
                .expectNext("VASUNTHRA", "KOWREESH")
                .verifyComplete();
    }

    @Test
    void usersFluxFlatMap() {
        var usersFluxFlatMap = fluxAndMonoServices.usersFluxFlatMap();
        StepVerifier.create(usersFluxFlatMap)
        .expectNextCount(24)
        .verifyComplete();
    }

    @Test
    void usersFluxFlatMapAsync() {
        var usersFluxFlatMapAsync = fluxAndMonoServices.usersFluxFlatMapAsync();
        StepVerifier.create(usersFluxFlatMapAsync)
                .expectNextCount(24)
                .verifyComplete();
    }

    @Test
    void phoneNoMonoFlatMap() {
        var phoneNoMonoFlatMap = fluxAndMonoServices.phoneNoMonoFlatMap();
        StepVerifier.create(phoneNoMonoFlatMap)
                .expectNext(84754)
                .verifyComplete();
    }

    @Test
    void usersFluxConcatMap() {
        var usersFluxConcatMap = fluxAndMonoServices.usersFluxConcatMap();
        StepVerifier.create(usersFluxConcatMap)
                .expectNextCount(24)
                .verifyComplete();
    }

    @Test
    void phoneNoMonoFlatMapMany() {
        var phoneNoMonoFlatMapMany = fluxAndMonoServices.phoneNoMonoFlatMapMany();
        StepVerifier.create(phoneNoMonoFlatMapMany)
                .expectNextCount(7)
                .verifyComplete();
    }

    @Test
    void usersFluxTransform() {
        var usersFluxTransform = fluxAndMonoServices.usersFluxTransform();
        StepVerifier.create(usersFluxTransform)
                .expectNext("Vasunthra", "Kowreesh")
                .verifyComplete();
    }

    @Test
    void usersFluxTransformDefIfEmpty() {
        var usersFluxTransformDefIfEmpty = fluxAndMonoServices.usersFluxTransformDefIfEmpty();
        StepVerifier.create(usersFluxTransformDefIfEmpty)
                .expectNext("Default")
                .verifyComplete();
    }

    @Test
    void usersFluxTransformSwitchIfEmpty() {
        var usersFluxTransformSwitchIfEmpty = fluxAndMonoServices.usersFluxTransformSwitchIfEmpty();
        StepVerifier.create(usersFluxTransformSwitchIfEmpty)
                .expectNext("Jaisri Velusamy")
                .verifyComplete();
    }

    @Test
    void usersFluxConcat() {
        var usersFluxConcat = fluxAndMonoServices.usersFluxConcat();
        StepVerifier.create(usersFluxConcat)
                .expectNext("Archana", "Vasunthra", "Kowreesh", "Jaisri")
                .verifyComplete();
    }

    @Test
    void usersFluxConcatWith() {
        var usersFluxConcatWith = fluxAndMonoServices.usersFluxConcatWith();
        StepVerifier.create(usersFluxConcatWith)
                .expectNext("Archana", "Vasunthra", "Kowreesh", "Jaisri")
                .verifyComplete();
    }

    @Test
    void usersMonoConcat() {
        var usersMonoConcat = fluxAndMonoServices.usersMonoConcat();
        StepVerifier.create(usersMonoConcat)
                .expectNext("Archana", "Kowreesh")
                .verifyComplete();
    }

    @Test
    void usersMonoConcatWith() {
        var usersMonoConcatWith = fluxAndMonoServices.usersMonoConcatWith();
        StepVerifier.create(usersMonoConcatWith)
                .expectNext("Archana", "Kowreesh")
                .verifyComplete();
    }

    @Test
    void usersFluxMerge() {
        var usersFluxMerge = fluxAndMonoServices.usersFluxMerge();
        StepVerifier.create(usersFluxMerge)
                .expectNext("Archana", "Kowreesh", "Vasunthra", "Jaisri")
                .verifyComplete();
    }

    @Test
    void usersFluxMergeWith() {
        var usersFluxMergeWith = fluxAndMonoServices.usersFluxMergeWith();
        StepVerifier.create(usersFluxMergeWith)
                .expectNext("Archana", "Kowreesh", "Vasunthra", "Jaisri")
                .verifyComplete();
    }

    @Test
    void usersFluxMergeWithSequential() {
        var usersFluxMergeWithSequential = fluxAndMonoServices.usersFluxMergeWithSequential();
        StepVerifier.create(usersFluxMergeWithSequential)
                .expectNext("Archana", "Vasunthra", "Kowreesh", "Jaisri")
                .verifyComplete();
    }

    @Test
    void usersFluxZip() {
        var usersFluxZip = fluxAndMonoServices.usersFluxZip();
        StepVerifier.create(usersFluxZip)
                .expectNext("ArchanaKowreesh", "VasunthraJaisri")
                .verifyComplete();
    }

    @Test
    void usersFluxZipWith() {
        var usersFluxZipWith = fluxAndMonoServices.usersFluxZipWith();
        StepVerifier.create(usersFluxZipWith)
                .expectNext("ArchanaKowreesh", "VasunthraJaisri")
                .verifyComplete();
    }

    @Test
    void usersFluxZipTuple() {
        var usersFluxZipTuple = fluxAndMonoServices.usersFluxZipTuple();
        StepVerifier.create(usersFluxZipTuple)
                .expectNext("ArchanaKowreeshHarish", "VasunthraJaisriRuthsan")
                .verifyComplete();
    }

    @Test
    void phoneNoMonoZipWith() {
        var phoneNoMonoZipWith = fluxAndMonoServices.phoneNoMonoZipWith();
        StepVerifier.create(phoneNoMonoZipWith)
                .expectNext("ArchanaVasunthra")
                .verifyComplete();
    }

    @Test
    void usersFluxDoOn() {
        var usersFluxDoOn = fluxAndMonoServices.usersFluxDoOn();
        StepVerifier.create(usersFluxDoOn)
                .expectNext("Vasunthra", "Kowreesh")
                .verifyComplete();
    }

    @Test
    void usersFluxOnErrorReturn() {
        var usersFluxOnErrorReturn = fluxAndMonoServices.usersFluxOnErrorReturn();
        StepVerifier.create(usersFluxOnErrorReturn)
                .expectNext("Archana", "Vasunthra", "Username")
                .verifyComplete();
    }

    @Test
    void usersFluxOnErrorContinue() {
        var usersFluxOnErrorContinue = fluxAndMonoServices.usersFluxOnErrorContinue();
        StepVerifier.create(usersFluxOnErrorContinue)
                .expectNext("VASUNTHRA")
                .verifyComplete();
    }

    @Test
    void usersFluxOnErrorMap() {
        var usersFluxOnErrorMap = fluxAndMonoServices.usersFluxOnErrorMap();
        StepVerifier.create(usersFluxOnErrorMap)
                .expectNext("VASUNTHRA")
                .expectError(IllegalStateException.class)
                .verify();
    }


    @Test
    void usersFluxOnError() {
        var usersFluxOnError = fluxAndMonoServices.usersFluxOnError();
        StepVerifier.create(usersFluxOnError)
                .expectNext("VASUNTHRA")
                .expectError(RuntimeException.class)
                .verify();
    }
}