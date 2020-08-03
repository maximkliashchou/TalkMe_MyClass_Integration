package by.mmkle.bean;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@EqualsAndHashCode
@Entity
public class Client implements Serializable {
    @Id
    private Long idChat;
    private LocalDate startSubscribe;
    private LocalDate endSubscribe;
    private String typeSubscribe;

    //todo create bean type
    //types - text, free, paid
}
