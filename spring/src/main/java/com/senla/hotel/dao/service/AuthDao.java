package com.senla.hotel.dao.service;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.senla.hotel.dao.api.IAuthDao;
import com.senla.hotel.model.User;
import com.senla.hotel.util.Constant;

@Repository
public class AuthDao extends AbstractDao<User, Integer> implements IAuthDao {

	public AuthDao() {
		super(User.class);
	}

	
	public User getUser(String name, String pass){
		Criteria criteria = createCriteria();
		Criterion n = Restrictions.eq(Constant.NAME, name);
		Criterion s = Restrictions.eq(Constant.PASS, pass);
		LogicalExpression orExp = Restrictions.and(s, n);
		criteria.add(orExp);
		return (User) criteria.uniqueResult();
		

	}


	

}
