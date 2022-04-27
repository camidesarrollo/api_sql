package app.inmobiliaria.api.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "roles")
@JsonIgnoreProperties(value = { "handler", "hibernateLazyInitializer" })
public class Role {
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;

	  @Column(length = 20)
	  private String name;
	  
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name = "vigencia_id")
	  private Vigencia vigencia;

	public Role() {

	}

	public Role(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Vigencia getVigencia() {
		return vigencia;
	}

	public void setVigencia(Vigencia vigencia) {
		this.vigencia = vigencia;
	}
	  
	  
}