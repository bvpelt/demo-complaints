package nl.bsoft.persistence;

import nl.bsoft.core.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by bvpelt on 1/28/17.
 */
@Repository
public interface ComplaintQueryObjectRepository extends JpaRepository<Complaint, String> {
}
