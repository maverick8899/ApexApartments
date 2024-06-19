
import com.dong.pojo.Accounts;
import com.dong.pojo.Customer;
import com.dong.pojo.Service;
import com.dong.repository.UserRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public Accounts getUserByUserName(String username) {

        Session session = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Service> q = b.createQuery(Service.class);
        Root rAcc = q.from(Accounts.class);
        Root rCus = q.from(Customer.class);
        q.select(rAcc);
        if (!username.isEmpty()) {
            Predicate p = b.equal(rAcc.get("username").as(String.class), username);
            q.where(p);
        }
        Query query = session.createQuery(q);
        return (Accounts) query.getSingleResult();
    }

    @Override
    public void addUser(Accounts user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
