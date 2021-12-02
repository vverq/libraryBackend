package ru.pinguin.librarybackend.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.context.request.NativeWebRequest
import org.zalando.problem.Problem
import org.zalando.problem.Status
import org.zalando.problem.ThrowableProblem
import org.zalando.problem.spring.web.advice.AdviceTrait


interface IWebExceptionHandler : AdviceTrait {


    fun create(status: Status, t: Throwable, title: String, req: NativeWebRequest): ResponseEntity<Problem?>? {
        return create(status, t, title, t.message ?: "", req)
    }

    fun create(
        status: Status, t: Throwable, title: String, detail: String,
        req: NativeWebRequest
    ): ResponseEntity<Problem?>? {
        val problem: ThrowableProblem = Problem.builder()
            .withStatus(status)
            .withTitle(title)
            .withDetail(detail)
            .build()
        return create(problem, req)
    }

    fun create(status: Status, title: String, detail: String, req: NativeWebRequest): ResponseEntity<Problem?>? {
        val problem: ThrowableProblem = Problem.builder()
            .withStatus(status)
            .withTitle(title)
            .withDetail(detail)
            .build()
        return create(problem, req)
    }
}