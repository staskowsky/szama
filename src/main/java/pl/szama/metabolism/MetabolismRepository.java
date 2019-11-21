package pl.szama.metabolism;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.szama.user.User;

@Repository
public interface MetabolismRepository extends JpaRepository<Metabolism, User> {
    Metabolism findByUser(User user);
}
