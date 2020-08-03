package by.mmkle.bean;

public enum TypeOfSubscribe {
    TEST("Test time"),
    FREE("Free time"),
    PAID("Paid time");

    String description;
    public String getDescription() {
        return description;
    }

    TypeOfSubscribe(String description) {
        this.description = description;
    }
}
