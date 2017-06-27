package no.difi.dc2017.idporteneventapi.data;

import org.springframework.data.jpa.repository.JpaRepository;
import no.difi.dc2017.idporteneventapi.model.StatYear;

import java.util.Collection;

public interface StatYearRepository extends JpaRepository<StatYear, Long> {
    Collection<StatYear> findByIssuer(String issuer);
}