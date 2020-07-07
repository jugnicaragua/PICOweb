package org.jugnicaragua.picoweb.backend.servicio;

import org.jugnicaragua.picoweb.backend.modelo.Propietario;
import org.jugnicaragua.picoweb.backend.repositorio.IPropietarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PropietarioServicio {

    private static final Logger LOGGER = Logger.getLogger(PropietarioServicio.class.getName());
    private IPropietarioRepositorio propietarioRepositorio;


    public PropietarioServicio(IPropietarioRepositorio propietarioRepositorio) {
        this.propietarioRepositorio = propietarioRepositorio;
    }

    public List<Propietario> findAll() {
        return propietarioRepositorio.findAll();
    }

    public List<Propietario> findAll(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return propietarioRepositorio.findAll();
        } else {
            return propietarioRepositorio.search(stringFilter);
        }
    }

    public long count() {
        return propietarioRepositorio.count();
    }

    public void delete(Propietario propietario) {
        propietarioRepositorio.delete(propietario);
    }

    public void save(Propietario propietario) {
        if (propietario == null) {
            LOGGER.log(Level.SEVERE,
                    "El propietario esta nulo. ¿Está seguro que está bien escrito?");
            return;
        }
        propietarioRepositorio.save(propietario);
    }

    public Map<String, Integer> getStats() {
        HashMap<String, Integer> stats = new HashMap<>();
        findAll().forEach(propietario -> stats.put(propietario.getNombre(),1));
        return stats;
    }

}
