package ua.lviv.iot.persistence.dao;

import ua.lviv.iot.Good;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

@Named
@Dependent
public class GoodDaoImpl extends AbstractDao<Good> implements GoodDao, Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    protected Class<Good> getEntityClass() {
        return Good.class;
    }

    //@Resource
    //private UserTransaction userTransaction;

    @PostConstruct
    public void init() {
        System.out.println("ready");
    }

}
