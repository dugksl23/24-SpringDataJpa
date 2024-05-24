package study.springdatajpa.dto;


import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class ApiResultResponse<T> {
    private Long count;
    private T data;
}
