package no.difi.dc2017.idporteneventapi.data;

import no.difi.dc2017.idporteneventapi.model.AuthType;
import no.difi.dc2017.idporteneventapi.model.EventData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

/**
 * Created by camp-ano on 21.06.2017.
 */


public interface EventDataRepository extends JpaRepository<EventData, Long> {
}
