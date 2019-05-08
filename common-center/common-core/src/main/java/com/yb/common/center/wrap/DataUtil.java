package com.yb.common.center.wrap;

import com.yb.common.center.Pager;

import java.util.ArrayList;
import java.util.List;

public class  DataUtil<T> {
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
