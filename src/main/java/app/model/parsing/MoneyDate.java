package app.model.parsing;

public class MoneyDate {
    private String code;
    private String nameMoney;
    private String value;
    private String codeMoney;
    private String date;

    public MoneyDate(String code, String nameMoney, String value, String codeMoney, String date){
        this.code = code;
        this.nameMoney = nameMoney;
        this.value = value;
        this.codeMoney = codeMoney;
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public String getNameMoney() {
        return nameMoney;
    }

    public String getValue() {
        return value;
    }

    public String getCodeMoney() {
        return codeMoney;
    }

    public String getDate() {
        return date;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setNameMoney(String nameMoney) {
        this.nameMoney = nameMoney;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setCodeMoney(String codeMoney) {
        this.codeMoney = codeMoney;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
