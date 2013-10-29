package epam.cdp.spring.dao.impl.util;

import static org.hibernate.criterion.Restrictions.eq;
import static org.hibernate.criterion.Restrictions.le;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import epam.cdp.spring.bean.TicketCategory;
import epam.cdp.spring.dao.TicketFilterCriteria;


public class TicketHibernateQueryBuilder {

	public Criteria build(TicketFilterCriteria filterCriteria, Session session, Class<?> entityClass, Criterion... criterion){
		Criteria criteria = session.createCriteria(entityClass);
		
		TicketCategory category = filterCriteria.getCategory();
		Date date = filterCriteria.getDate();
		String title  = filterCriteria.getTitle();
		
		for (Criterion cr: criterion){
			criteria.add(cr);
		}
		
		if (category != null){
			criteria.add(eq("category", category));
		}
		
		if (date != null){
			criteria.add(le("date", date));
		}
		
		if (title != null && !title.isEmpty()){
			criteria.add(eq("title", title));
		}	
				
		return criteria;
	}
}