package com.gxl.framework.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Demo implements Serializable {
    private Long id;

    private String name;

    private Byte type;

    private String description;

    private BigDecimal price_pre;

    private BigDecimal price_real;

    private Date create_time;

    private Date update_time;

    private String remark;

    private static final long serialVersionUID = 1L;

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
        this.name = name == null ? null : name.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public BigDecimal getPrice_pre() {
        return price_pre;
    }

    public void setPrice_pre(BigDecimal price_pre) {
        this.price_pre = price_pre;
    }

    public BigDecimal getPrice_real() {
        return price_real;
    }

    public void setPrice_real(BigDecimal price_real) {
        this.price_real = price_real;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", type=").append(type);
        sb.append(", description=").append(description);
        sb.append(", price_pre=").append(price_pre);
        sb.append(", price_real=").append(price_real);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_time=").append(update_time);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}