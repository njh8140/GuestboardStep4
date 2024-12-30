package controls;

import java.util.Map;

import DAO.GuestboardDao;

public class GuestboardListController implements Controller{
	GuestboardDao guestboardDao;
	
	public GuestboardListController setGuestboardDao(GuestboardDao guestboardDao) {
		this.guestboardDao = guestboardDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		model.put("guestboards", guestboardDao.selectAll());
		return "/guestboard/GuestboardList.jsp";
	}
}