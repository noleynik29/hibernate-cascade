package core.basesyntax.dao.impl;

import core.basesyntax.dao.MessageDetailsDao;
import core.basesyntax.model.Message;
import core.basesyntax.model.MessageDetails;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import static core.basesyntax.HibernateUtil.getSessionFactory;

public class MessageDetailsDaoImpl extends AbstractDao implements MessageDetailsDao {
    public MessageDetailsDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public MessageDetails create(MessageDetails entity) {
        Transaction transaction = null;
        try (Session session = getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert MessageDetails entity: " + entity, e);
        }
    }

    @Override
    public MessageDetails get(Long id) {
        try (Session session = getSessionFactory().openSession()) {
            return session.get(MessageDetails.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Can't get MessageDetails by id: " + id, e);
        }
    }
}
