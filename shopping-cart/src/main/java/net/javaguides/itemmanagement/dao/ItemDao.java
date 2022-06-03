package net.javaguides.itemmanagement.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import net.javaguides.itemmanagement.model.Item;
import net.javaguides.itemmanagement.utl.HibernateUtil;

/**
 * CRUD database operations
 *
 */
public class ItemDao {
	
	/**
	 * Save Item
	 * @param item
	 */
	public void saveItem(Item item) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.save(item);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Update item
	 * @param item
	 */
	public void updateItem(Item item) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save the student object
			session.update(item);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Delete item
	 * @param id
	 */
	public void deleteItem(int id) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a user object
			Item item = session.get(Item.class, id);
			if (item != null) {
				session.delete(item);
				System.out.println("item is deleted");
			}

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	/**
	 * Get item By ID
	 * @param id
	 * @return
	 */
	public Item getItem(int id) {

		Transaction transaction = null;
		Item item = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an item object
			item = session.get(Item.class, id);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return item;
	}
	
	/**
	 * Get all items
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Item> getAllItem() {

		Transaction transaction = null;
		List<Item> listOfItem = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			
			listOfItem = session.createQuery("from Item").getResultList();
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfItem;
	}
}
