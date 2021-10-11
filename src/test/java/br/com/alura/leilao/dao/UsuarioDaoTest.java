package br.com.alura.leilao.dao;

import br.com.alura.leilao.model.Usuario;
import br.com.alura.leilao.util.JpaUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioDaoTest {

    private UsuarioDao dao;

    @Test
    void testeBuscarUsuarioPorUserName() {
        EntityManager em = JpaUtil.getEntityManger();
        this.dao = new UsuarioDao(em);
        Usuario usuario = new Usuario("Fulano", "fulano@email.comn", "123456");
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();

        Usuario encontrado = this.dao.buscarPorUsername(usuario.getNome());
        Assert.assertNotNull(encontrado);
    }

    @Test
    void deletar() {
    }
}