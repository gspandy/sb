package com.sb.hyh.service.base;


import java.io.Serializable;

/**
 * 通用CRUD服务
 */
public abstract class BaseService<T, ID extends Serializable> extends BaseSearchService<T, ID> {

    /**
     * 保存
     */
    public <S extends T> void save(S entity) {
        baseDao.save(entity);
    }

    /**
     * 批量保存
     */
    public <S extends T> Iterable<S> save(Iterable<S> entities) {
        return baseDao.save(entities);
    }

    /**
     * 通过ID删除
     */
    public void delete(ID id) {
        baseDao.delete(id);
    }

    /**
     * 删除实体
     */
    public void delete(T entity) {
        baseDao.delete(entity);
    }

    /**
     * 通过ID批量删除
     */
    public void delete(Iterable<ID> ids) {
        for (ID id : ids) {
            delete(id);
        }
    }
}
