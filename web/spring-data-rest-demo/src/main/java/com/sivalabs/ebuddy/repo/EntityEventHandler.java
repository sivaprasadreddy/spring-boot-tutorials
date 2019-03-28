package com.sivalabs.ebuddy.repo;

import com.sivalabs.ebuddy.entity.Bookmark;
import com.sivalabs.ebuddy.entity.Note;
import com.sivalabs.ebuddy.entity.Todo;
import com.sivalabs.ebuddy.entity.Transaction;
import com.sivalabs.ebuddy.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@RepositoryEventHandler
@Component
public class EntityEventHandler {

    @Autowired
    private SecurityUtils securityUtils;

    @HandleBeforeCreate
    @HandleBeforeSave
    public void handleSave(Bookmark entity) {
        if(entity.getCreatedBy() == null) {
            entity.setCreatedBy(securityUtils.getLoginUser());
        }
    }

    @HandleBeforeCreate
    @HandleBeforeSave
    public void handleSave(Note entity) {
        if(entity.getCreatedBy() == null) {
            entity.setCreatedBy(securityUtils.getLoginUser());
        }
    }

    @HandleBeforeCreate
    @HandleBeforeSave
    public void handleSave(Todo entity) {
        if(entity.getCreatedBy() == null) {
            entity.setCreatedBy(securityUtils.getLoginUser());
        }
    }

    @HandleBeforeCreate
    @HandleBeforeSave
    public void handleSave(Transaction entity) {
        if(entity.getCreatedBy() == null) {
            entity.setCreatedBy(securityUtils.getLoginUser());
        }
    }

}
