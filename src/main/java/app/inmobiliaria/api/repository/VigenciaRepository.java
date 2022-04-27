package app.inmobiliaria.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.inmobiliaria.api.entity.Vigencia;


public interface VigenciaRepository extends JpaRepository<Vigencia, Long> {

	   Vigencia findByName(String strVigencia);
	   

}
