package controls;

import java.util.Map;

import DAO.GuestboardDao;
import bind.DataBinding;

public class GuestboardSearchController implements Controller, DataBinding {
	GuestboardDao guestboardDao;

	@Override
	public Object[] getDataBinders() {
		return new Object[] {"keyword", String.class};
	}

	public GuestboardSearchController setGuestboardDao(GuestboardDao guestboardDao) {
		this.guestboardDao = guestboardDao;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		String keyword = (String)model.get("keyword");
		model.put("guestboards", guestboardDao.searchName(keyword));
		
		return "/guestboard/GuestboardList.jsp";
		}
	}