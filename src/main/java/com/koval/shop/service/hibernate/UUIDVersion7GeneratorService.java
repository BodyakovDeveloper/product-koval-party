package com.koval.shop.service.hibernate;

import com.fasterxml.uuid.Generators;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class UUIDVersion7GeneratorService implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return Generators.timeBasedEpochGenerator().generate();
    }
}
