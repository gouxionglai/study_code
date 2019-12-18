package com.gxl.framework.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 模型基础类
 */
public class SearchBaseObject implements Serializable {

    private static final long serialVersionUID = -6217441067788888076L;

    /**
     * 排序
     */
    private String orderBy;

    /**
     * 偏移量
     */
    private int offset = 0;

    /**
     * 显示行数
     */
    private int row_count;

    /***
     * 关键字条件
     */
    private String keyword_condition;

    /**
     * 开始日期条件
     */
    private Date start_date_condition;

    /**
     * 结束日期条件
     */
    private Date end_date_condition;

    /**
     * 附件ids
     */
    private String file_ids;

    /**
     * @return the orderBy
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * @param orderBy the orderBy to set
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * @return the offset
     */
    public int getOffset() {
        return offset;
    }

    /**
     * @param offset the offset to set
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * @return the row_count
     */
    public int getRow_count() {
        return row_count;
    }

    /**
     * @param row_count the row_count to set
     */
    public void setRow_count(int row_count) {
        this.row_count = row_count;
    }

    /**
     * @return the keyword_condition
     */
    public String getKeyword_condition() {
        return keyword_condition;
    }

    /**
     * @param keyword_condition the keyword_condition to set
     */
    public void setKeyword_condition(String keyword_condition) {
        this.keyword_condition = keyword_condition;
    }

    /**
     * @return the start_date_condition
     */
    public Date getStart_date_condition() {
        return start_date_condition;
    }

    /**
     * @param start_date_condition the start_date_condition to set
     */
    public void setStart_date_condition(Date start_date_condition) {
        this.start_date_condition = start_date_condition;
    }

    /**
     * @return the end_date_condition
     */
    public Date getEnd_date_condition() {
        return end_date_condition;
    }

    /**
     * @param end_date_condition the end_date_condition to set
     */
    public void setEnd_date_condition(Date end_date_condition) {
        this.end_date_condition = end_date_condition;
    }

    public String getFile_ids() {
        return file_ids;
    }

    public void setFile_ids(String file_ids) {
        this.file_ids = file_ids;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SearchBaseObject [orderBy=");
        builder.append(orderBy);
        builder.append(", offset=");
        builder.append(offset);
        builder.append(", row_count=");
        builder.append(row_count);
        builder.append(", keyword_condition=");
        builder.append(keyword_condition);
        builder.append(", start_date_condition=");
        builder.append(start_date_condition);
        builder.append(", end_date_condition=");
        builder.append(end_date_condition);
        builder.append(", file_ids=");
        builder.append(file_ids);
        builder.append(", toString()=");
        builder.append(super.toString());
        builder.append("]");
        return builder.toString();
    }

}
