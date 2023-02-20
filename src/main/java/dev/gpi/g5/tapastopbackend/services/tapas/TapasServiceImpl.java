package dev.gpi.g5.tapastopbackend.services.tapas;

import dev.gpi.g5.tapastopbackend.models.Tapa;
import dev.gpi.g5.tapastopbackend.repositories.TapasRepository;
import dev.gpi.g5.tapastopbackend.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TapasServiceImpl implements TapasService {

    @Autowired
    private TapasRepository repository;


    @Override
    public List<Tapa> findAll() {
        return null;
    }

    @Override
    public Tapa findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Tapa save(Tapa object) {
        return null;
    }

    @Override
    public void delete(Tapa object) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
