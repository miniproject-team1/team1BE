package com.miniproject.be.domain.calendar.controller;

import com.miniproject.be.domain.calendar.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

}