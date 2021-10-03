package pl.coderslab.medicalregistration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.medicalregistration.entity.Procedure;
import pl.coderslab.medicalregistration.utils.ProcedureRepository;

import java.util.ArrayList;
import java.util.List;

public class ProcedureConverter implements Converter<String, List<Procedure>> {

    @Autowired
    ProcedureRepository procedureRepository;






    @Override
    public List<Procedure> convert(String source) {
        List<Procedure> listProcedure = new ArrayList<>();
        Procedure procedure = procedureRepository.getById(Long.parseLong(source));
        listProcedure.add(procedure);
        return listProcedure;
    }
}
