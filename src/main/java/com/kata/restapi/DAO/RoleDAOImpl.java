package com.kata.restapi.DAO;

import com.kata.restapi.model.Role;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private final EntityManager em;

    public RoleDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Role> getAllRoles() {
        return em.createQuery("SELECT r FROM Role r", Role.class).getResultList();
    }

    @Override
    public void saveRole(Role role) {
        em.merge(role);
    }

    @Override
    public Role getRole(Long id) {
        return em.find(Role.class, id);
    }

    @Override
    public Role getRoleByName(String name) {
        TypedQuery<Role> tq = em.createQuery("SELECT r FROM Role AS r WHERE r.role=:param", Role.class);
        return tq.setParameter("param", name).getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public void deleteRole(Long id) {
        Role role = em.getReference(Role.class, id);
        em.remove(role);
    }
}
