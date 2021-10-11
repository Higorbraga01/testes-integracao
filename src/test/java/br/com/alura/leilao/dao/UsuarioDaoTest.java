package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JpaUtil;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioDaoTest {

    private UsuarioDao dao;
    private  EntityManager em ;

    @BeforeEach
    public void beforeEach() {
        this.em = JpaUtil.getEntityManger();
        this.dao = new UsuarioDao(em);
        em.getTransaction().begin();
    }

    @AfterEach
    public void afterEach() {
        em.getTransaction().rollback();
    }

    @Test
    void deveriaEncontrarUsuarioCadastrado() {
        Usuario usuario = criarUsuario();
        Usuario encontrado = this.dao.buscarPorUsername(usuario.getNome());
        Assert.assertNotNull(encontrado);
    }

    @Test
    void naoDeveriaEncontrarUsuarioCadastrado() {
        criarUsuario();
        Assert.assertThrows(NoResultException.class, () -> this.dao.buscarPorUsername("Beltrano"));
    }

    private  Usuario criarUsuario() {
        Usuario usuario = new Usuario("Fulano", "fulano@email.comn", "123456");
        em.persist(usuario);
        return usuario;
    }

    @Test
    void deveriaRemoverUmUsuario() {
        Usuario usuario = criarUsuario();
        dao.deletar(usuario);
        Assert.assertThrows(NoResultException.class, () -> this.dao.buscarPorUsername("Fulano"));
    }
}