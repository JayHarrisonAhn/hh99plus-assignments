package com.example.hhplus_arch_jvm.application.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@SuperBuilder
@Getter @Setter
public class CourseInfo {
    Long id;
    String name;
    LocalDate date;
    String description;
}
