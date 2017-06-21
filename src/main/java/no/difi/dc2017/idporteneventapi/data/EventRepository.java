package no.difi.dc2017.idporteneventapi.data;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import no.difi.dc2017.idporteneventapi.model.Event;

import java.util.Collection;

public interface EventRepository extends JpaRepository<Event, Long>{
    Collection<Event> findFirst10BySsnOrderByIdDesc(String ssn);

}
