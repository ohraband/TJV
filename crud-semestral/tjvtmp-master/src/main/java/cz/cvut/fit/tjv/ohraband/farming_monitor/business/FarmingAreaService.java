package cz.cvut.fit.tjv.ohraband.farming_monitor.business;



import cz.cvut.fit.tjv.ohraband.farming_monitor.dao.FarmingAreaJpaRepository;
import cz.cvut.fit.tjv.ohraband.farming_monitor.domain.FarmingArea;
import org.springframework.stereotype.Component;




@Component
public class FarmingAreaService extends AbstractCrudService<Integer, FarmingArea, FarmingAreaJpaRepository> {
    public FarmingAreaService(FarmingAreaJpaRepository FarmingAreaRepository) {
        super(FarmingAreaRepository);
    }

    @Override
    protected boolean exists(FarmingArea entity) {
        return repository.existsById(entity.getId());
    }
}