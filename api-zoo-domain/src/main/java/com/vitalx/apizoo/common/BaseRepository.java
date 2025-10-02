package com.vitalx.apizoo.common;

import com.vitalx.apizoo.entities.common.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<
        GenericEntity extends AbstractEntity
        > {
    List<GenericEntity> findAll();

    void save(final GenericEntity entity);

    Optional<GenericEntity> findById(final int id);
}
