
package by.mmkle.bean;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Integer responsibleId;
    private Integer advSourceId;
    private Integer createSourceId;
    private Integer clientStateId;
    private List<Object> filials = null;
    private List<Object> attributes = null;
    private List<Object> joins = null;
}
