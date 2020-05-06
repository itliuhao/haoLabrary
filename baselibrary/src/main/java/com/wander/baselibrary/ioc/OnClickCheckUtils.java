package com.wander.baselibrary.ioc;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @Description: view事件判断工具类
 * @Author: liuhao
 * @CreateDate: 2020-04-29 00:19
 */

public class OnClickCheckUtils {

    public static final int CHECK_NET = 0; // 检测网络
    public static final int CHECK_CLICKFAST = 1; // 检测防止点击过快
    public static final int CHECK_ALL = 2; // 全部检测

    public static final long CHECK_MIN_TIME = 500;// 最小点击间隔

    public static final String NETWORK_FAIL_MSG = "请检查网络连接！";// 网络连接失败提示语

    private static long lastClickTime;

    public static boolean isFastClick(long minCheckTime) {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) < minCheckTime) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }

    public static boolean netWorkAvailable(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }


}
