package controller;

import databeans.TagBean;
import DAO.TagDAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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
			File file = new File("tag.csv");
			System.out.println(file.getCanonicalPath());
			FileWriter outputPathFileWriter = new FileWriter(file);
			BufferedWriter writer = new BufferedWriter(outputPathFileWriter);
			LinkedList<String> list = new LinkedList<String>();
			Iterator<Integer> iter = tmap.keySet().iterator();
			int rank = tmap.keySet().size();
			while (iter.hasNext()) {
				list.addFirst(rank + "," + tmap.get(iter.next()) + "," + iter.next());
				rank--;
			}
			list.addFirst("rank,tag,count");
			for (int i = 0; i < list.size(); i++) {
				writer.write(list.get(i));
				writer.newLine();
			}
			writer.close();
			
			/*Read File*/
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("tags.txt")));
			String nextline;
			while ((nextline = br.readLine()) != null) {
				System.out.println(nextline);// fastest the way to read and write
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
