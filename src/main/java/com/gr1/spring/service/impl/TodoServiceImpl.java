package com.gr1.spring.service.impl;


import com.gr1.spring.dto.UserDTO;
import com.gr1.spring.entity.Todo;
import com.gr1.spring.exception.CustomValidationException;
import com.gr1.spring.mapper.dto.TodoDTOMapper;
import com.gr1.spring.payload.request.todo.TodoFilterRequest;
import com.gr1.spring.payload.request.todo.TodoStatusRequest;
import com.gr1.spring.repository.TodoRepository;

import com.gr1.spring.security.service.UserService;
import com.gr1.spring.service.TodoService;
import com.gr1.spring.service.base.impl.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class TodoServiceImpl  extends BaseServiceImpl<Todo> implements TodoService{

    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private TodoDTOMapper dtoMapper;
    @Autowired
    private UserService userService;


    public TodoServiceImpl(TodoRepository todoRepository) {
        super(todoRepository);
    }

    @Override
    @Transactional
    public Todo updateById(Long id, Todo newEntity) {
        UserDTO user = userService.getUserDetail();
        Long userId = user.getId();
        Optional<Todo> todo = findByUserIdAndId(userId,id);
        if(!todo.isPresent()){
            String errorMessage = "Todo not found with ID: " + id;
            throw new CustomValidationException(errorMessage);
        }
        Todo oldEntity = this.findById(id);
        newEntity.setId(oldEntity.getId());
        return super.updateById(id, newEntity);
    }

    @Override
    public List<Todo> findByUserId(Long userId){

        return todoRepository.findByUserId(userId);
    }
    @Override
    public Optional<Todo> findByUserIdAndId(Long userId, Long id){
        return todoRepository.findByUserIdAndId(userId,id);
    }

    @Override
    public List<Todo> filterByStatusAndTitle(TodoFilterRequest filterRequest){
        UserDTO user = userService.getUserDetail();
        Long userId = user.getId();
        String title = filterRequest.getTitle().isPresent() ? filterRequest.getTitle().get() :null;
        if(filterRequest.getStatus().isPresent()&&filterRequest.getStatus().get().contains("all")){
            return  todoRepository.findByUser_IdAndTitleContainingIgnoreCase(userId,title);
        }
        Boolean status;
        status = filterRequest.getStatus().isPresent() ? Boolean.parseBoolean(filterRequest.getStatus().get()):null;
        return  todoRepository.findByUser_IdAndTitleContainingIgnoreCaseAndIsDone(userId,title,status);
    }

    @Transactional
    public Integer updateStatus(TodoStatusRequest status, String todoId){
        UserDTO user = userService.getUserDetail();
        Long userId = user.getId();
        Long id = Long.parseLong(todoId);
        Optional<Todo> todo = todoRepository.findByUserIdAndId(userId,id);
        if(!todo.isPresent()){
            throw new CustomValidationException("No todo found");
        }
        return todoRepository.updateByUser_IdAndStatus(id,status.getIs_done());
    }
    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }
    public Map<String, Object> getAllTodo(Long userId, String title, int page, int size, String[] sort){
        try {
            List<Sort.Order> orders = new ArrayList<Sort.Order>();
 
            if (sort[0].contains(",")) {
                // will sort more than 2 fields
                // sortOrder="field, direction"
                for (String sortOrder : sort) {
                    String[] _sort = sortOrder.split(",");
                    orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                // sort=[field, direction]
                orders.add(new Order(getSortDirection(sort[1]), sort[0]));
            }

            List<Todo> tutorials = new ArrayList<Todo>();
            Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

            Page<Todo> pageTuts;
            if (title == null)
                pageTuts = todoRepository.findAll(pagingSort);
            else
                pageTuts = todoRepository.findByTitleContainingIgnoreCase(title, pagingSort);

            tutorials = pageTuts.getContent();

            Map<String, Object> response = new HashMap<>();
            response.put("tutorials", tutorials);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return response;
        } catch (Exception e) {
            throw new CustomValidationException("Something went wrong");
        }
    }
}
