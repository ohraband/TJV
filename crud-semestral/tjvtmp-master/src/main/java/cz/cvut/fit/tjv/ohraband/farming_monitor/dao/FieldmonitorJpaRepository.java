package cz.cvut.fit.tjv.ohraband.farming_monitor.dao;

import cz.cvut.fit.tjv.ohraband.farming_monitor.domain.Fieldmonitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FieldmonitorJpaRepository extends JpaRepository<Fieldmonitor, Integer> {
}
