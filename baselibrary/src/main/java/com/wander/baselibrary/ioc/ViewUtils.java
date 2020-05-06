package com.wander.baselibrary.ioc;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Description: view绑定工具类
 * @Author: liuhao
 * @CreateDate: 2020-04-28 22:08
 */

public class ViewUtils {

    /**
     * activity中使用
     *
     * @param activity
     */
    public static void inject(Activity activity) {
        inject(new ViewFinder(activity), activity);
    }

    /**
     * 自定义view中使用
     *
     * @param view
     */
    public static void inject(View view) {
        inject(new ViewFinder(view), view);
    }

    /**
     * fragment中使用
     *
     * @param view
     * @param object
     */
    public static void inject(View view, Object object) {
        inject(new ViewFinder(view), object);
    }

    // 兼容上面三种方法  Object --> 反射需要执行的类
    private static void inject(ViewFinder viewFinder, Object object) {
        //
        injectFiled(viewFinder, object);

        injectEvent(viewFinder, object);
    }


    /**
     * 注入属性
     */
    private static void injectFiled(ViewFinder viewFinder, Object object) {
        // 1、获取类里面所有的属性

        Class<?> aClass = object.getClass();
        Field[] fields = aClass.getDeclaredFields(); // 获取所有的属性  包括私有和公有的

        // 2、获取ViewBind里面的value
        for (Field field : fields) {
            ViewBind viewBind = field.getAnnotation(ViewBind.class);

            if (viewBind != null) {
                // 获取到注解里面的id值
                int[] viewIds = viewBind.value();

                for (int viewId : viewIds) {
                    // 3、findviewbyid获取view
                    View view = viewFinder.findViewById(viewId);

                    if (view != null) {
                        // 能够注入所有的修饰符
                        field.setAccessible(true);

                        // 4、注入找到的view
                        try {
                            field.set(object, view);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }

    }

    /**
     * 注入事件
     */
    private static void injectEvent(ViewFinder viewFinder, Object object) {
        // 1、获取类里面所有的方法
        Class<?> aClass = object.getClass();
        Method[] methods = aClass.getDeclaredMethods();

        // 2、获取OnClick里面的value
        for (Method method : methods) {
            OnClick click = method.getAnnotation(OnClick.class);
            if (click != null) {
                // 获取到注解里面的id值
                int[] viewIds = click.value();

                for (int viewId : viewIds) {
                    // 3、findviewbyid获取view
                    View view = viewFinder.findViewById(viewId);

                    OnClickCheck onClikCheck = method.getAnnotation(OnClickCheck.class);
                    if (onClikCheck != null) {
                        String msg = onClikCheck.msg();
                        int type = onClikCheck.type();
                        long time = onClikCheck.time();
                        // 4、设置点击事件
                        view.setOnClickListener(new DecleardOnClickListener(method, object, type, msg, time));
                    } else {
                        // 4、设置点击事件
                        view.setOnClickListener(new DecleardOnClickListener(method, object));
                    }
                }
            }


        }
    }

    private static class DecleardOnClickListener implements View.OnClickListener {

        private Method method;
        private Object object;
        private int onClickCheck = -1;
        private String msg;
        private long minClicktime;

        public DecleardOnClickListener(Method method, Object object) {
            this.method = method;
            this.object = object;
        }

        public DecleardOnClickListener(Method method, Object object, int type, String msg, long time) {
            this.method = method;
            this.object = object;
            this.onClickCheck = type;
            this.msg = msg;
            this.minClicktime = time;
        }

        @Override
        public void onClick(View view) {
            if (onClickCheck == OnClickCheckUtils.CHECK_CLICKFAST) {
                if (OnClickCheckUtils.isFastClick(minClicktime)) {
                    return;
                }
            }

            if (onClickCheck == OnClickCheckUtils.CHECK_NET) {
                if (!OnClickCheckUtils.netWorkAvailable(view.getContext())) {
                    Toast.makeText(view.getContext(), msg, Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            if (onClickCheck == OnClickCheckUtils.CHECK_ALL) {
                if (OnClickCheckUtils.isFastClick(minClicktime) || !OnClickCheckUtils.netWorkAvailable(view.getContext())) {
                    Toast.makeText(view.getContext(), msg, Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            try {
                method.setAccessible(true);
                // 5、注入方法
                method.invoke(object, view);
            } catch (Exception e) {
                e.printStackTrace();
                // 防止按钮点击时没有传入view报错
                try {
                    method.invoke(object, null);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

        }
    }

}
