package org.jugnicaragua.picoweb.backend.repositorio;

import org.jugnicaragua.picoweb.backend.modelo.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IPropietarioRepositorio extends JpaRepository<Propietario, Long> {
    @Query("select p from Propietario p " +
            "where lower(p.nombre) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(p.apellido) like lower(concat('%', :searchTerm, '%'))")
    List<Propietario> search(@Param("searchTerm") String searchTerm);
}
