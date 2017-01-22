package com.example.babar.agecalculator;

import android.os.Bundle;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity implements OnClickListener{



    private FloatingActionButton btnStart;
    static final int DATE_START_DIALOG_ID = 0;
    private int startYear=1970;
    private int startMonth=6;
    private int startDay=15;
    private AgeCalculation age = null;
    private TextView currentDate;
    private TextView birthDate;
    TextView to_show;
    TextView to_show_years;
    TextView to_show_day;
    TextView to_show_months;
    int numOfDays;
    long time_c;
    long diff;
    String for_name_of_day;
    long time_d;
    String final_name_of_day;
    TextView totaldays;
    TextView day_name;
    TextView days_to_go;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        age=new AgeCalculation();
        currentDate=(TextView) findViewById(R.id.textView1);
        birthDate=(TextView) findViewById(R.id.textView2);
        to_show= (TextView) findViewById(R.id.age_show);
        btnStart=(FloatingActionButton) findViewById(R.id.button1);
        totaldays = (TextView) findViewById(R.id.to_show_days);
        days_to_go = (TextView) findViewById(R.id.daysleft);
        day_name = (TextView) findViewById(R.id.name_of_day);


        currentDate.setText("Current Date: "+age.togetCurrentDate());



        btnStart.setOnClickListener(this);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_START_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        startYear, startMonth, startDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener
            = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            startYear=selectedYear;
            startMonth=selectedMonth;
            startDay=selectedDay;
            age.setDateOfBirth(startYear, startMonth, startDay);
            birthDate.setText("Date of Birth: "+selectedDay+":"+(startMonth+1)+":"+startYear);

            String get_date_of_birth = selectedDay+":"+(startMonth+1)+":"+startYear;

            DateFormat df = new SimpleDateFormat("MM:dd:yyyy");
            Date d_o_b;
            try {
                d_o_b = df.parse(get_date_of_birth);
                time_d =d_o_b.getTime();

                String  name_of_day = d_o_b.toString();
                for_name_of_day = name_of_day.substring(0,3);


            } catch (ParseException e) {
                e.printStackTrace();
            }
            String iiiiiii = age.togetCurrentDate();

            Date c_d_o_b;
            try {
                c_d_o_b = df.parse(iiiiiii);
                time_c = c_d_o_b.getTime();


            } catch (ParseException e) {
                e.printStackTrace();
            }
            //17294

            diff = time_c-time_d;

       numOfDays = (int) (diff/(1000*60*60*24));
            totaldays.setText("Total days of Age: "+numOfDays + "");


            if (for_name_of_day.equalsIgnoreCase("sun") || for_name_of_day.equalsIgnoreCase("mon")
                    || for_name_of_day.equalsIgnoreCase("fri")) {

                final_name_of_day = for_name_of_day + "day";
            }
            else if(for_name_of_day.equalsIgnoreCase("tue")) {
                final_name_of_day = for_name_of_day + "sday";
            }
            else if(for_name_of_day.equalsIgnoreCase("wed")) {
                final_name_of_day = for_name_of_day + "nesday";
            }
            else if(for_name_of_day.equalsIgnoreCase("thu")) {
                final_name_of_day = for_name_of_day + "rsday";
            }
            else if(for_name_of_day.equalsIgnoreCase("sat")) {
                final_name_of_day = for_name_of_day + "urday";
            }

            day_name.setText("Birth day Name   : " +final_name_of_day + "");

            //for birthday work
            String for_birth = selectedDay+":"+(startMonth+1)+":"+startYear;

            DateFormat birth_formst = new SimpleDateFormat("MM:dd:yyyy");
            Date birth_date_to_calculate = null;
            try {
                birth_date_to_calculate =birth_formst.parse(for_birth);

            } catch (ParseException e) {
                e.printStackTrace();
            }

            String jty = age.togetCurrentDate();

            Date current_date = null;
            try {
              current_date = birth_formst.parse(jty);

            } catch (ParseException e) {
                e.printStackTrace();
            }


            final Calendar c = Calendar.getInstance();
            c.set(+startYear, +(startMonth+1), +selectedDay);  //this is birthdate. set it to date you get from date picker
//            c.add(Calendar.DATE, Integer.parseInt(for_birth));
            final Calendar today = Calendar.getInstance();
            // Take your DOB Month and compare it to current
            // month
            final int BMonth = c.get(Calendar.MONTH);
            final int CMonth = today.get(Calendar.MONTH);

            final int BDate = c.get(Calendar.DAY_OF_MONTH);
            final int CDate = today.get(Calendar.DAY_OF_MONTH);

            c.set(Calendar.YEAR, today.get(Calendar.YEAR));
            c.set(Calendar.DAY_OF_WEEK,
                    today.get(Calendar.DAY_OF_WEEK));
            if (BMonth < CMonth) {
                c.set(Calendar.YEAR,
                        today.get(Calendar.YEAR) + 1);
            }
            //added this condition so that it doesn't add in case birthdate is greater than current date . i.e. yet to come in this month.
            else if (BMonth == CMonth){
                if(BDate < CDate){
                    c.set(Calendar.YEAR,
                            today.get(Calendar.YEAR) + 1);
                }
            }
            // Result in millis

            final long millis = c.getTimeInMillis()
                    - today.getTimeInMillis();
            // Convert to days
            final long days_left_to_birthday = millis / 86400000; // Precalculated

            days_to_go.setText("Days to Birthday  : " +days_left_to_birthday + "");




            calculateAge();

        }
    };
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.button1:
                showDialog(DATE_START_DIALOG_ID);
                break;

            default:
                break;
        }
    }

    private void calculateAge()
    {
        age.tocalcualteYear();
        age.tocalcualteMonth();
        age.tocalcualteDay();
//        Toast.makeText(getBaseContext(), "click the resulted button"+age.getResultage() , Toast.LENGTH_SHORT).show();


        String calculatedage = age.getResultage();

        int y = AgeCalculation.remaining_year;
        int m = AgeCalculation.remaining_months;
        int d = AgeCalculation.remaining_days;
        to_show_day = (TextView) findViewById(R.id.for_days);
        to_show_years = (TextView) findViewById(R.id.for_years);
        to_show_months = (TextView) findViewById(R.id.for_months);

        to_show.setText("Your Age is  : "+calculatedage);


        to_show_years.setText(y+ "");
        to_show_months.setText(m+ "");
        to_show_day.setText(d+ "");
//
//        long diff = endDate.getTime() – startDate.getTime();//in Milli seconds
//        int numOfDays = diff/(1000*60*60*24);
//        int days = Days.daysBetween(date1, date2).getDays();


    }


}
