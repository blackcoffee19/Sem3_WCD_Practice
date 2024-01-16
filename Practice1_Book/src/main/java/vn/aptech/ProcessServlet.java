package vn.aptech;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.aptech.dao.BookDao;
import vn.aptech.entity.Book;

import java.io.IOException;

/**
 * Servlet implementation class ProcessServlet
 */
public class ProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BookDao bDao = new BookDao(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String a = request.getParameter("a");
		HttpSession session = request.getSession();
		if(a==null || a.isEmpty()) {
			request.getRequestDispatcher("/book/menu.jsp").forward(request, response);
		}else {
			switch (a) {
			case "DisplayList":{
				request.setAttribute("books", bDao.findAll());
				request.getRequestDispatcher("/book/index.jsp").forward(request, response);
				break;
			}
			case "DisplayCreate": {
				request.getRequestDispatcher("/book/create.jsp").forward(request, response);
				break;
			}
			case "DisplayUpdate": {
				String code = request.getParameter("b");
				
				Book book = bDao.findByCode(code);
				if(book.getBookCode()!=null) {
					request.setAttribute("book", book);
					request.getRequestDispatcher("/book/update.jsp").forward(request, response);
				}else {
					response.sendRedirect("ProcessServlet");
					return;
				}
				break;
			}
			default:
				request.getRequestDispatcher("/book/menu.jsp").forward(request, response);
				break;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String a = request.getParameter("a");
		HttpSession session = request.getSession();
		if(a==null || a.isEmpty()) {
			response.sendRedirect("ProcessServlet");
			return ;
		}else {
			switch (a) {
			case "Create": {
				String bookCode = request.getParameter("code");
				String title= request.getParameter("title");
				String priceS = request.getParameter("price");
				String publisher = request.getParameter("publisher");
				int price =0 ;
				try {
					price = Integer.parseInt(priceS);
					if(price <0) {
						session.setAttribute("error", "Price Must be greater than 0");
						request.getRequestDispatcher("/book/create.jsp").forward(request, response);
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
					session.setAttribute("error", "Price Must be a number");
					request.getRequestDispatcher("/book/create.jsp").forward(request, response);
					return;
				}
				Book bk= new Book();
				bk.setBookCode(bookCode);
				bk.setPrice(price);
				bk.setTitle(title);
				bk.setPublisher(publisher);
				if(bDao.create(bk)) {
					session.setAttribute("msg", "Create Successfully");
					response.sendRedirect("ProcessServlet?a=DisplayList");
					return;
				}else {
					session.setAttribute("error", "Error Create new Book Failure. Check if Book Code exist");
					request.getRequestDispatcher("/book/create.jsp").forward(request, response);
					return;
				}
				
			}
			case "Update": {
				String code = request.getParameter("code");
				String title= request.getParameter("title");
				String priceS = request.getParameter("price");
				String publisher = request.getParameter("publisher");
				int price =0;
				try {
					price = Integer.parseInt(priceS);
					if(price<0) {
						session.setAttribute("error", "Price Must be greater than 0");
						response.sendRedirect("ProcessServlet?a=DisplayList");
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
					session.setAttribute("error", "Price Must be a number");
					response.sendRedirect("ProcessServlet?a=DisplayList");
					return;
				}
				
				Book bk= new Book();
				bk.setBookCode(code);
				bk.setTitle(title);
				bk.setPrice(price);
				bk.setPublisher(publisher);
				if(bDao.update(bk)) {
					session.setAttribute("msg", "Update Successfully");
					response.sendRedirect("ProcessServlet?a=DisplayList");
					return;
				}else {
					session.setAttribute("error", "Error Update Book Failure");
					response.sendRedirect("ProcessServlet?a=DisplayList");
					return;
				}
			}
			case "Delete": {
				String code = request.getParameter("code");
				if(bDao.delete(code)) {
					session.setAttribute("msg", "Delete Successfully");
				}else {
					session.setAttribute("error", "Error Delete Book Failure");				}
				response.sendRedirect("ProcessServlet?a=DisplayList");
				return;
			}
			default:
				response.sendRedirect("ProcessServlet");
				return;
			}
		}
	}

}
