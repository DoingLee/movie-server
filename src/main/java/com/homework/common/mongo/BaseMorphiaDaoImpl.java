package com.homework.common.mongo;

import com.homework.common.collection.PropertyExtractUtils;
import com.homework.common.exception.FrontNotifiableRuntimeException;
import com.homework.common.mongo.domain.DocumentBaseDomain;
import org.apache.commons.lang3.Validate;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseMorphiaDaoImpl<T, S> implements BaseMorphiaDao<T, S> {

    Datastore ds;
    private Class<T> entityClass;

    @Override
    public Datastore getDataStore() {
        return ds;
    }

    @SuppressWarnings("unchecked")
    @Resource(name = "datastore")
    public void setDataStore(Datastore datastore) {
        this.ds = datastore;
        entityClass = (Class<T>) ((ParameterizedType) getClass()
                                                              .getGenericSuperclass()).getActualTypeArguments()[0];
    }


    @Override
    public T getById(S id) {
        Datastore ds = getDataStore();
        T t = ds.get(entityClass, id);
        return t;
    }

    @Override
    public List<T> getByIdList(List<S> ids) {
        Datastore ds = getDataStore();
        Query<T> query = ds.createQuery(entityClass).filter("_id in", ids);
        return query.asList();
    }

    @Override
    public void save(T object) {
        Datastore ds = getDataStore();
        ds.save(object);
    }


    @Override
    public void compareAndsave(T object, S id) {

        if (id == null || object == null) {
            throw new FrontNotifiableRuntimeException("文档保存失败");
        }


        if (!(object instanceof DocumentBaseDomain)) {
            throw new FrontNotifiableRuntimeException("文档保存失败，需要实现接口DocumentVersionComparable");
        }


        Datastore ds = getDataStore();
        T docInDS = ds.get(entityClass, id);

        if (docInDS == null) {
            ds.save(object);
        } else {
            Long version = PropertyExtractUtils.getByPropertyValue(object, "version");

            Long versionInDoc = PropertyExtractUtils.getByPropertyValue(docInDS, "version");
            if (version == null || versionInDoc == null || !version.equals(versionInDoc)) {
                throw new FrontNotifiableRuntimeException("版本不一致，无法保存");
            }

            PropertyExtractUtils.setByPropertyValue(object, "version", version + 1, Long.class);
            ds.save(object);
        }

    }


    @Override
    public void update(Query<T> selectQuery, UpdateOperations<T> updateOperations) {
        Datastore ds = getDataStore();
        ds.update(selectQuery, updateOperations);
    }

    @Override
    public void remove(S id) {
        Datastore ds = getDataStore();
        ds.delete(ds.find(entityClass, "_id  ", id));
    }

    @Override
    public void removeAll(List<S> ids) {
        Datastore ds = getDataStore();
        Query<T> query = ds.createQuery(entityClass).filter("_id in", ids);
        ds.delete(query);
    }

    @Override
    public List<T> getByCondition(List<String> properties, Object... parameters) {
        Validate.notEmpty(properties);
        Validate.notEmpty(parameters);
        Validate.isTrue(properties.size() == parameters.length);

        Datastore ds = getDataStore();
        Query<T> query = ds.createQuery(entityClass);
        for (int i = 0; i < properties.size(); i++) {
            query.filter(properties.get(i), parameters[i]);
        }
        return query.asList();
    }

    @Override
    public List<T> getByConditionForPagination(int skip, int size, List<String> properties, Object... parameters) {
        Validate.notEmpty(properties);
        Validate.notEmpty(parameters);
        Validate.isTrue(properties.size() == parameters.length);

        Datastore ds = getDataStore();
        Query<T> query = ds.createQuery(entityClass);
        for (int i = 0; i < properties.size(); i++) {
            query.filter(properties.get(i), parameters[i]);
        }
        query.offset(skip);
        query.limit(size);
        return query.asList();
    }

    @Override
    public List<T> orderByConditionForPagination(String sort, int skip, int size, List<String> properties, Object... parameters) {
        Validate.notEmpty(properties);
        Validate.notEmpty(parameters);
        Validate.isTrue(properties.size() == parameters.length);

        Datastore ds = getDataStore();
        Query<T> query = ds.createQuery(entityClass);
        for (int i = 0; i < properties.size(); i++) {
            query.filter(properties.get(i), parameters[i]);
        }
        query.offset(skip);
        query.limit(size);
        query.order(sort);
        return query.asList();
    }

    @Override
    public long countByCondition(List<String> properties, Object... parameters) {
        Validate.notEmpty(properties);
        Validate.notEmpty(parameters);
        Validate.isTrue(properties.size() == parameters.length);

        Datastore ds = getDataStore();
        Query<T> query = ds.createQuery(entityClass);
        for (int i = 0; i < properties.size(); i++) {
            query.filter(properties.get(i), parameters[i]);
        }
        return query.countAll();
    }

    @Override
    public T getOneByCondition(List<String> properties, Object... parameters) {
        Validate.notEmpty(properties);
        Validate.notEmpty(parameters);
        Validate.isTrue(properties.size() == parameters.length);

        Datastore ds = getDataStore();
        Query<T> query = ds.createQuery(entityClass);
        for (int i = 0; i < properties.size(); i++) {
            query.filter(properties.get(i), parameters[i]);
        }
        return query.get();
    }
}
