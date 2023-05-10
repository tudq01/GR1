package com.gr1.spring.mapper.request;

import com.gr1.spring.entity.Todo;
import com.gr1.spring.entity.User;
import com.gr1.spring.payload.TodoRequest;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TodoRequestMapper {
    @Autowired
    private ModelMapper mapper;

    // fix todoRequest to User convert to Todo class
    public Todo toTodo(TodoRequest todoRequest){
        return mapper.map(todoRequest,Todo.class);
    }


}
