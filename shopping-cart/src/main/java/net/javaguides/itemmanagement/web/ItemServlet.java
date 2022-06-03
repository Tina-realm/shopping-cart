package net.javaguides.itemmanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.javaguides.itemmanagement.dao.ItemDao;
import net.javaguides.itemmanagement.model.Item;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 */

@WebServlet("/")
public class ItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemDao itemDao;
	
	public void init() {
		itemDao = new ItemDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertItem(request, response);
				break;
			case "/delete":
				deleteItem(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateItem(request, response);
				break;
			case "/checkout":
			    checkoutItem(request, response);
			    break;
			default:
				listItem(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void checkoutItem(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		List<Item> listItem = itemDao.getAllItem();
		int subtotal=0;
		for (Item item:listItem) {
			subtotal+=item.getPrice()*item.getQuantity();
		}
		request.setAttribute("subtotal", subtotal);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("check-out.jsp");
		dispatcher.forward(request, response);
		// TODO Auto-generated method stub
		
	}

	private void listItem(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Item> listItem = itemDao.getAllItem();
		request.setAttribute("listItem", listItem);
		RequestDispatcher dispatcher = request.getRequestDispatcher("item-list.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("item-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Item existingItem = itemDao.getItem(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("item-form.jsp");
		request.setAttribute("Item", existingItem);
		dispatcher.forward(request, response);

	}

	private void insertItem(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		Item newItem = new Item(name, price, quantity);
		itemDao.saveItem(newItem);
		response.sendRedirect("list");
	}

	private void updateItem(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));

		Item item = new Item(id, name, price, quantity);
		itemDao.updateItem(item);
		response.sendRedirect("list");
	}

	private void deleteItem(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		itemDao.deleteItem(id);
		response.sendRedirect("list");
	}
}
