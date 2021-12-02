package ru.pinguin.librarybackend.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.NativeWebRequest
import org.zalando.problem.Problem
import org.zalando.problem.Status
import org.zalando.problem.spring.web.advice.ProblemHandling


@RestControllerAdvice
class WebExceptionHandler : IWebExceptionHandler, ProblemHandling {

    @ExceptionHandler(NotFoundException::class)
    protected fun handleException(ex: NotFoundException, request: NativeWebRequest): ResponseEntity<Problem?>? {
        return create(Status.NOT_FOUND, "Not found", ex.message ?: "", request)
    }
}