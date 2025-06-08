package com.toiukha.comment.model;

import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.toiukha.hibernate.HibernateUtil;

public class CommentDAO implements CommentDAOInterface {
	
	private SessionFactory factory;
	
	public CommentDAO() {
		factory = HibernateUtil.getSessionFactory();
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void insert(CommentVO commentVO) {
		getSession().persist(commentVO);
	}

	@Override
	public void updateComm(Integer commId, String commCon, String[] commImg) {
		getSession().createMutationQuery("UPDATE CommentVO as cVO SET cVO.commCon = :commCon, cVO.commImg = :commImg WHERE cVO.commId = :commId")
			.setParameter("commId", commId)
			.setParameter("commCon", commCon)
			.setParameter("commImg", commImg)
			.executeUpdate();
	}

	@Override
	public void updateCommSta(Integer commId, Byte commSta) {
		getSession().createMutationQuery("UPDATE CommentVO as cVO SET cVO.commSta = :commSta WHERE cVO.commId = :commId")
		.setParameter("commId", commId)
		.setParameter("commSta", commSta)
		.executeUpdate();
	}

	@Override
	public CommentVO getOneComm(Integer commId) {
		return getSession().find(CommentVO.class, commId);
	}
	
	@Override
	public List<CommentVO> getArtComms (Integer commArt) {
		return getSession().createQuery("FROM CommentVO WHERE commArt = :commArt", CommentVO.class)
				.setParameter("commArt", commArt)
				.getResultList();
	}

	@Override
	public List<CommentVO> getCompositeQuery(Map<String, String> map) {
		return null;
	}

	
}
