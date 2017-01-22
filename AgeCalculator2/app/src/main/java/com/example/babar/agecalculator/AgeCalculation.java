package com.example.babar.agecalculator;

/**
 * Created by babar on 12/30/2016.
 */

import java.util.Calendar;

public class AgeCalculation {
    private int startYear;
    private int startMonth;
    private int startDay;
    private int endYear;
    private int endMonth;
    private int endDay;
    public static int remaining_year;
    public static int remaining_months;
    public static int remaining_days;
    private Calendar end_date;
    public String togetCurrentDate()
    {
        end_date =Calendar.getInstance();
        endYear= end_date.get(Calendar.YEAR);
        endMonth= end_date.get(Calendar.MONTH);
        endMonth++;
        endDay= end_date.get(Calendar.DAY_OF_MONTH);
        return endDay+":"+endMonth+":"+endYear;
    }
    public void setDateOfBirth(int sYear, int sMonth, int sDay)
    {
        startYear=sYear;
        startMonth=sMonth;
        startMonth++;
        startDay=sDay;

    }
    public void tocalcualteYear()
    {
        remaining_year =endYear-startYear;

    }

    public void tocalcualteMonth()
    {
        if(endMonth>=startMonth)
        {
            remaining_months = endMonth-startMonth;
        }
        else
        {
            remaining_months =endMonth-startMonth;
            remaining_months =12+ remaining_months;
            remaining_year--;
        }

    }
    public void tocalcualteDay()
    {

        if(endDay>=startDay)
        {
            remaining_days = endDay-startDay;
        }
        else
        {
            remaining_days =endDay-startDay;
            remaining_days =30+ remaining_days;
            if(remaining_months ==0)
            {
                remaining_months =11;
                remaining_year--;
            }
            else
            {
                remaining_months--;
            }

        }
    }

    public String getResultage()
    {
        return remaining_days +":"+ remaining_months +":"+ remaining_year;

    }



}
