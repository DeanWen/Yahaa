package controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import model.Flickr;
import model.Model;

import org.genericdao.RollbackException;
import org.mybeans.form.FormBeanException;
import org.mybeans.form.FormBeanFactory;
import org.scribe.model.Token;
import org.xml.sax.SAXException;

import DAO.LikeDAO;
import DAO.TagDAO;
import DAO.UserDAO;
import databeans.LikeBean;
import databeans.TagBean;
import databeans.UserBean;
import formbeans.LikeFlickrForm;

public class RankAction extends Action {

	private UserDAO userDAO;
	private UserBean[] list;
	
	public RankAction(Model model) {
		userDAO = model.getUserDAO();
	}


	public String getName() { return "rank.do"; }

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
