package co.aarav.mvc.hibernate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.DateType;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.usertype.UserType;

public class CalendarType implements UserType {

	private final static int[] TYPES = { Types.DATE, Types.TIME };

	public Object assemble(Serializable arg0, Object arg1)
			throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	public Object deepCopy(Object value) throws HibernateException {
		Calendar calendar = (Calendar) value;
		Calendar newCalendar = Calendar.getInstance();
		newCalendar.setTime(calendar.getTime());
		newCalendar.set(Calendar.HOUR_OF_DAY, calendar
				.get(Calendar.HOUR_OF_DAY));
		newCalendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
		return newCalendar;
	}

	public Serializable disassemble(Object arg0) throws HibernateException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean equals(Object x, Object y) throws HibernateException {
		return x == null ? y == null : x.equals(y);
	}

	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	public boolean isMutable() {
		return true;
	}

	/*@SuppressWarnings("deprecation")
	public Object nullSafeGet(ResultSet resultSet, String[] names, Object owner)
			throws HibernateException, SQLException {
		Date date = (Date) Hibernate.DATE.nullSafeGet(resultSet, names[0]);
		Time time = (Time) Hibernate.TIME.nullSafeGet(resultSet, names[1]);

		if (date == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Calendar tempCalendar = Calendar.getInstance();
		tempCalendar.setTimeInMillis(time.getTime());
		calendar.set(Calendar.HOUR_OF_DAY, tempCalendar
				.get(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, tempCalendar.get(Calendar.MINUTE));

		return calendar;
	}

	@SuppressWarnings("deprecation")
	public void nullSafeSet(PreparedStatement statement, Object value, int index)
			throws HibernateException, SQLException {
           	Calendar calendar = (Calendar) value;
		Date date = (calendar == null) ? null : calendar.getTime();
		Time time = (calendar == null) ? null : new Time(calendar.getTime()
				.getTime());
		Hibernate.DATE.nullSafeSet(statement, date, index);
		Hibernate.TIME.nullSafeSet(statement, time, index + 1); 
	}*/

	public Object replace(Object original, Object target, Object owner)
			throws HibernateException {
		Calendar calendar = (Calendar) original;
		Calendar newCalendar = Calendar.getInstance();
		newCalendar.setTime(calendar.getTime());
		newCalendar.set(Calendar.HOUR_OF_DAY, calendar
				.get(Calendar.HOUR_OF_DAY));
		newCalendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE));
		return newCalendar;
	}

	public Class<Calendar> returnedClass() {
		return Calendar.class;
	}

	public int[] sqlTypes() {
		return TYPES;
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor session, Object owner)
			throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		Date date = (Date) DateType.INSTANCE.nullSafeGet(rs, names[0], session);
	//	Time time = (Time)  DateType.INSTANCE.nullSafeGet(rs, names[1],session);

		if (date == null) {
			return null;
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		Calendar tempCalendar = Calendar.getInstance();
	//	tempCalendar.setTimeInMillis(time.getTime());
		calendar.set(Calendar.HOUR_OF_DAY, tempCalendar
				.get(Calendar.HOUR_OF_DAY));
		calendar.set(Calendar.MINUTE, tempCalendar.get(Calendar.MINUTE));

		return calendar;
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SessionImplementor session)
			throws HibernateException, SQLException {
		// TODO Auto-generated method stub
		
		Calendar calendar = (Calendar) value;
		Date date = (calendar == null) ? null : calendar.getTime();
		Time time = (calendar == null) ? null : new Time(calendar.getTime()
				.getTime());
		StandardBasicTypes.DATE.nullSafeSet(st, date, index,session);
		StandardBasicTypes.TIME.nullSafeSet(st, time, index + 1,session);
		
	}
}
