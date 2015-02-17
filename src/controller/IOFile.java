package controller;

import databeans.TagBean;
import DAO.TagDAO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import model.Model;

public class IOFile {
	private TagDAO tagDAO;
	public IOFile(Model model) {
		tagDAO = model.getTagDAO();
	}
	
	public void readData() throws URISyntaxException {
		TagBean[] all = tagDAO.getAll();
		
		TreeMap<Integer, String> tmap = new TreeMap<Integer, String>();
		for (int i = 0; i < all.length; i++) {
			tmap.put(all[i].getCount(), all[i].getTag());
		}
		

		try {
			File file = new File("./" + "tags.txt");
			System.out.println(file.getCanonicalPath());
			
			FileWriter outputPathFileWriter = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(outputPathFileWriter);
			LinkedList<String> list = new LinkedList<String>();
			Iterator<Integer> iter = tmap.keySet().iterator();
			while (iter.hasNext()) {
				list.addFirst(tmap.get(iter.next()));
			}
			
			for (int i = 0; i < list.size(); i++) {
				writer.write(list.get(i) + "\t");
			}
			
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
