package controls;

import java.util.Map;

import DAO.GuestboardDao;
import bind.DataBinding;
import vo.Guestboard;

public class GuestboardUpdateController implements Controller, DataBinding {
	GuestboardDao guestboardDao;

	@Override
	public Object[] getDataBinders() {
		return new Object[] {"id", Integer.class, "guestboard", vo.Guestboard.class};
	}

	public GuestboardUpdateController setGuestboardDao(GuestboardDao guestboardDao) {
		this.guestboardDao = guestboardDao;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Guestboard guestboard = (Guestboard)model.get("guestboard");

		if(guestboard.getEmail() == null) {
			
			model.put("guestboard", guestboardDao.selectOne((Integer)model.get("id")));
			
			return "/guestboard/GuestboardUpdate.jsp";
		} else {
			guestboardDao.update(guestboard);
				
			return "redirect:list.do";
		}
	}
	}