package com.gxl.framework.model;





import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author weilai
 * @description
 * @since 2020/1/10
 */
public class User implements Serializable {
    public interface doInsert{}
    private Long id;
    private String name;
    @NotNull(message="性别不能为空",groups = {User.doInsert.class})
    private Byte sex;
    @Min(value = 18, message = "不能小于18岁",groups = {User.doInsert.class})
    private Integer age;
    private Date birth;
    private Double money;


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

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", sex=").append(sex);
        sb.append(", age=").append(age);
        sb.append(", birth=").append(birth);
        sb.append(", money=").append(money);
        sb.append('}');
        return sb.toString();
    }
}
