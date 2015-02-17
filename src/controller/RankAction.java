package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Model;

import DAO.UserDAO;
import databeans.UserBean;

public class RankAction extends Action {

	private UserDAO userDAO;
	private UserBean[] list;
	
	public RankAction(Model model) {
		userDAO = model.getUserDAO();
	}


	@Override
	public String getName() { return "rank.do"; }

	@Override
	public String perform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		list = userDAO.getAll();
		quickSort(0, list.length - 1);	
		for(int i = 0; i < list.length / 2; i++)
		{
		    UserBean temp = list[i];
		    list[i] = list[list.length - i - 1];
		    list[list.length - i - 1] = temp;
		}
		request.setAttribute("list", list);
		return "rank.jsp";
	}
	
    private void quickSort(int lowerIndex, int higherIndex) {        
        int i = lowerIndex;
        int j = higherIndex;
        int pivot = list[lowerIndex+(higherIndex-lowerIndex)/2].getLikeGiven();
        while (i <= j) {
            while (list[i].getLikeGiven() < pivot) {
                i++;
            }
            while (list[j].getLikeGiven() > pivot) {
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                i++;
                j--;
            }
        }
        if (lowerIndex < j)
            quickSort(lowerIndex, j);
        if (i < higherIndex)
            quickSort(i, higherIndex);
    }
    
    private void exchangeNumbers(int i, int j) {
        UserBean temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }

}
