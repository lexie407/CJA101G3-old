package com.toiukha.comment.model;

import java.util.List;
import java.util.Map;

import com.toiukha.notification.model.NotificationVO;

public interface CommentDAOInterface {

	public void insert (CommentVO commentVO);
	public void updateComm (Integer commId, String commCon, String[] commImg);
	public void updateCommSta (Integer commId, Byte commSta);
	public CommentVO getOneComm(Integer commId);
	public List<CommentVO> getArtComms (Integer commArt);
	public List<CommentVO> getCompositeQuery(Map<String, String> map);
	
}
