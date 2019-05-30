package com.yb.common.center.basic.method;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yb.common.center.Pager;

import java.util.Date;

/**
 * 基础实体类
 *
 * @author yebing
 */
public class BasicDTO {
    /**
     * 代表删除
     */
    public static final int DEL = 0;

    /**
     * 主键id
     */
    protected Long id;

    /**
     * 是否已逻辑删除，1：是，0：否
     */
    protected Integer delete;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Date createTime;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    protected Date updateTime;

    private Pager pager;

    public static int getDEL() {
        return DEL;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDelete() {
        return delete;
    }

    public void setDelete(Integer delete) {
        this.delete = delete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }
}
