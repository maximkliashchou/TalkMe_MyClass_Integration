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
public class CustomFieldsValue {
    private Integer fieldId;
    private String fieldName;
    private String fieldCode;
    private String fieldType;
    private List<Value> values = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
