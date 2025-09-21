package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.constants.EazySchoolConstants;
import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.model.Roles;
import com.eazybytes.eazyschool.repository.PersonRepository;
import com.eazybytes.eazyschool.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private RolesRepository rolesRepository;

    public boolean createNewPerson(Person person){
        boolean isSaved=false;
        Roles roles=rolesRepository.getByRoleName(EazySchoolConstants.STUDENT_ROLE);
        person.setRoles(roles);
        Person savedPerson=personRepository.save(person);
        if (savedPerson!=null&& savedPerson.getPersonId()>0){
            isSaved=true;
        }
        return isSaved;
    }

}
