package file;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@RestController
public class UsersController {
    private final AtomicLong id = new AtomicLong();

    @RequestMapping("/allUsers")
    public List<Users> allUsers() {
        Query query = HibernateSessionFactory.getSessionFactory().openSession().createQuery("from Users");
        return query.list();
    }

    @RequestMapping(value = "/addUser")
    public Users addUser(@RequestParam String username,@RequestParam String firstname,@RequestParam String email) {
        Users user = new Users();
        user.setUserName(username);
        user.setFirstName(firstname);
        user.setEmail(email);

        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        user.setId(0);
        session.save(user);
        session.getTransaction().commit();
        session.close();

        return user;
    }

    @RequestMapping("/deleteUser")
    public Integer deleteUser(@RequestParam long id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Users user = session.get(Users.class,id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
        return 1;
    }
}
