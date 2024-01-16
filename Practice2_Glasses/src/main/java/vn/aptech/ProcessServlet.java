package vn.aptech;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.aptech.dao.GlassDao;
import vn.aptech.entity.Glass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Servlet implementation class ProcessServlet
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024,maxFileSize = 5 * 1024 * 1024,maxRequestSize = 5*5*1024*1024)
public class ProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public GlassDao gDao = new GlassDao();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 50 * 1024;
    private int maxMemSize = 4 * 1024;
    private File file ;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String a = request.getParameter("a");
		HttpSession session = request.getSession();
		if(a==null || a.isEmpty()) {
			request.setAttribute("glasses", gDao.findAll());
			request.getRequestDispatcher("/glass/index.jsp").forward(request, response);
		}else {
			switch (a) {
			case "DisplayCreate": {
				request.getRequestDispatcher("/glass/createGlass.jsp").forward(request, response);
				break;
			}
			
			case "DisplayUpdate": {
				String idS = request.getParameter("b");
				int id = 0;
				if(idS.isEmpty()) {
					response.sendRedirect("ProcessServlet");
					return;
				}
				try {
					id = Integer.parseInt(idS);
					
				}catch (Exception e) {
					e.printStackTrace();
					session.setAttribute("error", "Cannot found Glass");
					response.sendRedirect("ProcessServlet");
					return;
				}
				Glass glass = gDao.findById(id);
				if(glass.getGlassId()>0) {
					request.setAttribute("glass", glass);
					request.getRequestDispatcher("/glass/updateGlass.jsp").forward(request, response);
				}else {
					response.sendRedirect("ProcessServlet");
					return;
				}
				break;
			}
			default:
				request.setAttribute("glasses", gDao.findAll());
				request.getRequestDispatcher("/glass/index.jsp").forward(request, response);
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
				String name= request.getParameter("name");
				String priceS = request.getParameter("price");
				int price =0 ;
				try {
					price = Integer.parseInt(priceS);
					if(price<0) {
						session.setAttribute("error", "Price Glass must greater than 0");
						request.getRequestDispatcher("/glass/createGlass.jsp").forward(request, response);
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
					session.setAttribute("error", "Price must be a number");
					request.getRequestDispatcher("/glass/createGlass.jsp").forward(request, response);
					return;
				}
				Part part = request.getPart("photo");
				String filename = Path.of(part.getSubmittedFileName()).getFileName().toString();
				if (!filename.isEmpty()) {
					String imgFolderPath = request.getServletContext().getRealPath("/images");
					if(!Files.exists(Path.of(imgFolderPath))) {
						Files.createDirectory(Path.of(imgFolderPath));
					}
					part.write(imgFolderPath+"/"+filename);	
				}else {
					session.setAttribute("error", "Image is required");
					request.getRequestDispatcher("/glass/createGlass.jsp").forward(request, response);
					return;
				}
				Glass glass = new Glass();
				glass.setName(name);
				glass.setPrice(price);
				glass.setImage(filename);
				if(gDao.create(glass)) {
					session.setAttribute("msg", "Create Successfully");
					response.sendRedirect("ProcessServlet");
					return;
				}else {
					session.setAttribute("error", "Error Create new Glass Failure");
					request.getRequestDispatcher("/glass/createGlass.jsp").forward(request, response);
				}
				break;
			}
			case "Update": {
				String idS = request.getParameter("id");
				String name= request.getParameter("name");
				String priceS = request.getParameter("price");
				String image = request.getParameter("image");
				int price =0,id =0;
				
				try {
					id = Integer.parseInt(idS);
					price = Integer.parseInt(priceS);
					if(price<0) {
						session.setAttribute("error", "Error Update Failue. Price Glass must greater than 0");
						response.sendRedirect("ProcessServlet");
						return;
					}
				} catch (Exception e) {
					e.printStackTrace();
					session.setAttribute("error", "Error Update Failue");
					response.sendRedirect("ProcessServlet");
					return;
				}
				String filename = "";		
				Part part = request.getPart("photo");
				filename = Path.of(part.getSubmittedFileName()).getFileName().toString();
				if (!filename.isEmpty()) {
					try {
						String imgFolderPath = request.getServletContext().getRealPath("/images");

						if(!Files.exists(Path.of(imgFolderPath))) {
							Files.createDirectory(Path.of(imgFolderPath));
						}
						part.write(imgFolderPath+"/"+filename);													
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						if(image!=null) {
							String imgPath = request.getServletContext().getRealPath("/images/"+image);
							File img= new File(imgPath);
							if( img.exists() ) {img.delete();}							
						}
					}
				}
				Glass glass= new Glass();
				glass.setGlassId(id);
				glass.setName(name);
				glass.setPrice(price);
				glass.setImage(filename.isEmpty()?image:filename);
				if(gDao.update(glass)) {
					session.setAttribute("msg", "Update Successfully");
				}else {
					session.setAttribute("error", "Error Update Glass Failure");
				}
				response.sendRedirect("ProcessServlet");
				return;
			}
			case "Delete": {
				String idS = request.getParameter("id");
				String image= request.getParameter("image");
				int id =0;
				try {
					id = Integer.parseInt(idS);
				} catch (Exception e) {
					e.printStackTrace();
					session.setAttribute("error", "Error Delete Glass Failure");
					response.sendRedirect("ProcessServlet");
					return;
				}
				//Delete Image
				if(image!=null && !image.isEmpty()) {
					String imgFolderPath = request.getServletContext().getRealPath("/images/"+image);
					File img= new File(imgFolderPath);
					if( img.exists() ) {img.delete();}					
				}
				if(gDao.delete(id)) {
					session.setAttribute("msg", "Delete Successfully");
				}else {
					session.setAttribute("error", "Error Delete Glass Failure");
				}
				response.sendRedirect("ProcessServlet");
				return;
			}
			default:
				response.sendRedirect("ProcessServlet");
				return;
			}
		}
	}

}
