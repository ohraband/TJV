package cz.cvut.fit.tjv.ohraband.farming_monitor.dao;


import cz.cvut.fit.tjv.ohraband.farming_monitor.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface  UserJpaRepository extends JpaRepository<User, Integer>{

    Collection<User> findAllByFirstName(String firstName);
}
