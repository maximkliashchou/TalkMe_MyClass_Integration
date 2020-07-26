package by.mmkle.bean;

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
public class Message {
    private int id;
    private String operator;
    private String whoSend;
    private String text;
    private String dateTime;
    private String messageType;
    private Integer dialogId;
    private String status;
    private Boolean isVisibleForClient;
    private Object page;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
}
