package cz.cvut.fit.tjv.ohraband.farming_monitor.business;



import cz.cvut.fit.tjv.ohraband.farming_monitor.dao.FieldmonitorJpaRepository;
import cz.cvut.fit.tjv.ohraband.farming_monitor.domain.Fieldmonitor;
import org.springframework.stereotype.Component;




@Component
public class FieldmonitorService extends AbstractCrudService<Integer, Fieldmonitor, FieldmonitorJpaRepository> {
    public FieldmonitorService(FieldmonitorJpaRepository fieldmonitorRepository) {
        super(fieldmonitorRepository);
    }

    @Override
    protected boolean exists(Fieldmonitor entity) {
        return repository.existsById(entity.getIdFieldmonitor());
    }
}
