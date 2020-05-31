package com.coke.lab3_hibernate;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class TestSystem {

	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("lab3_hibernate");

	public static void main(String[] args) {
		execute();
		ENTITY_MANAGER_FACTORY.close();
	}

	// ---------------------------------------------------------------------------------------------
	public static void addContract(int id, String customer, int date_start, int date_end, double price) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;

		try {
			et = em.getTransaction();
			et.begin();
			Customer cust = new Customer();
			cust.setID(id);
			cust.setCustomer(customer);
			cust.setDate_start(date_start);
			cust.setDate_end(date_end);
			cust.setPrice(price);
			em.persist(cust);
			et.commit();
		} catch (Exception ex) {
			if (et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}
	//get the table data
	// ---------------------------------------------------------------------------------------------
	public static void getContract(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String query = "SELECT c FROM Customer c WHERE c.id = :id";

		TypedQuery<Customer> tq = em.createQuery(query, Customer.class);
		tq.setParameter("id", id);
		Customer cust = null;
		try {
			cust = tq.getSingleResult();
			System.out.println(cust.getID() + " " + cust.getCustomer());
		} catch (NoResultException ex) {
			ex.printStackTrace();
		} finally {
			em.close();
		}

	}

	public static void getContracts() {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		String strQuery = "SELECT c FROM Customer c WHERE c.id IS NOT NULL";
		TypedQuery<Customer> tq = em.createQuery(strQuery, Customer.class);
		List<Customer> custs;

		try {
			custs = tq.getResultList();
			System.out.format("%-3s|%-20s|%-10s|%-10s|%-10s", "ID", "customer", "date_start", "date_end", "price");
			System.out.println("\n+--+--------------------+----------+----------+----------");
			custs.forEach(cust -> System.out.format("%-3d|%-20s|%-10d|%-10d|%-10f\n", cust.getID(), cust.getCustomer(),
					cust.getDate_start(), cust.getDate_end(), cust.getPrice()));
		} catch (NoResultException ex) {
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}
	//change data
	// ---------------------------------------------------------------------------------------------
	public static void changeCustomer(int id, String customer) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		Customer cust = null;
		try {
			et = em.getTransaction();
			et.begin();
			cust = em.find(Customer.class, id);
			cust.setCustomer(customer);

			em.persist(cust);
			et.commit();
		} catch (Exception ex) {
			if (et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static void changeDate_start(int id, int date_start) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		Customer cust = null;
		try {
			et = em.getTransaction();
			et.begin();
			cust = em.find(Customer.class, id);
			cust.setDate_start(date_start);

			em.persist(cust);
			et.commit();
		} catch (Exception ex) {
			if (et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static void changeDate_end(int id, int date_end) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		Customer cust = null;
		try {
			et = em.getTransaction();
			et.begin();
			cust = em.find(Customer.class, id);
			cust.setDate_end(date_end);

			em.persist(cust);
			et.commit();
		} catch (Exception ex) {
			if (et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}

	public static void changePrice(int id, double price) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		Customer cust = null;
		try {
			et = em.getTransaction();
			et.begin();
			cust = em.find(Customer.class, id);
			cust.setPrice(price);

			em.persist(cust);
			et.commit();
		} catch (Exception ex) {
			if (et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}
	//deletion
	// ---------------------------------------------------------------------------------------------
	public static void deleteContract(int id) {
		EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction et = null;
		Customer cust = null;
		try {
			et = em.getTransaction();
			et.begin();
			cust = em.find(Customer.class, id);
			em.remove(cust);

			// em.persist(cust);
			et.commit();
		} catch (Exception ex) {
			if (et != null) {
				et.rollback();
			}
			ex.printStackTrace();
		} finally {
			em.close();
		}
	}
	//CRUD operations
	// ---------------------------------------------------------------------------------------------
	public static void add(Scanner scan) {
		System.out
				.println("Please enter ID, customer, date_start, date_end, price separating each value with a space: ");
		String input = scan.nextLine();
		String[] inputs = input.split(" ");
		addContract(Integer.parseInt(inputs[0]), inputs[1], Integer.parseInt(inputs[2]), Integer.parseInt(inputs[3]),
				Double.parseDouble(inputs[4]));
	}

	public static void edit(Scanner scan) {
		System.out.println("Please enter ID of the row you want to edit: ");
		int id = scan.nextInt();
		scan.nextLine();
		System.out
				.println("Please enter new customer, date_start, date_end, price separating each value with a space: ");
		String input = scan.nextLine();
		String[] inputs = input.split(" ");
		changeCustomer(id, inputs[0]);
		changeDate_start(id, Integer.parseInt(inputs[1]));
		changeDate_end(id, Integer.parseInt(inputs[2]));
		changePrice(id, Double.parseDouble(inputs[3]));

	}

	public static void delete(Scanner scan) {
		System.out.println("Please enter ID of the row you want to delete: ");
		int id = scan.nextInt();
		deleteContract(id);
	}
	//console interface 
	// ---------------------------------------------------------------------------------------------
	public static void execute() {

		changeDate_start(2, 666);
		changeDate_end(2, 666);
		changePrice(2, 666);
		getContracts();

		Scanner scan = new Scanner(System.in);
		boolean exit = false;

		while (!exit) {
			System.out.println("Press: add - 1, edit - 2, delete - 3, exit - 4:");
			int n = scan.nextInt();
			scan.nextLine();
			switch (n) {
			case 1:
				add(scan);
				break;
			case 2:
				edit(scan);
				break;
			case 3:
				delete(scan);
				break;
			case 4:
				exit = true;
				System.exit(0);
				;
			}
			getContracts();
		}
	}
}
