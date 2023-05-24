package com.gr1.spring.payload;import lombok.AllArgsConstructor;
        import lombok.Data;
        import lombok.NoArgsConstructor;

        import javax.validation.constraints.NotBlank;
        import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoRequest {
    @NotBlank
    @Size(max = 30, message = "Title cannot exceed 50 characters")
    private String title;

    @NotBlank
    @Size(max = 200, message = "Description cannot exceed 200 characters")
    private String description;


    private Boolean is_done;
    private Long userId;

}
