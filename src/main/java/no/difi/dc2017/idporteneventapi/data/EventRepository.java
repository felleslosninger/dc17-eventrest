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
    @Query(value = "select auth_type.value, count(auth_type.id) from (auth_type inner join (select auth_type from event where ssn = 01015700269 limit 50000) as ev on ev.auth_type = auth_type.id) group by auth_type.id", nativeQuery = true)
    List<Object[]> getMostUsed();

}
