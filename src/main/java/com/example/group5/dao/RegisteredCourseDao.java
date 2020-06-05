package com.example.group5.dao;

import com.example.group5.model.RegisteredCourses;
import com.example.group5.service.RegisteredCourseService;

import java.util.List;

public interface RegisteredCourseDao {

    List<RegisteredCourses> getRegisteredCourse(String bannerID);

}
