package com.tribium.auditking.core;
import org.springframework.data.repository

@RepositoryInterceptor
public class AuditRepositoryInterceptor implements RepositoryInterceptor {

    @Override
    public void preInvoke(RepositoryMethod repositoryMethod, Object[] args) {
        System.out.println("Executing repository method: " + repositoryMethod.getName());
    }

    @Override
    public void postInvoke(RepositoryMethod repositoryMethod, Object[] args, Object result) {
        System.out.println("Repository method execution completed: " + repositoryMethod.getName());
    }
}