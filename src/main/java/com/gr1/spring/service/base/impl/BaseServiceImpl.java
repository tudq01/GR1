package com.gr1.spring.service.base.impl;

import com.gr1.spring.entity.base.BaseEntity;
import com.gr1.spring.exception.EntityNotFoundException;
import com.gr1.spring.repository.base.BaseRepository;
import com.gr1.spring.service.base.BaseService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

@Service
public abstract  class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

   private final BaseRepository<T,Long> baseRepository;
    private Class getGenericClass() {
        return ((Class<T>)
                ((ParameterizedType) getClass().getGenericSuperclass())
                        .getActualTypeArguments()[0]);
    }
    protected BaseServiceImpl(BaseRepository<T, Long> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public T findById(Long id) {
        return baseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(getGenericClass(), "id", id.toString()));
    }

    @Override
    @Transactional
    public T save(T entity) {
        return baseRepository.save(entity);
    }

    // not good!
    @Override
    @Transactional
    public T updateById(Long id, T newEntity) {
        T oldEntity = this.findById(id);
        if (oldEntity == null) {
            throw new EntityNotFoundException(getGenericClass(), "id", id.toString());
        }
        newEntity.setId(id);
        return baseRepository.save(newEntity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Optional<T> optional = Optional.ofNullable(this.findById(id));
        optional.map(entity -> {
                    baseRepository.deleteById(id);
                    return id;
                })
                .orElseThrow(() -> new EntityNotFoundException(getGenericClass(), "id", id.toString()));
    }
}
