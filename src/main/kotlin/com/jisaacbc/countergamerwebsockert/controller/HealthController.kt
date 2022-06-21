package com.jisaacbc.countergamerwebsockert.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Service
@RestController
@RequestMapping(
    path = ["/"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class HealthController {

    @GetMapping
    fun health() = ResponseEntity<Any>(HttpStatus.OK)
}