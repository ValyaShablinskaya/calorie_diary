package by.it_academy.calorie_diary.audit;

import by.it_academy.calorie_diary.entity.Audit;
import by.it_academy.calorie_diary.entity.User;
import by.it_academy.calorie_diary.services.api.IAuditService;
import by.it_academy.calorie_diary.services.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

@Component
public class AuditListener {
    private static IAuditService service;
    private static IUserService userService;

    @PrePersist
    public void beforeCreate(Object object) {
        Audit audit = createAudit(object, "create");
        service.create(audit);
    }

    @PreUpdate
    public void beforeUpdate(Object object) {
        Audit audit = createAudit(object, "update");
        service.create(audit);
    }

    @PreRemove
    public void beforeRemove(Object object) {
        Audit audit = createAudit(object, "remove");
        service.create(audit);
    }

    private Audit createAudit(Object object, String text) {
        Audit audit = new Audit();
        User user = userService.findCurrentUser();
        audit.setUser(user);
        audit.setText(text);
        audit.setType(object.getClass().getAnnotation(Auditable.class).type());
        return audit;
    }

    @Autowired
    public void setService(IAuditService service) {
        this.service = service;
    }

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
