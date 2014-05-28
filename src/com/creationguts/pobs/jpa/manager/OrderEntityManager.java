package com.creationguts.pobs.jpa.manager;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import com.creationguts.pobs.jpa.model.Order;

public class OrderEntityManager extends EntityManager<Order> {
	
	public List<Order> getOrders() {
		
		logger.debug("Getting orders");
		getEntityManager().getTransaction().begin();
		List<Order> result = getEntityManager().createQuery( "from Order", Order.class ).getResultList();
		logger.debug("total orders returned: " + result.size());
		getEntityManager().getTransaction().commit();
		getEntityManager().close();
	
		return result;
	}
	
	public Map<Date, List<Order>> getOrders(Date beginDeliveryDate,
			Date endDeliveryDate, Date beginReadyDate, Date endReadyDate,
			Order.Status status) {
		
		String dvrDate = "";
		if (beginDeliveryDate == null) {
			if (endDeliveryDate != null)
				dvrDate = " o.deliveryDate <= :enddvry";
		} else {
			if (endDeliveryDate != null)
				dvrDate = " o.deliveryDate >= :begindvry and o.deliveryDate <= :enddvry";
			dvrDate = " o.deliveryDate >= :begindvry";
		}

		String rdyDate = "";
		if (beginReadyDate == null) {
			if (endReadyDate != null)
				rdyDate = " o.readyDate <= :endrdy";
		} else {
			if (endReadyDate != null)
				rdyDate = " o.readyDate >= :beginrdy and o.readyDate <= :endrdy";
			rdyDate = " o.readyDate >= :beginrdy";
		}

		logger.debug("Getting orders");
		getEntityManager().getTransaction().begin();
		TypedQuery<Order> q = getEntityManager().createQuery(
				"from Order o where 1=1 and " + dvrDate + " and " + rdyDate,
				Order.class);
		logger.debug(" query formed: " + q);
		if (q.getParameters().contains("begindvry"))
			q.setParameter("begindvry", beginDeliveryDate, TemporalType.DATE);
		if (q.getParameters().contains("enddvry"))
			q.setParameter("endrdy", beginDeliveryDate, TemporalType.DATE);
		if (q.getParameters().contains("beginrdy"))
			q.setParameter("beginrdy", beginReadyDate, TemporalType.DATE);
		if (q.getParameters().contains("endrdy"))
			q.setParameter("endrdy", beginReadyDate, TemporalType.DATE);
		
		List<Order> result = q.getResultList();
		
		logger.debug("total orders returned: " + result.size());
		getEntityManager().getTransaction().commit();
		getEntityManager().close();

		return null;
	}
	
	private static Logger logger = Logger.getLogger(OrderEntityManager.class);
}
