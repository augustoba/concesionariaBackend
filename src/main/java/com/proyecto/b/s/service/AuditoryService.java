package com.proyecto.b.s.service;

import com.proyecto.b.s.model.User;

public interface AuditoryService {

    void auditorySave(String eventAfter, String eventBefore, User user, String modificationType);
}
