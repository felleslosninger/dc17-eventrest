package no.difi.dc2017.idporteneventapi.data;

import no.difi.dc2017.idporteneventapi.model.LogType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by camp-ano on 22.06.2017.
 */
public interface LogTypeRepository extends JpaRepository<LogType, Long> {

}
