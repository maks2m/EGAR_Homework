package ru.elkin.egar;

/*
 * класс доходов
 */

public class Income {

    private Long id;
    // наименование получения дохода
    private String name;
    // план получения денег
    private Long planMoney;
    // факт получения денег
    private Long factMoney;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPlanMoney() {
        return planMoney;
    }

    public void setPlanMoney(Long planMoney) {
        this.planMoney = planMoney;
    }

    public Long getFactMoney() {
        return factMoney;
    }

    public void setFactMoney(Long factMoney) {
        this.factMoney = factMoney;
    }

    @Override
    public String toString() {
        return "Income{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", planMoney=" + planMoney +
                ", factMoney=" + factMoney +
                '}';
    }
}
