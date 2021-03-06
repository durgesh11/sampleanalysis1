package co.aarav.mvc.hibernate;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.AbstractSingleColumnStandardBasicType;
import org.hibernate.type.TypeResolver;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;

//taken from http://appfuse.org/display/APF/Java+5+Enums+Persistence+with+Hibernate

public class GenericEnumUserType implements UserType, ParameterizedType {
	private static final String DEFAULT_IDENTIFIER_METHOD_NAME = "name";
	private static final String DEFAULT_VALUE_OF_METHOD_NAME = "valueOf";

	@SuppressWarnings("rawtypes")
	private Class<? extends Enum> enumClass;
	private Class<?> identifierType;
	private Method identifierMethod;
	private Method valueOfMethod;
//	private NullableType type;
	private int[] sqlTypes;
	@SuppressWarnings("rawtypes")
	private AbstractSingleColumnStandardBasicType type;

	@SuppressWarnings("rawtypes")
	@Override
	public void setParameterValues(Properties parameters) {
		String enumClassName = parameters.getProperty("enumClass");
		try {
			enumClass = Class.forName(enumClassName).asSubclass(Enum.class);
		} catch (ClassNotFoundException exception) {
			throw new HibernateException("Enum class not found", exception);
		}

		String identifierMethodName = parameters.getProperty("identifierMethod", DEFAULT_IDENTIFIER_METHOD_NAME);

		try {
			identifierMethod = enumClass.getMethod(identifierMethodName, new Class[0]);
			identifierType = identifierMethod.getReturnType();
		} catch (Exception exception) {
			throw new HibernateException("Failed to optain identifier method", exception);
		}

		TypeResolver tr = new TypeResolver();
		type = (AbstractSingleColumnStandardBasicType) tr.basic(identifierType.getName());
		if (type == null) {
			throw new HibernateException("Unsupported identifier type " + identifierType.getName());
		}
		sqlTypes = new int[] { type.sqlType() };

		String valueOfMethodName = parameters.getProperty("valueOfMethod", DEFAULT_VALUE_OF_METHOD_NAME);

		try {
			valueOfMethod = enumClass.getMethod(valueOfMethodName, new Class[] { identifierType });
		} catch (Exception exception) {
			throw new HibernateException("Failed to optain valueOf method", exception);
		}
	}

	@SuppressWarnings("rawtypes")
	public Class returnedClass() {
		return enumClass;
	}

	/*
	 * public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws
	 * HibernateException, SQLException { Object identifier = type.get(rs,
	 * names[0]); if (identifier == null) { return null; }
	 * 
	 * try { return valueOfMethod.invoke(enumClass, new Object[] { identifier }); }
	 * catch (Exception e) { throw new HibernateException(
	 * "Exception while invoking valueOf method '" + valueOfMethod.getName() +
	 * "' of " + "enumeration class '" + enumClass + "'", e); } }
	 * 
	 * public void nullSafeSet(PreparedStatement st, Object value, int index) throws
	 * HibernateException, SQLException { try { if (value == null) {
	 * st.setNull(index, type.sqlType()); } else { Object identifier =
	 * identifierMethod.invoke(value, new Object[0]); type.set(st, identifier,
	 * index); } } catch (Exception e) { throw new HibernateException(
	 * "Exception while invoking identifierMethod '" + identifierMethod.getName() +
	 * "' of " + "enumeration class '" + enumClass + "'", e); } }
	 */

	public int[] sqlTypes() {
		return sqlTypes;
	}

	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return cached;
	}

	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	public boolean equals(Object x, Object y) throws HibernateException {
		return x == y;
	}

	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	public boolean isMutable() {
		return false;
	}

	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		Object identifier = type.get(rs, names[0], session);
		if (identifier == null) {
			return null;
		}

		try {
			return valueOfMethod.invoke(enumClass, new Object[] { identifier });
		} catch (Exception e) {
			throw new HibernateException("Exception while invoking valueOf method '" + valueOfMethod.getName() + "' of "
					+ "enumeration class '" + enumClass + "'", e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
			throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		try {
			if (value == null) {
				st.setNull(index, type.sqlType());
			} else {
				Object identifier = identifierMethod.invoke(value, new Object[0]);
				type.set(st, identifier, index, session);
			}
		} catch (Exception e) {
			throw new HibernateException("Exception while invoking identifierMethod '" + identifierMethod.getName()
					+ "' of " + "enumeration class '" + enumClass + "'", e);
		}
	}
}
