package by.mmkle.bean.Amo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Value {
    private String value;
    private Integer enumId;
    private String enumCode;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}