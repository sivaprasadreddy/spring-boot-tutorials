package com.sivalabs.demo.security;

import com.sivalabs.demo.MyUserPrincipal;
import com.sivalabs.demo.entities.Privilege;
import com.sivalabs.demo.entities.Role;
import com.sivalabs.demo.entities.User;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;


public class CustomMethodSecurityExpressionRoot
        extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private Object filterObject;
    private Object returnObject;

    public CustomMethodSecurityExpressionRoot(Authentication authentication) {
        super(authentication);
    }

    public boolean hasPrivilege(String privilegeName) {
        User user = ((MyUserPrincipal) this.getPrincipal()).getUser();
        for (Role role : user.getRoles()) {
            for (Privilege privilege : role.getPrivileges()) {
                if(privilege.getName().equals(privilegeName)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Object getFilterObject() {
        return this.filterObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }

    @Override
    public void setFilterObject(Object obj) {
        this.filterObject = obj;
    }

    @Override
    public void setReturnObject(Object obj) {
        this.returnObject = obj;
    }
}

