package com.gr1.spring.payload.request.todo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoStatusRequest {
    private Boolean is_done;
}
