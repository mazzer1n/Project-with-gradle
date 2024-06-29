package ru.malyarov.maxim.gradlestarter.dao;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.malyarov.maxim.gradlestarter.models.Person;

import java.util.List;
import java.util.UUID;

@Component
@Transactional(readOnly = true)
public class PersonDAO {
    private final EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Person> index() {
        return entityManager.createQuery("select p from Person p", Person.class).getResultList();
    }

    public Person show(UUID uuid) {
        return entityManager.find(Person.class, uuid);
    }
    @Transactional
    public void save(Person person) {
        entityManager.persist(person);
    }

    public void update(UUID id, Person updatedPerson) {
        Person personToBeUpdated = entityManager.find(Person.class, id);
            personToBeUpdated.setFullname(updatedPerson.getFullname());
            personToBeUpdated.setBirthday(updatedPerson.getBirthday());
            entityManager.merge(personToBeUpdated);
    }

    public void delete(UUID id) {
        entityManager.remove(id);
    }
}
