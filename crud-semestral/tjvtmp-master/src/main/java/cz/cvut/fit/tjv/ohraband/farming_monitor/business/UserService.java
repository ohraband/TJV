package cz.cvut.fit.tjv.ohraband.farming_monitor.business;



import cz.cvut.fit.tjv.ohraband.farming_monitor.dao.UserJpaRepository;
import cz.cvut.fit.tjv.ohraband.farming_monitor.domain.User;
import org.springframework.stereotype.Component;




@Component
public class UserService extends AbstractCrudService<Integer, User, UserJpaRepository> {
    public UserService(UserJpaRepository userRepository) {
        super(userRepository);
    }

    @Override
    protected boolean exists(User entity) {
        return repository.existsById(entity.getId());
    }
}



//import cz.cvut.fit.tjv.ohraband.farming_monitor.api.controller.UserDto;
//        import org.springframework.web.bind.annotation.DeleteMapping;
//        import org.springframework.web.bind.annotation.GetMapping;
//        import org.springframework.web.bind.annotation.PutMapping;
//
//public class UserService {
//
//    public static String create(){
//        return "Brand new object";
//    }
//
//
//    public static String readById(String id){
//        return "Object" + id;
//    }
//
//    public static String update(){
//        return "Object updated";
//    }
//
//
//    public static void delete(){
//    }
//
//    public static UserDto createobj(){
//        UserDto u = new UserDto("createdusername", "firstName", "lastName");
//        return u ;
//    }
//
//
//    public static String GetFieldmonitor(){
//        return "Fieldmonitor assigned to this id ";
//    }
//
//
//    public static String UpdateFieldmonitor(){ return "Fieldmonitor updated ";
//    }
//
//    public static void DeleteFieldmonitor(){
//    }
//
//
//
//}
