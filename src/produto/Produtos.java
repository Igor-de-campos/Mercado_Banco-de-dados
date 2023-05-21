package produto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Products")
public class Produtos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    @Column(name = "unidades")
	private int unidades;

	@Column(name = "nome")
	private String nome;

    @Column(name = "codigo") 
    private int codigo;

    @Column(name = "setor")
	private String setor;
    
    private static int codigoInicial = 1000;
    
    public Produtos() {
    	this.codigo = codigoInicial;
    	incrementarCodigo();
    }
	
	
	public int getUnidades() {
		return unidades;	
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
	
	private static void incrementarCodigo() {
		codigoInicial++;
	}
	
	
	

}
