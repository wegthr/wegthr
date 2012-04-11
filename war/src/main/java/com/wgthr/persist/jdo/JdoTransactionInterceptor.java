package com.wgthr.persist.jdo;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.jdo.Transaction;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@Singleton
public class JdoTransactionInterceptor implements MethodInterceptor {

    @Inject
    private JdoPersistenceManagerProvider pmProvider;
    
    @Override
    public Object invoke(final MethodInvocation invocation) throws Throwable {
        
        final Transaction tx = pmProvider.get().currentTransaction();
        final Object returner;
        try {
           tx.begin(); 
           returner = invocation.proceed();
           tx.commit();
        } catch (final Exception e) {
           tx.rollback();
           throw e;
        }
        return returner;
    }

}
