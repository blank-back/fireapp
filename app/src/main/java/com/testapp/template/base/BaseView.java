package com.testapp.template.base;

import android.view.View;

/**
 * Created by testapp on 2018/3/22.
 */

public interface BaseView<T> {

    void setPresenter(T presenter);

    void initViews(View view);
}
