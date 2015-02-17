package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import databeans.UserBean;
import DAO.LikeDAO;
import model.Model;



public class LikeTimeChart extends Action {

	private LikeDAO likeDAO;
	
	public LikeTimeChart(Model model) {
		likeDAO = model.getLikeDAO();
	}
	
	public String getName() {
		return "likeTimeChart.do";
	}
	
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		UserBean user = (UserBean) session.getAttribute("user");
		
		String twitterId = user.getTwitterId();
		ArrayList<String> likeRec = likeDAO.getTimeListByUserId(twitterId);
		ArrayList<Integer> countList = new ArrayList<Integer>();
		ArrayList<String> time = new ArrayList<String>();
		for (int i = 0; i < 24; i++) {
			time.add(String.valueOf(i));
			int count = 0;
			for (String s : likeRec) {
				if (s.equals(String.valueOf(i))) {
					count++;
				}
			}
			countList.add(count);
		}
		request.setAttribute("time",time);
		request.setAttribute("countList", countList);

		return "likeTimeChart.jsp";
	}
}

