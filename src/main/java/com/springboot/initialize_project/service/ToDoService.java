package com.springboot.initialize_project.service;

import com.springboot.initialize_project.data.dto.init.RequestToDoDto;
import com.springboot.initialize_project.data.dto.init.ResponseListToDoDto;
import com.springboot.initialize_project.data.dto.init.ToDoDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ToDoService {
    void createToDo(RequestToDoDto requestToDoDto,
                    HttpServletRequest servletRequest,
                    HttpServletResponse servletResponse);
    void deleteToDo(Long id,
                    HttpServletRequest servletRequest,
                    HttpServletResponse servletResponse);

    ToDoDto getToDo(Long id,
                    HttpServletRequest servletRequest,
                    HttpServletResponse servletResponse);

    void updateToDo(ToDoDto toDoDto,
                    HttpServletRequest servletRequest,
                    HttpServletResponse servletResponse);

    ResponseListToDoDto getToDoList(String state, int page, int size,
                                    HttpServletRequest servletRequest,
                                    HttpServletResponse servletResponse);
}
