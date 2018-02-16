package com.homework.common.mongo.domain;

import java.io.Serializable;

public abstract class DocumentBaseDomain implements Serializable, DocumentVersionComparable {

    private static final long serialVersionUID = -6677452322246033257L;

    private Long version          = 3250689160307891L;

    @Override
    public Long getVersion() {
        return version;
    }

    @Override
    public void setVersion(Long version) {
        this.version = version;
    }
}
