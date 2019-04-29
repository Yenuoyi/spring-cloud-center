package com.yb.user.center.export;

import com.yb.user.center.common.basicmethod.BasicDTO;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yebing
 */
public class UserDTO extends BasicDTO implements Serializable {
    private static final long serialVersionUID = -4867781418448777818L;
    private String name;
    private Date fromDate;
    private Date toDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
