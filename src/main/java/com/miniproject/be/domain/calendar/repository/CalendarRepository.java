package com.miniproject.be.domain.calendar.repository;

import com.miniproject.be.domain.calendar.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CalendarRepository extends JpaRepository<Calendar, Long> {

}