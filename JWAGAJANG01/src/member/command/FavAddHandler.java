package member.command;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.command.CommandHandler;
import member.model.Fav;
import member.service.FavService;

public class FavAddHandler implements CommandHandler {
	
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Integer mdcode = Integer.parseInt(req.getParameter("mdcode"));
		String userid = req.getParameter("userid");
		
		
		Fav fav = new Fav(mdcode,userid);
		
		FavService fs = FavService.getInstance();
		
		Integer md_code = fs.checkFavDup(userid, mdcode);
		
		if(md_code == null) {
			fs.insert(fav);	
		}
		
		
		return "goods.do?md_code="+mdcode;
	}
}
