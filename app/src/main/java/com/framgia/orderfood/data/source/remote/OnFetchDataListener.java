package com.framgia.orderfood.data.source.remote;

import java.util.List;

public interface OnFetchDataListener <T> {
    void onSuccess (List<T> data);
    void onErorr (Exception e);
}
