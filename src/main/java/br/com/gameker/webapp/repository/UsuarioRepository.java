package br.com.gameker.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gameker.webapp.entity.UsuarioEntity;

/*
Quando usamos o Spring Data não precisamos nos preocupar em montar a query nem usar 
o EntityManager para execução dos comandos SQL, o Spring Data já resolve tudo isso 
para nós, podemos perceber que na Interface acima temos um método que consulta o 
usuário pelo login, isso está sendo possível por que estamos herdando a Interface 
JpaRepository e no nosso método estamos usando o prefixo findBy, atráves 
desse prefixo o Spring já sabe que deve ser realizada uma consulta pelo campo login.
 */
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
	UsuarioEntity findByLogin(String login);
}
