package pl.coderslab.medicalregistration.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.medicalregistration.entity.Procedure;

public interface ProcedureRepositoryTest extends JpaRepository<Procedure, Long> {

    @Transactional
    void deleteByProcedureName(String name);
}
