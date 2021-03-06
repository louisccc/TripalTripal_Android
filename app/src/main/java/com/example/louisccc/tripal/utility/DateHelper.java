package com.example.louisccc.tripal.utility;

import android.app.ActionBar;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {
	
	public static void embeddedTabs(Object actionBar, Boolean embed_tabs) {
		try {
			if (actionBar instanceof ActionBar) {
				// ICS and forward
				try {
					Field actionBarField = actionBar.getClass()
							.getDeclaredField("mActionBar");
					actionBarField.setAccessible(true);
					actionBar = actionBarField.get(actionBar);
				} catch (Exception e) {
					Log.e("", "Error enabling embedded tabs", e);
				}
			}
			Method setHasEmbeddedTabsMethod = actionBar.getClass().getDeclaredMethod("setHasEmbeddedTabs", boolean.class);
			setHasEmbeddedTabsMethod.setAccessible(true);
			setHasEmbeddedTabsMethod.invoke(actionBar, embed_tabs);
		} catch (Exception e) {
			Log.e("", "Error marking actionbar embedded", e);
		}
	}
	
	public static Date getDate(int year, int month, int day) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month-1);
		c.set(Calendar.DAY_OF_MONTH, day);
		return c.getTime();
	}
	public static Date getDate(String time_string){
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			date = sdf.parse(time_string);
			//timestamp = new Timestamp(date.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}
	public static Calendar getCalendarDay(int diff){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, diff);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}
	public static String getDate(int diff){
		Calendar c = getCalendarDay(diff);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(c.getTime());
	}

	public static String getLocalString(Context mContext, int diff) {
		return SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT,
				mContext.getResources().getConfiguration().locale).format(
				getCalendarDay(diff).getTime());
	}
	public static String getLocalString(Context mContext, Date date) {
		return SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT,
				mContext.getResources().getConfiguration().locale).format(
				date);
	}
	public static String getDateString(int year, int month, int day) {
		Date d = getDate(year, month, day);
		return getDateString(d);
	}
	public static String getDateString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public static String getTodayDateString() {
		Calendar c = getCalendarDay(0);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		return getDateString(year, month, day);
	}

	public static TextView getTextViewWithText (Context ctx, String text ) {
		TextView textview = new TextView( ctx );
		textview.setText( text );
		textview.setTextColor(0xffa4a6a8);
		textview.setPadding(0, 0, 0, 14);
		textview.setGravity(Gravity.LEFT);
		return textview;
	}

	public static TextView getEmptyTextViewWithText (Context ctx, String text ) {
		TextView textview = new TextView( ctx );
		textview.setLayoutParams(
				new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.MATCH_PARENT
				)
		);
		textview.setText( text );
		textview.setTextColor(0xffa4a6a8);
		textview.setPadding(0, 0, 0, 14);
		textview.setGravity(Gravity.CENTER);
		return textview;
	}

	public static boolean isDateOverlapToday(Date from, Date to) {
		Date today = getCalendarDay(0).getTime();
		return (today.compareTo(from) >= 0 && today.compareTo(to) <= 0);
	}

}
