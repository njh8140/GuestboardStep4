package controls;

import java.util.Map;

import DAO.GuestboardDao;
import bind.DataBinding;

public class GuestboardDeleteController implements Controller, DataBinding {
	GuestboardDao guestboardDao;

	@Override
	public Object[] getDataBinders() {
		return new Object[] {"id", Integer.class};
	}

	public GuestboardDeleteController setGuestboardDao(GuestboardDao guestboardDao) {
		this.guestboardDao = guestboardDao;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		guestboardDao.delete((Integer)model.get("id"));
		
		return "redirect:list.do";
	}

}
