package listeners;


import javax.naming.InitialContext;
import javax.sql.DataSource;

import DAO.GuestboardDao;
import controls.GuestboardAddController;
import controls.GuestboardDeleteController;
import controls.GuestboardListController;
import controls.GuestboardSearchController;
import controls.GuestboardUpdateController;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
	
    public void contextInitialized(ServletContextEvent sce)  { 
    	System.out.println("Listener init();");
    	try{
			ServletContext sc = sce.getServletContext();
			
			//Server 자원을 사용하는 방법
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource)ic.lookup("java:comp/env/jdbc/guestboard"); 
			
			GuestboardDao guestboardDao = new GuestboardDao();
			guestboardDao.setDataSource(ds);
			//sc.setAttribute("guestboardDao", guestboardDao);
			sc.setAttribute("/guestboard/list.do", new GuestboardListController().setGuestboardDao(guestboardDao));
			sc.setAttribute("/guestboard/update.do", new GuestboardUpdateController().setGuestboardDao(guestboardDao));
			sc.setAttribute("/guestboard/add.do", new GuestboardAddController().setGuestboardDao(guestboardDao));
			sc.setAttribute("/guestboard/delete.do", new GuestboardDeleteController().setGuestboardDao(guestboardDao));
			sc.setAttribute("/guestboard/search.do", new GuestboardSearchController().setGuestboardDao(guestboardDao));
			
		} catch(Exception e){
			e.printStackTrace();
		}
    }

    public void contextDestroyed(ServletContextEvent sce)  { 
    	System.out.println("Listener destroy();");
    	
    }
}