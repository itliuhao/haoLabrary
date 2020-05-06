package com.wander.baselibrary.ioc;

import android.app.Activity;
import android.view.View;

/**
 * @Description: view的findviewbyid辅助类
 * @Author: liuhao
 * @CreateDate: 2020-04-28 22:12
 */

public class ViewFinder {
    private Activity activity;
    private View view;

    public ViewFinder(Activity activity) {
        this.activity = activity;
    }

    public ViewFinder(View view) {
        this.view = view;
    }

    public View findViewById(int viewId) {
        return activity != null ? activity.findViewById(viewId) : view.findViewById(viewId);
    }
}
