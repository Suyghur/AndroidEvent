package event.imy.application;

import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 4399-蒋明伟 on 2017/8/3.
 */

public class EventHelper {
    private volatile static EventHelper instance;
    private HashMap<Integer, Object> classHashMap = new HashMap<>();
    private static final String TAG = "EventHelper";

    private EventHelper() {
        // 私有构造方法
    }

    public static EventHelper getInstance() {
        if (instance == null) {
            synchronized (EventHelper.class) {
                if (instance == null) {
                    instance = new EventHelper();
                }
            }
        }
        return instance;
    }

    public void register(Object object) {
        Class<?> aClass = object.getClass();
        //加入前先校验是否有注解了方法
        checkHasMethod(aClass);
        classHashMap.put(object.hashCode(), object);

    }


    /**
     * 校验类方法名
     *
     * @param aClass
     */
    private void checkHasMethod(Class<?> aClass) {
        Method[] methods = aClass.getMethods();
        boolean has_on_event = false;
        int j = 0;
        for (int i = 0; i < methods.length; i++) {
            String name = methods[i].getName();
            Log.i(TAG, "checkHasMethod name:" + name);
            if (name.equals("onEvent")) {
                has_on_event = true;
                j = i;
                break;
            } else {
                has_on_event = false;
            }
        }
        if (!has_on_event) {
            throw new EventException("注册的类里没有onEvent方法或非公共参数");
        }
        Register annotation = methods[j].getAnnotation(Register.class); //检测注解
        if (annotation == null) {
            throw new EventException("注册的onEvent方法未使用Register注解");
        }

    }

    public void unregister(Object object) {
        classHashMap.remove(object.hashCode());
    }

    public synchronized void postEvent(Object event) {
        //向所有注册的类发送事件
        for (Map.Entry<Integer, Object> entry : classHashMap.entrySet()) {
            Object value = entry.getValue();
            Class aClass = value.getClass();
            Log.i(TAG, "value:" + value.hashCode() + value.getClass().getName());
            try {
                //
                Method[] methods = aClass.getMethods();
                for (int i = 0; i < methods.length; i++) {
                    Method method = methods[i];
                    if (method.getName().equals("onEvent")) {
                        Log.i(TAG, "执行---:" + method.getName() + "参数:" + event);
                        method.invoke(value, event);
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
