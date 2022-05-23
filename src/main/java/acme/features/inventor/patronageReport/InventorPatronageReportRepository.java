package acme.features.inventor.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.Patronage;
import acme.entities.PatronageReport;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronageReportRepository extends AbstractRepository {

	@Query("select p from PatronageReport p where p.patronage.inventor.id = :id")
	Collection<PatronageReport> findManyPatronageReportByInventorId(int id);

	@Query("select pr from PatronageReport pr where pr.id = :id")
	PatronageReport findOnePatronageReportById(int id);
	
	@Query("select pr from PatronageReport pr")
	Collection<PatronageReport> findAllPatronageReports();
	
	@Query("select p from Patronage p where p.id = :id")
	Patronage findPatronageById(int id);
	
	@Query("select pr from PatronageReport pr where pr.patronage.id = :id")
	Collection<PatronageReport> findPatronageReportByPatronageId(int id);
	
	@Query("select p from Patronage p where p.code = :code")
	Patronage findPatronageByCode(String code);
	
	@Query("select p from Patronage p where p.inventor.id = :id and p.published=:published")
	Collection<Patronage> findManyPatronagesByInventorId(int id, boolean published);

} 