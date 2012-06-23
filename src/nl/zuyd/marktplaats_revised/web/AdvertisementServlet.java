package nl.zuyd.marktplaats_revised.web;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.zuyd.marktplaats_revised.entities.Advertisement;
import nl.zuyd.marktplaats_revised.repositories.IAdvertisementRepository;
import nl.zuyd.marktplaats_revised.repositories.IUserRepository;

/**
 * Servlet implementation class AdvertisementServlet
 */
@WebServlet("/advertisements")  
public class AdvertisementServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	IAdvertisementRepository advertRepo;
	@EJB
	IUserRepository userRepo;
	
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
			Advertisement advert = this.advertRepo.getById(Integer.parseInt(s));
			request.setAttribute("Advertisement", advert);
						
			if (advert == null) {
				request.setAttribute("Advertisements", advertRepo.getAll());
				
				this.getServletContext()
						.getRequestDispatcher("/ListAdvertisements.jsp")
						.forward(request, response);
			}
			
			// or just check if the owner == the current user
			if (advert.getAdvertiser().getId() == userRepo.getById(request.getUserPrincipal().getName()).getId())
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
		//List<Advertisement> l = advertRepo.getAll();
		String p;
		if ((p = request.getParameter("delete_id")) != null)
		{
			// del
			Advertisement advertToDelete = this.advertRepo.getById(Integer
					.parseInt(p));

			if (advertToDelete.getAdvertiser().getUsername().equals(request.getUserPrincipal().getName()))
			{
				response.getWriter().write(
						"Advert with title " + advertToDelete.getTitle()
								+ " is going to be deleted");
				
				// TODO: redirect to /advertisements
				advertRepo.deleteAdvertisement(advertToDelete);
			}
		}
		else if ((p = request.getParameter("sold_id")) != null)
		{
			int id = Integer.parseInt(p);
			
			Advertisement soldAdvert = this.advertRepo.getById(id);
			
			if (soldAdvert != null) {
				soldAdvert.setStatus(1); // 0 = unsold, 1 = sold
				this.advertRepo.saveAdvertisement(soldAdvert);
			}
			
			// TODO: redirect to /advertisements
		}
		else if ((p = request.getParameter("save_id")) != null)
		{			
			Advertisement advertToUpdate = this.advertRepo.getById(Integer
					.parseInt(p));
			
			if (advertToUpdate != null){
				//TODO SAVE THE UPDATED ADVERT
				
				String title = request.getParameter("Title");
				String description = request.getParameter("Description");
				String price = request.getParameter("Price");
				
				advertToUpdate.setTitle(title);
				advertToUpdate.setDescription(description);
				advertToUpdate.setPrice(price);
				
				this.advertRepo.saveAdvertisement(advertToUpdate);
				
				// TODO: redirect to /advertisements
			}
		}
		else
		{
			doGet(request, response);
		}
	}
}
