package servlets;

import java.io.IOException;
import java.util.HashMap;

import bind.DataBinding;
import bind.ServletRequestDataBinder;
import controls.Controller;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servletPath = request.getServletPath();
		System.out.println("servletPath :"+ servletPath);
		
		try {
			ServletContext sc = this.getServletContext();
			HashMap<String, Object> model = new HashMap<String, Object>();
			model.put("session", request.getSession());
			Controller pageController = (Controller)sc.getAttribute(servletPath);
			
			if(pageController instanceof DataBinding) {
				prepareRequestData(request, model, (DataBinding)pageController);
			}
			
			String viewUrl = pageController.execute(model);
			  
			System.out.println("viewUrl : "+viewUrl);
			  
			for(String key: model.keySet()) { 
				System.out.println("key***"+key);
			  
				request.setAttribute(key, model.get(key)); 
				}
			  
			 	if(viewUrl.startsWith("redirect:")) { 
			 		String url = viewUrl.substring(9);
			 		System.out.println(url); 
			 		response.sendRedirect(url); 
			  } else {
				  	RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
				  	rd.include(request, response); 
			  }
			 		System.out.println("=========================");
		}catch(Exception e) {
					e.printStackTrace();
					request.setAttribute("error", e);
					RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
					rd.forward(request, response);
		}
	}
	private void prepareRequestData(HttpServletRequest request, HashMap<String, Object> model, DataBinding dataBinding)
			throws Exception {
				Object[] dataBinders = dataBinding.getDataBinders();
				String dataName = null;
				Class<?> dataType = null;
				Object dataObj = null;
					
			for (int i = 0; i < dataBinders.length; i+=2) {
				dataName = (String)dataBinders[i];
				dataType = (Class<?>) dataBinders[i+1];
				System.out.println("dataName : "+ dataName + "dataType : " + dataType);
				dataObj = ServletRequestDataBinder.bind(request, dataType, dataName);
				model.put(dataName, dataObj);
			}
	}
}