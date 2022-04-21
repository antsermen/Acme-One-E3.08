package acme.features.patron.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.PatronageReport;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronPatronageReportRepository extends AbstractRepository {
	
	@Query("select p from PatronageReport p where p.id = :id")
	PatronageReport findOnePatronageReportById(int id);
	
	@Query("select p from PatronageReport p where p.patronage.id = :patronId")
	Collection<PatronageReport> findManyPatronagesReportByPatronId(int patronId);
	
}

