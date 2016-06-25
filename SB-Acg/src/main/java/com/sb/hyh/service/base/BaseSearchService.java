package com.sb.hyh.service.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sb.hyh.dao.base.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 通用查询服务
 */
public abstract class BaseSearchService<T, ID extends Serializable> {
    @Autowired
    protected BaseDao<T, ID> baseDao;
    @Autowired
    protected JdbcTemplate jdbcTemplate;
    /**
     * 对象转换器
     */
    protected ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 日志记录器
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取当前类泛型实体类型Class
     */
    public Class<T> getEntityClass() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class) params[0];
    }

    /**
     * 动态条件分页查询
     */
    public Page<T> findPage(Specification<T> specification, PageRequest pageRequest) {
        return baseDao.findAll(specification, pageRequest);
    }

    /**
     * 通过ID查找
     */
    public T findById(ID id) {
        return baseDao.findOne(id);
    }

    /**
     * 通过动态条件查找
     */
    public T findBySpecification(Specification<T> specification) {
        return baseDao.findOne(specification);
    }

    /**
     * 通过ID判断是否存在
     */
    public boolean exists(ID id) {
        return baseDao.exists(id);
    }

    /**
     * 查询数量
     */
    public long count() {
        return baseDao.count();
    }

    /**
     * 动态条件查询数量
     */
    public long count(Specification<T> specification) {
        return baseDao.count(specification);
    }

    /**
     * 查询所有
     */
    public List<T> findAll() {
        return baseDao.findAll();
    }

    /**
     * 动态条件查询所有
     */
    public List<T> findAll(Specification<T> specification) {
        return baseDao.findAll(specification);
    }

    /**
     * 动态条件查询所有
     */
    public List<T> findAll(Sort sort) {
        return baseDao.findAll(sort);
    }

    /**
     * 动态条件查询所有
     */
    public List<T> findAll(Specification<T> specification, Sort sort) {
        return baseDao.findAll(specification, sort);
    }
}