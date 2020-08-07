package by.mmkle.bean.Amo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contact {
    private Integer id;
    private String name;
    private String firstName;
    private String lastName;
    private Integer responsibleUserId;
    private Integer groupId;
    private Integer createdBy;
    private Integer updatedBy;
    private Integer createdAt;
    private Integer updatedAt;
    private Object closestTaskAt;
    private List<CustomFieldsValue> customFieldsValues = null;
    private Integer accountId;
    private Object links;
    private Object embedded;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}