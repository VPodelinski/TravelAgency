package by.vitali.infrastructure.module;

import javax.persistence.OneToMany;
import java.util.Set;

public class TypeAction {
    private int id;
    private String actionType;
    @OneToMany(mappedBy = "typeAction")
    private Set<Tour> actions;
}
