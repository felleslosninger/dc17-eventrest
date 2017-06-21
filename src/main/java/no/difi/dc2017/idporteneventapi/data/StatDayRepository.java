package no.difi.dc2017.idporteneventapi.data;

import no.difi.dc2017.idporteneventapi.model.EventDataType;
import no.difi.dc2017.idporteneventapi.model.StatDay;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by camp-ano on 21.06.2017.
 */
public interface StatDayRepository extends JpaRepository<StatDay, Long> {
}
