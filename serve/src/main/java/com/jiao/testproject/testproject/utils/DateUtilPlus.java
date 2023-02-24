package com.jiao.testproject.testproject.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtilPlus {

	public static DateTimeFormatter formatFull = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public static DateTimeFormatter formatFullMill = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
	public static DateTimeFormatter formatFullShort = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	public static DateTimeFormatter formatYMD = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	/**
	 * 
	 * 得到当前日期格式为"yyyy-MM-dd HH:mm:ss"
	 * 
	 * @return
	 *
	 */
	public static String getFullDate() {
		return formatFull.format(LocalDateTime.now());
	}

	/**
	 * 
	 * 得到当前日期格式为"yyyyMMddHHmmss"
	 * 
	 * @return
	 *
	 */
	public static String getFullDateShort() {
		return formatFullShort.format(LocalDateTime.now());
	}

	/**
	 * 
	 * 得到当前日期格式为""yyyy-MM-dd"
	 * 
	 * @return
	 *
	 */
	public static String getYMD() {
		return formatYMD.format(LocalDateTime.now());
	}

	/**
	 * 
	 * 将"yyyy-MM-dd HH:mm:ss"转换为毫秒数
	 * 
	 * @param fullDateString
	 * @return
	 *
	 */
	public static Long toEpochMilli(String fullDateString) {
		return LocalDateTime.parse(fullDateString, formatFull).toInstant(OffsetDateTime.now().getOffset())
				.toEpochMilli();
	}

	/**
	 * 
	 * 将"yyyy-MM-dd"转换为天
	 * 
	 * @param ymd
	 * @return
	 *
	 */
	public static Long toEpochDayYMD(String ymd) {
		return LocalDate.parse(ymd, formatYMD).toEpochDay();
	}

	/**
	 * 
	 * 毫秒转"yyyy-MM-dd HH:mm:ss.SSS"
	 * 
	 * @param milliseconds
	 * @return
	 *
	 */
	public static String formatMillToFullMill(Long milliseconds) {
		LocalDateTime date = LocalDateTime.ofEpochSecond(milliseconds / 1000, (int) (milliseconds % 1000) * 1000000,
				OffsetDateTime.now().getOffset());
		return formatFullMill.format(date);
	}

	/**
	 * 
	 * 毫秒转"yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param milliseconds
	 * @return
	 *
	 */
	public static String formatMillToFull(Long milliseconds) {
		LocalDateTime date = LocalDateTime.ofEpochSecond(milliseconds / 1000, (int) (milliseconds % 1000) * 1000000,
				OffsetDateTime.now().getOffset());
		return formatFull.format(date);
	}

	/**
	 * 
	 * 获取起始日期间的所有日期 yyyy-MM-dd
	 * 
	 * @param start yyyy-MM-dd
	 * @param end   yyyy-MM-dd
	 * @return
	 *
	 */
	public static List<String> daysBetween(String start, String end) {
		List<String> list = new ArrayList<>();

		if (start.compareTo(end) < 0) {
			while (start.compareTo(end) <= 0) {
				list.add(start);
				start = formatYMD.format(LocalDate.parse(start, formatYMD).plusDays(1));
			}
		} else if (end.compareTo(start) < 0) {
			while (end.compareTo(start) <= 0) {
				list.add(end);
				end = formatYMD.format(LocalDate.parse(end, formatYMD).plusDays(1));
			}
		} else {
			list.add(start);
		}

		return list;
	}

	/**
	 * 
	 * 获取起始月份间的所有月份 yyyy-MM
	 * 
	 * @param start yyyy-MM
	 * @param end   yyyy-MM
	 * @return
	 *
	 */
	public static List<String> monthsBetween(String start, String end) {
		List<String> list = new ArrayList<>();

		start += "-01";
		end += "-01";

		if (start.compareTo(end) < 0) {
			while (start.compareTo(end) <= 0) {
				list.add(start.substring(0, 7));
				start = formatYMD.format(LocalDate.parse(start, formatYMD).plusMonths(1));
			}
		} else if (end.compareTo(start) < 0) {
			while (end.compareTo(start) <= 0) {
				list.add(end.substring(0, 7));
				end = formatYMD.format(LocalDate.parse(end, formatYMD).plusMonths(1));
			}
		} else {
			list.add(start.substring(0, 7));
		}

		return list;
	}

	public static String nowPlus(long amountToAdd, TemporalUnit unit) {
		return formatFull.format(LocalDateTime.now().plus(amountToAdd, unit));
	}

	public static String nowPlusDays(long days) {
		return formatFull.format(LocalDateTime.now().plusDays(days));
	}

	public static String nowPlusHours(long hours) {
		return formatFull.format(LocalDateTime.now().plusHours(hours));
	}

	public static String nowPlusMinutes(long minutes) {
		return formatFull.format(LocalDateTime.now().plusMinutes(minutes));
	}

	public static String nowPlusMonths(long months) {
		return formatFull.format(LocalDateTime.now().plusMonths(months));
	}

	public static String nowPlusNanos(long nanos) {
		return formatFull.format(LocalDateTime.now().plusNanos(nanos));
	}

	public static String nowPlusSeconds(long seconds) {
		return formatFull.format(LocalDateTime.now().plusSeconds(seconds));
	}

	public static String nowPlusWeeks(long weeks) {
		return formatFull.format(LocalDateTime.now().plusWeeks(weeks));
	}

	public static String nowPlusYears(long years) {
		return formatFull.format(LocalDateTime.now().plusYears(years));
	}

	public static String nowMinus(long amountToSubtract, TemporalUnit unit) {
		return formatFull.format(LocalDateTime.now().minus(amountToSubtract, unit));
	}

	public static String nowMinusDays(long days) {
		return formatFull.format(LocalDateTime.now().minusDays(days));
	}

	public static String nowMinusHours(long hours) {
		return formatFull.format(LocalDateTime.now().minusHours(hours));
	}

	public static String nowMinusMinutes(long minutes) {
		return formatFull.format(LocalDateTime.now().minusMinutes(minutes));
	}

	public static String nowMinusMonths(long months) {
		return formatFull.format(LocalDateTime.now().minusMonths(months));
	}

	public static String nowMinusNanos(long nanos) {
		return formatFull.format(LocalDateTime.now().minusNanos(nanos));
	}

	public static String nowMinusSeconds(long seconds) {
		return formatFull.format(LocalDateTime.now().minusSeconds(seconds));
	}

	public static String nowMinusWeeks(long weeks) {
		return formatFull.format(LocalDateTime.now().minusWeeks(weeks));
	}

	public static String nowMinusYears(long years) {
		return formatFull.format(LocalDateTime.now().minusYears(years));
	}

	/**
	 * 
	 * 获取月份第一天，yyyy-MM-dd
	 * 
	 * @param year
	 * @param month
	 * @return
	 *
	 */
	public static String getFirstDayOfMonth(int year, int month) {
		// 第一天
		LocalDate firstday = LocalDate.of(year, month, 1);
		return formatYMD.format(firstday);
	}

	/**
	 * 
	 * 获取月份最后一天，yyyy-MM-dd
	 * 
	 * @param year
	 * @param month
	 * @return
	 *
	 */
	public static String getLastDayOfMonth(int year, int month) {
		// 第一天
		LocalDate firstday = LocalDate.of(year, month, 1);
		// 最后一天
		LocalDate lastDay = firstday.with(TemporalAdjusters.lastDayOfMonth());
		return formatYMD.format(lastDay);
	}

	/**
	 * 
	 * 获取当前月份最后一天，yyyy-MM-dd
	 * 
	 * @return
	 *
	 */
	public static String getLastDayOfCurrentMonth() {
		// 最后一天
		LocalDate lastDay = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
		return formatYMD.format(lastDay);
	}

	/**
	 * 
	 * 获取当前月份第一天，yyyy-MM-dd
	 * 
	 * @return
	 *
	 */
	public static String getFirstDayOfCurrentMonth() {
		LocalDate firstDay = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
		return formatYMD.format(firstDay);
	}

	/**
	 * 
	 * 指定日期加减天数
	 * 
	 * @param startDay 开始日期yyyy-MM-dd
	 * @param plusDays 增加天数，负数表示减去天数
	 * @return
	 *
	 */
	public static String plusDays(String startDay, Integer plusDays) {
		return formatYMD.format(LocalDate.parse(startDay, formatYMD).plusDays(plusDays));
	}
	
	/**
	 * 
	 * 当前时间加上年数，得到新的时间
	 * 
	 * @param time  当前时间
	 * @param year  年份
	 * @return
	 * @throws ParseException 
	 *
	 * @变更记录 2022年4月28日 下午4:40:06 王亮 创建
	 *
	 */
	public static String getNewDateByAddYear(String time, int year) throws ParseException {
		Calendar cal=Calendar.getInstance();
		SimpleDateFormat sdf  =new SimpleDateFormat("yyyy-MM-dd");
		Date date= sdf.parse(time);
		cal.setTime(date);
		cal.add(Calendar.YEAR, year);
		return sdf.format(cal.getTime());
	}

	
	/**
	 * 
	 * 获取起始日期间的所有日期 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param start yyyy-MM-dd HH:mm:ss
	 * @param end   yyyy-MM-dd HH:mm:ss
	 * @return
	 *
	 */
	public static List<String> hoursBetween(String start, String end) {
		List<String> list = new ArrayList<>();
		
		if (start.compareTo(end) < 0) {
			while (start.compareTo(end) <= 0) {
				list.add(start);
				start = formatFull.format(LocalDateTime.parse(start, formatFull).plus(1,ChronoUnit.HOURS));
			}
		} else if (end.compareTo(start) < 0) {
			while (end.compareTo(start) <= 0) {
				list.add(end);
				end = formatFull.format(LocalDateTime.parse(end, formatFull).plus(1,ChronoUnit.HOURS));
			}
		} else {
			list.add(start);
		}

		return list;
	}
	
	
	/**
	 * 
	 * @param start
	 * @param hours
	 * @param direction 向前推 1 向后推 -1
	 * @return
	 */
	public static List<String> hoursPlus(String start,int hours,int direction){
		List<String> list = new ArrayList<>();
		int i = 0;
		while(i <= hours) {
			if(-1 == direction) {
				list.add(0,start);
			}else {
				list.add(start);
			}
			start = formatFull.format(LocalDateTime.parse(start, formatFull).plus(direction,ChronoUnit.HOURS));
			i++;
		}
		return list;
	}
	
	
	/**
	 * 
	 * @param start
	 * @param days
	 * @param direction 向前推 1 向后推 -1
	 * @return
	 */
	public static List<String> daysPlus(String start,int days,int direction){
		List<String> list = new ArrayList<>();
		int i = 0;
		while(i <= days) {
			if(-1 == direction) {
				list.add(0,start);
			}else {
				list.add(start);
			}
			start = formatYMD.format(LocalDate.parse(start, formatYMD).plusDays(direction));
			i++;
		}
		return list;
	}
	
	
	/**
	 * 
	 * @param start
	 * @param months
	 * @param direction 向前推 1 向后推 -1
	 * @return
	 */
	public static List<String> monthsPlus(String start,int months,int direction){
		List<String> list = new ArrayList<>();
		start = start + "-01";
		int i = 0;
		while(i <= months) {
			if(-1 == direction) {
				list.add(0, start.substring(0, 7));
			}else {
				list.add(start.substring(0, 7));
			}
			start = formatYMD.format(LocalDate.parse(start, formatYMD).plusMonths(direction));
			i++;
		}
		return list;
	}
		
}
