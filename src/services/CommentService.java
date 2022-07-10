package services;

import java.util.Collection;

import fileStorages.CommentFileStorage;
import model.Comment;

public class CommentService {
	
	public static CommentFileStorage commentFileStorage;
	
	public CommentService() {
		commentFileStorage = new CommentFileStorage();
	}
	
	public Boolean DidCustomerCommentSportBuilding(String customer,String sportBuilding) {
		return commentFileStorage.DidCustomerCommentSportBuilding(customer,sportBuilding);
	}
	public Collection<Comment> GetCommentsBySportBuildingForEveryone(String sportBuilding){
		return commentFileStorage.GetCommentsBySportBuildingForEveryone(sportBuilding);
	}
	public Collection<Comment> GetCommentsBySportBuildingForAdminAndManager(String sportBuilding){
		return commentFileStorage.GetCommentsBySportBuildingForAdminAndManager(sportBuilding);
	}
	public String ApproveComment(String id) {
		return commentFileStorage.ApproveComment(id);
	}
	public String DenyComment(String id) {
		return commentFileStorage.DenyComment(id);
	}
	public String AddComment(String customer,String sportBuilding,String commentConent,int grade) {
		return commentFileStorage.AddComment(customer,sportBuilding,commentConent,grade);
	}
}
