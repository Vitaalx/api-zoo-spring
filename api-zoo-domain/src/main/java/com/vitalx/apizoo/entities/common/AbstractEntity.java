package com.vitalx.apizoo.entities.common;

import java.util.UUID;

public abstract class AbstractEntity {
    private final String id = UUID.randomUUID().toString();
}
