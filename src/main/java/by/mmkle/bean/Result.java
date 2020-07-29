package by.mmkle.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {
        private String name;
        private String phone;
        private String email;
        private LocalDateTime time;
        private String message;
}
