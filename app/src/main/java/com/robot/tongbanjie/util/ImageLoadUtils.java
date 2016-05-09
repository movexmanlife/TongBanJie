package com.robot.tongbanjie.util;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * 图片加载工具类：在第三方图片加载上封装一层，便于解耦替换
 */
public final class ImageLoadUtils {
    private ImageLoadUtils() {

    }

    /**
     * @param context
     * @param resId 加载drawable，图片对应的资源Id
     * @param imageView
     */
    public static void load(Context context, int resId, ImageView imageView) {
        Picasso.with(context).load(resId).into(imageView);
    }

    /**
     * @param context
     * @param resId 加载drawable，图片对应的资源Id
     * @param imageView
     * @param size
     */
    public static void load(Context context, int resId, ImageView imageView, Size size) {
        Picasso.with(context).load(resId).resize(size.width, size.height).centerCrop().into(imageView);
    }

    /**
     * @param context
     * @param resId 加载drawable，图片对应的资源Id
     * @param imageView
     * @param placeHolderResId 站位图片资源Id
     * @param errorResId 错误图片资源Id
     */
    public static void load(Context context, int resId, ImageView imageView, int placeHolderResId, int errorResId) {
        Picasso.with(context).load(resId).placeholder(placeHolderResId).error(errorResId).into(imageView);
    }

    /**
     * @param context
     * @param uri
     * @param imageView
     */
    public static void load(Context context, Uri uri, ImageView imageView) {
        Picasso.with(context).load(uri).into(imageView);
    }

    /**
     * @param context
     * @param uri
     * @param imageView
     * @param placeHolderResId 站位图片资源Id
     * @param errorResId 错误图片资源Id
     */
    public static void load(Context context, Uri uri, ImageView imageView, int placeHolderResId, int errorResId) {
        Picasso.with(context).load(uri).placeholder(placeHolderResId).error(errorResId).into(imageView);
    }

    /**
     * 加载asset目录下的图片文件
     * @param context
     * @param imageFileName
     * @param imageView
     */
    public static void loadAsset(Context context, String imageFileName, ImageView imageView) {
        Picasso.with(context).load("file:///android_asset/" + imageFileName).into(imageView);
    }

    /**
     * 加载asset目录下的图片文件
     * @param context
     * @param imageFileName
     * @param imageView
     * @param placeHolderResId 站位图片资源Id
     * @param errorResId 错误图片资源Id
     */
    public static void loadAsset(Context context, String imageFileName, ImageView imageView, int placeHolderResId, int errorResId) {
        Picasso.with(context).load("file:///android_asset/" + imageFileName).placeholder(placeHolderResId).error(errorResId).into(imageView);
    }

    /**
     * @param context
     * @param url
     * @param imageView
     */
    public static void load(Context context, String url, ImageView imageView) {
        Picasso.with(context).load(url).into(imageView);
    }

    /**
     * @param context
     * @param url
     * @param imageView
     * @param size
     */
    public static void load(Context context, String url, ImageView imageView, Size size) {
        Picasso.with(context).load(url).resize(size.width, size.height).into(imageView);
    }

    /**
     * @param context
     * @param url
     * @param imageView
     * @param placeHolderResId 站位图片资源Id
     * @param errorResId 错误图片资源Id
     */
    public static void load(Context context, String url, ImageView imageView, int placeHolderResId, int errorResId) {
        Picasso.with(context).load(url).placeholder(placeHolderResId).error(errorResId).into(imageView);
    }

    /**
     * 加载圆形图片
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadCircle(Context context, String url, ImageView imageView) {
        Picasso.with(context).load(url).transform(new CircleTransform()).into(imageView);
    }

    /**
     * 加载圆形图片
     * @param context
     * @param url
     * @param imageView
     * @param placeHolderResId 站位图片资源Id
     * @param errorResId 错误图片资源Id
     */
    public static void loadCircle(Context context, String url, ImageView imageView, int placeHolderResId, int errorResId) {
        Picasso.with(context).load(url).placeholder(placeHolderResId).error(errorResId).transform(new CircleTransform()).into(imageView);
    }

    /**
     * 加载圆角图片
     * @param context
     * @param url
     * @param radius 圆角半径
     * @param margin
     * @param imageView
     */
    public static void loadRounded(Context context, String url, int radius, int margin, ImageView imageView) {
        Picasso.with(context).load(url).transform(new RoundedCornersTransformation(radius, margin)).into(imageView);
    }

    /**
     * 加载圆角图片
     * @param context
     * @param url
     * @param radius 圆角半径
     * @param margin
     * @param imageView
     * @param placeHolderResId 站位图片资源Id
     * @param errorResId 错误图片资源Id
     */
    public static void loadRounded(Context context, String url, int placeHolderResId, int errorResId, ImageView imageView,
                                   int radius, int margin) {
        Picasso.with(context).load(url).placeholder(placeHolderResId).error(errorResId).
                transform(new RoundedCornersTransformation(radius, margin)).into(imageView);
    }

    /**
     * @param context
     * @param url
     * @param imageView
     * @param callback 回调接口
     */
    public static void loadWithCallback(Context context, String url, ImageView imageView, final Callback callback) {
        Picasso.with(context).load(url).into(imageView, new com.squareup.picasso.Callback(){
            @Override
            public void onSuccess() {
                if (callback != null) {
                    callback.onSuccess();
                }
            }

            @Override
            public void onError() {
                if (callback != null) {
                    callback.onError();
                }
            }
        });
    }

    /**
     * @param context
     * @param url
     * @param imageView
     * @param callback 回调接口
     */
    public static void loadWithCallback(Context context, String url, ImageView imageView, int placeHolderResId, int errorResId, final Callback callback) {
        Picasso.with(context).load(url).placeholder(placeHolderResId).error(errorResId).into(imageView, new com.squareup.picasso.Callback(){
            @Override
            public void onSuccess() {
                if (callback != null) {
                    callback.onSuccess();
                }
            }

            @Override
            public void onError() {
                if (callback != null) {
                    callback.onError();
                }
            }
        });
    }

    public interface Callback {
        void onSuccess();
        void onProgress(int current, int total);
        void onError();
    }

    public static class Size {
        public int width;
        public int height;
    }
}
