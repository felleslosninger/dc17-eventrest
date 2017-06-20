package no.difi.dc2017.idporteneventapi.data;

import org.springframework.data.jpa.repository.JpaRepository;

import no.difi.dc2017.idporteneventapi.model.Event;
import org.springframework.stereotype.Repository;


public interface EventRepository extends JpaRepository<Event, Long>{

}
