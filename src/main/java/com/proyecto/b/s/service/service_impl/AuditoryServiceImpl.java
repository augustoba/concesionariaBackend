package com.proyecto.b.s.service.service_impl;

import com.proyecto.b.s.repository.AuditoryRepository;
import com.proyecto.b.s.model.Auditory;
import com.proyecto.b.s.model.User;
import com.proyecto.b.s.service.AuditoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditoryServiceImpl implements AuditoryService {

    private final AuditoryRepository auditoryRepository;

    public AuditoryServiceImpl(AuditoryRepository auditoryRepository) {
        this.auditoryRepository = auditoryRepository;
    }

    @Override
    public void auditorySave(String eventAfter, String eventBefore, User user, String modificationType){
        Auditory auditory = new Auditory();
        auditory.setDate(LocalDateTime.now());

            auditory.setAfterModification(eventAfter);


        auditory.setModificationType(modificationType);
        if (user==null){
            auditory.setUser("--");
        }else{
            auditory.setUser(user.getId() + " | " + user.getName() + " - " + user.getLastName());

        }


        auditory.setBeforeModification(eventBefore);
        auditoryRepository.save(auditory);
    }
}
