package dao;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import model.Pessoa;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

@ManagedBean
@ApplicationScoped
public class PessoaDao {

    public List<Pessoa> listaTodos() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        List lista = session.createQuery("from Pessoa").list();
        t.commit();

        return lista;
    }

    public void insere(Pessoa p1) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();

        session.persist(p1);
        t.commit();

    }

    public List<Pessoa> listaPorCodigo(Integer codigo) {

        List lista = new ArrayList<>();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;

        try {
            t = session.beginTransaction();
            Pessoa p = (Pessoa) session.get(Pessoa.class, codigo);

            if (p != null) {
                lista.add(p);
            }

            t.commit();
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            throw e;
        } finally {
            session.close();
        }

        return lista;
    }

    public List<Pessoa> listaPorNome(String nome) {

        List lista = null;

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = null;

        try {
            t = session.beginTransaction();
            Query query = session.createQuery("from Pessoa where nome like :nome");
            query.setParameter("nome", String.format("%%%s%%", nome));
            lista = query.list();
            t.commit();
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            throw e;
        } finally {
            session.close();
        }

        return lista;
    }

    public void atualiza(Pessoa pessoa) {
        Session session = HibernateUtil
                .getSessionFactory().openSession();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            session.merge(pessoa);
            t.commit();
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }
}
