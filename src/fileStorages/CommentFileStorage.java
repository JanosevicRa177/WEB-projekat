package fileStorages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import enums.CommentStatus;
import model.Comment;

public class CommentFileStorage {
	
	public Map<String, Comment> comments;
	
	public CommentFileStorage() {
	}
	
	public String AddComment(String customer,String sportBuilding,String commentConent,int grade) {
		comments = readComments();
		Comment comment = new Comment(customer,sportBuilding,commentConent,grade,comments.size(),CommentStatus.NotEvaluated);
		comments.put(Integer.toString(comment.getId()),comment);
		writeComments();
		return "Success";
	}
	public Collection<Comment> GetCommentsBySportBuildingForEveryone(String sportBuilding) {
		comments = readComments();
		Collection<Comment> sportBuildingComments = new HashSet<Comment>();
		for(Comment comment : comments.values()) {
			if(comment.getSportBuilding().equals(sportBuilding) && (comment.getStatus() == CommentStatus.Approved)) {
				sportBuildingComments.add(comment);
			}
		}
		return sportBuildingComments;
	}
	public Collection<Comment> GetCommentsBySportBuildingForAdminAndManager(String sportBuilding) {
		comments = readComments();
		Collection<Comment> sportBuildingComments = new HashSet<Comment>();
		for(Comment comment : comments.values()) {
			if(comment.getSportBuilding().equals(sportBuilding)) {
				sportBuildingComments.add(comment);
			}
		}
		return sportBuildingComments;
	}
	public String ApproveComment(String id) {
		comments = readComments();
		Comment commentToApprove = comments.get(id);
		comments.remove(id);
		commentToApprove.setStatus(CommentStatus.Approved);
		comments.put(id,commentToApprove);
		writeComments();
		return "Success";
	}
	public String DenyComment(String id) {
		comments = readComments();
		Comment commentToDeny = comments.get(id);
		comments.remove(id);
		commentToDeny.setStatus(CommentStatus.Denied);
		comments.put(id,commentToDeny);
		writeComments();
		return "Success";
	}
	public Boolean DidCustomerCommentSportBuilding(String customer, String sportBuilding) {
		comments = readComments();
		for(Comment comment : comments.values()) {
			if(comment.getSportBuilding().equals(sportBuilding) && comment.getCustomer().equals(customer)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean writeComments() 
	{
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter("comments.txt");
			PrintWriter output = new PrintWriter(fileWriter, true);
			for(String key : comments.keySet())
			{
				Comment comment = comments.get(key);
				String outputString = "";
				outputString += comment.getId() + ";";
				outputString += comment.getCustomer() + ";";
				outputString += comment.getSportBuilding() + ";";
				outputString += comment.getComment() + ";";
				outputString += comment.getGrade() + ";";
				outputString += comment.getStatus() + ";";
				output.println(outputString);
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public Map<String, Comment> readComments() {
		Map<String, Comment> commentsInner = new TreeMap<String, Comment>(String.CASE_INSENSITIVE_ORDER);
		BufferedReader in = null;
		try {
			File file = new File("comments.txt");
			in = new BufferedReader(new FileReader(file));
			String line,id = "",customer = "", sportBuilding = "", commentContent = "", grade = "", status = "";
			StringTokenizer st;
			try {
				while ((line = in.readLine()) != null) {
					line = line.trim();
					if (line.equals("") || line.indexOf('#') == 0)
						continue;
					st = new StringTokenizer(line, ";");
					while (st.hasMoreTokens()) {
						id = st.nextToken().trim();
						customer = st.nextToken().trim();
						sportBuilding = st.nextToken().trim();
						commentContent = st.nextToken().trim();
						grade = st.nextToken().trim();
						status = st.nextToken().trim();
						
					}
					CommentStatus commentStatus;
					if(status.equals("Approved")) commentStatus = CommentStatus.Approved;
					else if(status.equals("Denied")) commentStatus = CommentStatus.Denied;
					else commentStatus = CommentStatus.NotEvaluated;
					Comment comment = new Comment(customer,sportBuilding,commentContent,Integer.parseInt(grade),Integer.parseInt(id),commentStatus);
					commentsInner.put(id,comment);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if ( in != null ) {
				try {
					in.close();
				}
				catch (Exception e) { }
			}
		}
		return commentsInner ;
	}
}
