package com.dev.ForoEscolar.services.impl;

import com.dev.ForoEscolar.dtos.asistencia.AsistenciaDTO;
import com.dev.ForoEscolar.mapper.asistencia.AsistenciaMapper;
import com.dev.ForoEscolar.model.Asistencia;
import com.dev.ForoEscolar.repository.AsistenciaRepository;
import com.dev.ForoEscolar.services.AsistenciaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AsistenciaServiceImpl implements AsistenciaService {

    private final AsistenciaRepository asistenciaRepository;

    private final AsistenciaMapper asistenciaMapper;

    @Autowired
    public AsistenciaServiceImpl(AsistenciaRepository asistenciaRepository, AsistenciaMapper asistenciaMapper) {
        this.asistenciaRepository = asistenciaRepository;
        this.asistenciaMapper = asistenciaMapper;
    }


    @Override
    public AsistenciaDTO update(AsistenciaDTO asistenciaDTO) {
       return null;
    }

    @Override
    @Transactional
    public AsistenciaDTO save(AsistenciaDTO requestDTO) {
        Asistencia newAsistencia = asistenciaMapper.toEntity(requestDTO);
        asistenciaRepository.save(newAsistencia);

        long totalAsistencias = asistenciaRepository.countByAsistio(true);
        long totalRegistros = asistenciaRepository.count();
        double porcentajeAsistencia = totalRegistros > 0
                ? (double) totalAsistencias / totalRegistros * 100
                : 0;
        AsistenciaDTO responseDTO = asistenciaMapper.toResponseDto(newAsistencia);
        responseDTO = new AsistenciaDTO(
                responseDTO.id(),
                responseDTO.asistio(),
                responseDTO.diasAnioEscolar(),
                responseDTO.fecha(),
                responseDTO.observaciones(),
                porcentajeAsistencia,
                responseDTO.profesor(),
                responseDTO.estudiante()
        );
        return responseDTO;
    }

    @Override
    public Optional<AsistenciaDTO> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Iterable<AsistenciaDTO> findAll() {
        return null;
    }

    @Override
    public void deleteById(Long aLong) {

    }

//    private double calcularPorcentajeAsistencia() {
//        long totalAsistencias = asistenciaRepository.countByAsistio(true);
//        long totalRegistros = asistenciaRepository.count();
//        return totalRegistros > 0 ? (double) totalAsistencias / totalRegistros * 100 : 0;
//    }
}
