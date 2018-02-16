package com.homework.common.collection;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.homework.common.exception.SystemErrorRuntimeException;
import com.homework.common.reflection.ReflectionUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PropertyExtractUtils {

    public static <T> void setByPropertyValue(Object object, String propertyName, T value, Class<T> clazz) {
        if (object == null) {
            return;
        }


        Method mth = ReflectionUtils.getSetPropertyMethod(object.getClass(), propertyName, clazz);

        try {
            mth.invoke(object, value);
        } catch (Exception e) {
            throw new SystemErrorRuntimeException(e);
        }
    }

    /**
     * 获取一个对象的值
     * 
     * @param object
     * @param propertyName
     * @param <T>
     * @return
     * @author chq
     */
    public static <T> T getByPropertyValue(Object object, String propertyName) {
        if (object == null) {
            return null;
        }

        Method mth = ReflectionUtils.getPropertyMethod(object.getClass(), propertyName);

        T value = null;
        try {
            value = (T) mth.invoke(object);
        } catch (Exception e) {
            throw new SystemErrorRuntimeException(e);
        }
        return value;
    }

    /***
     * 根据bean的某个非空属性propertyName获取数据列表， 其中propertyName首字母必须大写
     * 
     * @param list
     * @param propertyName
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> getByPropertyValue(List<? extends Object> list, String propertyName) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        List<T> retList = new ArrayList<T>(list.size());
        java.util.Set<T> set = new java.util.HashSet<T>(list.size());
        Class<?> clz = list.get(0).getClass();
        Method mth = ReflectionUtils.getPropertyMethod(clz, propertyName);

        for (Object item : list) {
            Object value = null;
            try {
                value = mth.invoke(item);
            } catch (Exception e) {
                throw new SystemErrorRuntimeException(e);
            }
            if (value == null) {
                continue;
            }
            set.add((T) value);
        }
        retList.addAll(set);
        return retList;
    }

    public static <T> List<T> getByPropertyValue(List<? extends Object> list, String propertyName, Class<T> clazz) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        List<T> retList = new ArrayList<T>(list.size());
        java.util.Set<T> set = new java.util.HashSet<T>(list.size());
        Class<?> clz = list.get(0).getClass();
        Method mth = ReflectionUtils.getPropertyMethod(clz, propertyName);

        for (Object item : list) {
            Object value = null;
            try {
                value = mth.invoke(item);
            } catch (Exception e) {
                throw new SystemErrorRuntimeException(e);
            }
            if (value == null) {
                continue;
            }
            set.add(clazz.cast(value));
        }
        retList.addAll(set);
        return retList;
    }

    /***
     * 根据bean的某个非空属性propertyName获取数据列表， 其中propertyName首字母必须大写
     *
     * @param list
     * @param propertyName
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> safeGetByPropertyValue(List<? extends Object> list, String propertyName) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<T>();
        }

        List<T> retList = new ArrayList<T>(list.size());
        java.util.Set<T> set = new java.util.HashSet<T>(list.size());
        Class<?> clz = list.get(0).getClass();
        Method mth = ReflectionUtils.getPropertyMethod(clz, propertyName);

        for (Object item : list) {
            Object value = null;
            try {
                value = mth.invoke(item);
            } catch (Exception e) {
                throw new SystemErrorRuntimeException(e);
            }
            if (value == null) {
                continue;
            }
            set.add((T) value);
        }
        retList.addAll(set);
        return retList;
    }

    public static <T> List<T> safeGetByPropertyValue(List<? extends Object> list, String propertyName, Class<T> clazz) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<T>();
        }

        List<T> retList = new ArrayList<T>(list.size());
        java.util.Set<T> set = new java.util.HashSet<T>(list.size());
        Class<?> clz = list.get(0).getClass();
        Method mth = ReflectionUtils.getPropertyMethod(clz, propertyName);

        for (Object item : list) {
            Object value = null;
            try {
                value = mth.invoke(item);
            } catch (Exception e) {
                throw new SystemErrorRuntimeException(e);
            }
            if (value == null) {
                continue;
            }
            set.add(clazz.cast(value));
        }
        retList.addAll(set);
        return retList;
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> getMapFromListByProperty(List<V> list, String propertyName, Class<K> propertyClass) {
        Map<K, V> resultMap = new HashMap<K, V>();
        if (CollectionUtils.isEmpty(list)) {
            return resultMap;
        }

        Class<?> clz = list.get(0).getClass();

        Method mth = ReflectionUtils.getPropertyMethod(clz, propertyName);
        for (Object obj : list) {
            Object value = null;
            try {
                value = mth.invoke(obj);
            } catch (Exception e) {
                throw new SystemErrorRuntimeException(e);
            }
            if (value == null) {
                continue;
            }
            resultMap.put(propertyClass.cast(value), (V) obj);
        }
        return resultMap;
    }

    public static <K, V> Map<K, V> getMapFromListByPropertySafely(List<V> list, String propertyName,
                                                                  Class<K> propertyClass) {
        if (CollectionUtils.isEmpty(list)) {
            return Maps.newHashMap();
        }

        return getMapFromListByProperty(list, propertyName, propertyClass);
    }

    @SuppressWarnings("unchecked")
    public static <V> Map<String, V> getMapFromListByMultiProperty(List<V> list, boolean separator,
                                                                   String[] propertyList) {
        if (CollectionUtils.isEmpty(list)) {
            return Maps.newHashMap();
        }

        if (propertyList == null || propertyList.length == 0) {
            return Maps.newHashMap();
        }

        Class<?> clz = list.get(0).getClass();
        Map<String, V> resultMap = new HashMap<String, V>(list.size());
        Method[] mthArray = new Method[propertyList.length];

        for (int i = 0; i < propertyList.length; ++i) {
            mthArray[i] = ReflectionUtils.getPropertyMethod(clz, propertyList[i]);
        }

        StringBuffer key = new StringBuffer();
        Object value;
        for (Object obj : list) {
            key.setLength(0);
            for (int i = 0; i < mthArray.length; ++i) {
                value = null;
                try {
                    value = mthArray[i].invoke(obj);
                } catch (Exception e) {
                    throw new SystemErrorRuntimeException(e);
                }
                if (value == null) {
                    key.setLength(0);
                    break;
                } else {
                    if (i != 0 && separator) {
                        key.append('-');
                    }
                    key.append(value);
                }
            }
            if (key.length() > 0) {
                resultMap.put(key.toString(), (V) obj);
            }
        }
        return resultMap;
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, List<V>> getListMapFromListByProperty(List<V> list, String propertyName,
                                                                      Class<K> propertyClass) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        }

        Class<?> clz = list.get(0).getClass();
        Map<K, List<V>> resultMap = new HashMap<K, List<V>>(list.size());
        Method mth = ReflectionUtils.getPropertyMethod(clz, propertyName);
        for (Object obj : list) {
            Object value = null;
            try {
                value = mth.invoke(obj);
            } catch (Exception e) {
                throw new SystemErrorRuntimeException(e);
            }
            if (value == null) {
                continue;
            }
            List<V> valueList = resultMap.get(propertyClass.cast(value));
            if (valueList == null) {
                valueList = new ArrayList<V>();
            }
            valueList.add((V) obj);
            resultMap.put(propertyClass.cast(value), valueList);
        }
        return resultMap;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> getDistinctsByPropertyValueWithOrder(List<? extends Object> list, String propertyName) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        List<T> retList = new ArrayList<T>(list.size());
        Class<?> clz = list.get(0).getClass();
        Method mth = ReflectionUtils.getPropertyMethod(clz, propertyName);

        for (Object item : list) {
            Object value = null;
            try {
                value = mth.invoke(item);
            } catch (Exception e) {
                throw new SystemErrorRuntimeException(e);
            }
            if (value == null) {
                continue;
            }
            retList.add((T) value);
        }

        List<T> distinctList = Lists.newArrayList();
        for (T t : retList) {
            if (distinctList.contains(t)) {
                continue;
            }
            distinctList.add(t);
        }
        return distinctList;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> getAllByPropertyValueWithOrder(List<? extends Object> list, String propertyName) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        List<T> retList = new ArrayList<T>(list.size());
        Class<?> clz = list.get(0).getClass();
        Method mth = ReflectionUtils.getPropertyMethod(clz, propertyName);

        for (Object item : list) {
            Object value = null;
            try {
                value = mth.invoke(item);
            } catch (Exception e) {
                throw new SystemErrorRuntimeException(e);
            }
            if (value == null) {
                continue;
            }
            retList.add((T) value);
        }
        return retList;
    }


    public static <K,V> List<V> sortListByPropertyListSafely(List<V> needSortList, List<K> propertyList, String propertyName,
                                                             Class<K> propertyClass) {
        if (CollectionUtils.isEmpty(needSortList)) {
            return Collections.EMPTY_LIST;
        }
        if(CollectionUtils.isEmpty(propertyList)){
            return needSortList;
        }

        Map<K, V> map= getMapFromListByProperty(needSortList, propertyName, propertyClass);

        List<V> sortedList=new ArrayList<V>(needSortList.size());
        for(K property:propertyList){
            V item=map.get(property);
            if(item!=null) {
                sortedList.add(item);
            }
        }
        return sortedList;
    }
    
    public static <K, V> List<V> buildValueListByKeys(List<K> keyList, Map<K, V> map) {
        if (CollectionUtils.isEmpty(keyList) || map == null) {
            return Lists.newArrayList();
        }
        List<V> valueList = Lists.newArrayList();
        for (K key : keyList) {
            V v = map.get(key);
            if (v != null) {
                valueList.add(v);
            }
        }
        return valueList;
    }

}
