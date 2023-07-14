package com.gr1.spring.payload.request.todo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoFilterRequest {
    private Optional<String> title;
    private Optional<String> status;
}
