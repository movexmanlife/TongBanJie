package com.robot.tongbanjie.util.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * SharedPreferences操作接口
 */
public interface SharedPreferencesImpl {
    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param context
     * @param key
     * @param object
     */
    void put(Context context, String key, Object object);
    
    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */
    Object get(Context context, String key, Object defaultObject);
    
    /**
     * 移除某个key值已经对应的值
     * @param context
     * @param key
     */
    void remove(Context context, String key);
    
    /**
     * 清除所有数据
     * @param context
     */
    void clear(Context context);
    
    /**
     * 查询某个key是否已经存在
     * @param context
     * @param key
     * @return
     */
    boolean contains(Context context, String key);
    
    /**
     * 返回所有的键值对
     *
     * @param context
     * @return
     */
    Map<String, ?> getAll(Context context);
}
