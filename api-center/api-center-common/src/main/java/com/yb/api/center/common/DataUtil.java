package com.yb.api.center.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 工具类
 *
 * @author yebing
 */
public class DataUtil<T> {
    List<T> list = new ArrayList<>();
    Pager pager = new Pager();

    public List<?> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Pager getPager() {
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }
}
