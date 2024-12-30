package controls;

import java.util.Map;

import DAO.GuestboardDao;
import bind.DataBinding;
import vo.Guestboard;

public class GuestboardAddController implements Controller, DataBinding {
	GuestboardDao guestboardDao;

	@Override
	public Object[] getDataBinders() {
		return new Object[] {"guestboard", vo.Guestboard.class};
	}

	public GuestboardAddController setGuestboardDao(GuestboardDao guestboardDao) {
		this.guestboardDao = guestboardDao;
		return this;
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Guestboard guestboard = (Guestboard)model.get("guestboard");
		
		if(guestboard.getEmail()==null) {
			
			return "/guestboard/GuestboardInsert.jsp";
		}else {
			guestboardDao.insert(guestboard);
			return "redirect:list.do";
		}
	}

}
