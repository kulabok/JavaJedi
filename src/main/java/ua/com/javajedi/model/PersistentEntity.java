package ua.com.javajedi.model;

import org.hibernate.proxy.HibernateProxyHelper;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class PersistentEntity<ID extends Serializable> implements Persistable<ID> {

	private static final long serialVersionUID = 0L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO doesn't work well for PostgreSql in Hibernate
	private ID id;

	@Override
	public ID getId() {
		return id;
	}

	/**
	 * Sets the id of the entity.
	 *
	 * @param id the id to set
	 */
	public void setId(final ID id) {
		this.id = id;
	}

	@Override
	@Transient
	public boolean isNew() {
		return null == getId();
	}

	@Override
	public String toString() {
		return String.format("Entity of type %s with id: %s", this.getClass().getName(), getId());
	}

	/*
	 * The expression !getClass().equals(HibernateProxyHelper.getClassWithoutInitializingProxy(obj)) is not considered as
	 * a parameter class check.
	 */
	@SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
	@Override
	public boolean equals(final Object obj) {
		if (null == obj) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!getClass().equals(HibernateProxyHelper.getClassWithoutInitializingProxy(obj))) {
			return false;
		}
		final PersistentEntity<?> that = (PersistentEntity<?>) obj;
		return null != this.getId() && this.getId().equals(that.getId());
	}

	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode += null == getId() ? 0 : getId().hashCode() * 31;
		return hashCode;
	}
}
