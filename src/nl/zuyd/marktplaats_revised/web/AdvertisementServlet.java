package nl.zuyd.marktplaats_revised.web;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.zuyd.marktplaats_revised.Advertisement;
import nl.zuyd.marktplaats_revised.AdvertisementRepository;
import nl.zuyd.marktplaats_revised.DataRepository;
import nl.zuyd.marktplaats_revised.User;

/**
 * Servlet implementation class AdvertisementServlet
 */
@WebServlet("/advertisements")
public class AdvertisementServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	AdvertisementRepository advertRepo;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdvertisementServlet()
	{	
		super();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{		
		List<Advertisement> l = advertRepo.getAll();
		
		// check for query params
		String s;
		if ((s = request.getParameter("id")) != null)
		{
			Advertisement a = null;
			for (Advertisement advertisement : l)
			{
				if (advertisement.getId() == Integer.parseInt(s))
					a = advertisement;
			}
			request.setAttribute("Advertisement", a);
			if (a.getAdvertiser().getId() == 1)
			{
				this.getServletContext()
						.getRequestDispatcher("/EditAdvertisement.jsp")
						.forward(request, response);
			}
			else
			{
				this.getServletContext()
						.getRequestDispatcher("/SingleAdvertisement.jsp")
						.forward(request, response);
			}
		}
		else
		{
			
			// else list all advertisements and forward to the
			// ListAdvertisements.jsp to display all advertisements
			request.setAttribute("Advertisements", l);
			this.getServletContext()
					.getRequestDispatcher("/ListAdvertisements.jsp")
					.forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		List<Advertisement> l = this.advertRepo.getAll();
		
		String p;
		if ((p = request.getParameter("delete_id")) != null)
		{
			// del
			response.getWriter().write(p);
		}
		else if ((p = request.getParameter("sold_id")) != null)
		{
			response.getWriter().write(p);
		}
		else if ((p = request.getParameter("save_id")) != null)
		{
			Advertisement a = null;
			//GET BY ID
			for (Advertisement advertisement : l) {
				if (advertisement.getId()==Integer.parseInt(p))
				{
					a=advertisement;
				}
			}
			
			if (a != null){
				//TODO
			}
		}
		else
		{
			super.doPost(request, response);
		}
	}
}
