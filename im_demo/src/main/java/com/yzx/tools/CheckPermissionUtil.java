package com.yzx.tools;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class CheckPermissionUtil {

    /**
     * 检查是否拥有指定的权限
     */
    public static boolean checkPermission(Activity activity, String permission) {
        return checkPermissionsTool(activity, new String[]{permission});
    }

    public static boolean checkPermissions(Activity activity, String[] permissions) {
        return checkPermissionsTool(activity, permissions);
    }

    private static boolean checkPermissionsTool(Activity activity, String[] permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : permissions) {
                if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                    // 只要有一个权限没有被授予, 则直接返回 false
                    //Log.e("err","权限"+permission+"没有授权");
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 权限请求方法
     */
    public static void requestPermission(Activity activity, String permission) {
        requestPermissionsTool(activity, new String[]{permission});
    }

    public static void requestPermissions(Activity activity, String[] permissions) {
        requestPermissionsTool(activity, permissions);
    }

    private static void requestPermissionsTool(Activity activity, String[] permissions) {
        if (!checkPermissions(activity, permissions)) {
            ActivityCompat.requestPermissions(activity, permissions, 0xaa);
        }
    }


    /**
     * 判断权限
     *
     * @param context
     * @return
     */
    public static boolean checkCallingOrSelfPermission(Context context, String permission) {
        return checkCallingOrSelfPermissionsUtil(context, new String[]{permission});
    }

    public static boolean checkCallingOrSelfPermission(Context context, String[] permissions) {
        return checkCallingOrSelfPermissionsUtil(context, permissions);
    }

    private static boolean checkCallingOrSelfPermissionsUtil(Context context, String[] permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : permissions) {
                if (context.checkCallingOrSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    // 只要有一个权限没有被授予, 则直接返回 false
                    //Log.e("err","权限"+permission+"没有授权");
                    return false;
                }
            }
        }
        return true;
    }

}
