package com.star.wanandroid.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.star.wanandroid.R;
import com.star.wanandroid.app.Constants;
import com.star.wanandroid.app.WanAndroidApp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class CommonUtils {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(float dpValue) {
        final float scale = WanAndroidApp.getInstance().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static void showMessage(Activity activity, String msg) {
        LogHelper.e("showMessage ：" + msg);
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showSnackMessage(Activity activity, String msg) {
        LogHelper.e("showSnackMessage ：" + msg);
        Snackbar snackbar = Snackbar.make(activity.getWindow().getDecorView(), msg, Snackbar.LENGTH_SHORT);
        View view = snackbar.getView();
        ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(ContextCompat.getColor(activity, R.color.white));
        snackbar.show();
    }

    public static boolean isEquals(Object a, Object b) {
        return (a == null) ? (b == null) : a.equals(b);
    }

    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) WanAndroidApp.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    public static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    public static int randomColor() {
        Random random = new Random();

        int red =random.nextInt(150);
        int green =random.nextInt(150);
        int blue =random.nextInt(150);

        return Color.rgb(red,green, blue);
    }

    public static int randomTagColor() {
        int randomNum = new Random().nextInt();
        int position = randomNum % Constants.TAB_COLORS.length;
        if (position < 0) {
            position = -position;
        }
        return Constants.TAB_COLORS[position];
    }

    /**
     * 泛型转换工具方法 eg:object ==> map<String, String>
     *
     * @param object Object
     * @param <T> 转换得到的泛型对象
     * @return T
     */
    @SuppressWarnings("unchecked")
    public static <T> T cast(Object object) {
        return (T) object;
    }
}
