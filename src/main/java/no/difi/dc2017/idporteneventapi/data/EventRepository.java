package no.difi.dc2017.idporteneventapi.data;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import no.difi.dc2017.idporteneventapi.model.Event;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long>{
    Collection<Event> findFirst10BySsnOrderByIdDesc(String ssn);

    @Modifying
    //@Query("select auth_type.value, count(auth_type.id) from auth_type inner join (select * from event where ssn = 01015700269 limit 50000) as ev on auth_type.id = ev.auth_type group by auth_type.id")
    @Query(value = "select auth_type.id, auth_type.value, count(auth_type.id) from (auth_type inner join (select auth_type from event where log_type = 23 and  ssn = ? limit 50000) as ev on ev.auth_type = auth_type.id) group by auth_type.id", nativeQuery = true)
    List<Object[]> getMostUsedId(String ssn);

    @Query(value = "select * from event where (log_type=515 or log_type = 516) and ssn = ? order by id desc limit 1;" , nativeQuery = true)
    List<Event> isReserved(String ssn);
}
