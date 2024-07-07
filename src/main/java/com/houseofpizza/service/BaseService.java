package com.houseofpizza.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import com.houseofpizza.exceptions.ErrorCodes;
import com.houseofpizza.repository.BaseRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseService<R extends BaseRepository<T, ID>, T, ID extends Serializable> {

    protected final R repository;

    protected BaseService(R repository) {
        this.repository = repository;
    }

    public R getRepository() {
        return this.repository;
    }

    /**
     * Find one.
     *
     * @param id id of entity
     * @return optional of entity
     */
    @Transactional(readOnly = true)
    public Optional<T> findOne(ID id) {
        return this.getRepository().findById(id);
    }

    /**
     * Find one or return default entity.
     *
     * @param id            id of entity
     * @param defaultEntity default entity passed with param
     * @return entity found
     */
    @Transactional(readOnly = true)
    public T findOneOrDefault(ID id, T defaultEntity) {
        return id != null
            ? findOne(id).orElse(defaultEntity)
            : defaultEntity;
    }

    /**
     * Find one or return null.
     *
     * @param id id of entity
     * @return entity
     */
    @Transactional(readOnly = true)
    public T findOneOrNull(ID id) {
        return findOneOrDefault(id, null);
    }

    /**
     * FindAll.
     *
     * @return all entities
     */
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return this.getRepository().findAll();
    }

    /**
     * FindAll paged.
     *
     * @param pageable for pagination
     * @return all paged entities
     */
    @Transactional(readOnly = true)
    public Page<T> findAll(Pageable pageable) {
        return this.getRepository().findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<T> findByIdsIn(List<ID> ids) {
        return this.getRepository().findAllById(ids);
    }

    @Transactional
    public T save(T entity) {
        return this.getRepository().save(entity);
    }

    @Transactional
    public List<T> saveAll(List<T> entities) {
        return this.getRepository().saveAll(entities);
    }

    /**
     * Find one or return error message.
     *
     * @param id     id of entity
     * @param status status http error
     * @param error  error code message
     * @return entity
     */
    @Transactional(readOnly = true)
    public T findOneOrError(ID id, HttpStatus status, ErrorCodes error) {
        return this.getRepository().findById(id)
            .orElseThrow(() -> ErrorCodes.generateErrorException(status, error));
    }

    /**
     * Find one or throw standard error message 'Entity not found'.
     *
     * @param id id of entity
     * @return entity found or error message with http error code 404 and error message
     */
    @Transactional(readOnly = true)
    public T findOneOrError404(ID id) {
        return this.getRepository().findById(id)
            .orElseThrow(() -> ErrorCodes.generateError404(ErrorCodes.NOT_FOUND));
    }

    @Transactional(readOnly = true)
    public T findOneOrError404(ID id, Enum<?> errorEnum) {
        return this.getRepository().findById(id)
            .orElseThrow(() -> ErrorCodes.generateError404(errorEnum));
    }

    @Transactional(readOnly = true)
    public List<T> findByIdsInOrError(List<ID> ids, HttpStatus status, ErrorCodes error) {
        List<T> list = findByIdsIn(ids);
        if (list.size() == ids.size()) {
            return list;
        }
        throw ErrorCodes.generateErrorException(status, error);
    }

    public void delete(T entity) {
        this.getRepository().delete(entity);
    }

    public void deleteAll() {
        this.getRepository().deleteAll();
    }

}