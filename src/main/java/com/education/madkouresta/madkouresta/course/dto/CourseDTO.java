package com.education.madkouresta.madkouresta.course.dto;

import com.education.madkouresta.madkouresta.course.model.Course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {
    private Integer id;
    private String name;
    private String startAt;
    private String endAt;

    public CourseDTO CourseToDTO(Course course) {
        return CourseDTO.builder()
                .id(course.getId())
                .name(course.getName())
                .startAt(course.getStartAt())
                .endAt(course.getEndAt())
                .build();

    }

    public Course DTOToCourse(CourseDTO courseDTO) {
        return Course.builder()
                .id(courseDTO.getId())
                .name(courseDTO.getName())
                .startAt(courseDTO.getStartAt())
                .endAt(courseDTO.getEndAt())
                .build();

    }

}
