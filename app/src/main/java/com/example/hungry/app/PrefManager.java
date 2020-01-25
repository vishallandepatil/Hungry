package com.example.hungry.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import com.example.hungry.login.model.User;
import com.google.firebase.iid.FirebaseInstanceId;


public class PrefManager
{

        SharedPreferences pref;
        SharedPreferences.Editor editor;
        Context _context;
        public static int PRIVATE_MODE = 0;

        public static final String PREF_NAME = "Insta Client";


   private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
   private static final String CLIENT_ID = "clientmaster_id";
   private static final String MOBILE = "mobile";
   private static final String FNAME = "fname";
   private static final String LNAME = "lname";
   private static final String MAIL = "mail";
   private static final String ISMAILVERIFY = "ismail";
   private static final String CITT_ID = "cityid";
   private static final String CITY_NAME = "cityname";
   private static final String STATE_ID = "stateid";
   private static final String STATE_NAME = "statename";
   private static final String GENDER = "gender";
   private static final String DOB = "dob";
   private static final String TOken = "token";
   private static final String tockenid = "tockenid";
   private static final String SLIDERLAUNCH = "slider";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";
    private static final String BLOCKED_EVENTS = "BlockedEvents";
    private static final String ISNOTIFICATIONVIBRATE= "isVibrate";
    private static final String RingTone= "ringTOne";





    public PrefManager(Context context) {
        try {
            this._context = context;
            pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
            editor = pref.edit();
        }
        catch (Exception e)
        {

        }
        }

        public void createLogin(User user_details) {

            editor.putString(CLIENT_ID, ""+user_details.getCM_ID());
            editor.putString(MOBILE, user_details.getCM_MOBILE());
            editor.putString(FNAME, user_details.getCM_FIRST_NAME());
            editor.putString(LNAME, user_details.getCM_LAST_NAME());
            editor.putString(MAIL, user_details.getCM_EMAIL());
            // editor.putString(CITY_NAME, user_details);
           // editor.putString(STATE_NAME, user_details.getState_name());
            editor.putString(STATE_ID, user_details.getSTATE_MASTER_ID()+"");
            editor.putString(CITT_ID, user_details.getCITY_MASTER_ID()+"");
            editor.putString(GENDER, user_details.getCM_GENDER());
            editor.putString(ISMAILVERIFY, user_details.getCM_IS_EMAIL_VERIFIED());


            editor.putString(DOB, user_details.getCM_DOB());
            editor.putBoolean(KEY_IS_LOGGED_IN, true);
            editor.commit();

          //  String refreshedToken = FirebaseInstanceId.getInstance().getToken();






        }

        public String getTocken( )
        {
        return  pref.getString(TOken,null);
        }
        public void setTocken(String token)   {
            editor.putString(TOken, token);
            editor.commit();
        }


    public User getUserDetails() {

        try {
            User user = new User();
            user.setCM_ID(Integer.parseInt(pref.getString(CLIENT_ID, "0")));
            user.setCM_MOBILE(pref.getString(MOBILE, null));
            user.setCM_FIRST_NAME(pref.getString(FNAME, null));
            user.setCM_LAST_NAME(pref.getString(LNAME, null));
            user.setCM_EMAIL(pref.getString(MAIL, null));
            user.setCM_IS_EMAIL_VERIFIED(pref.getString(ISMAILVERIFY, null));
           // user.setCI(pref.getString(CITY_NAME, null));
            user.setCITY_MASTER_ID(Integer.parseInt(pref.getString(CITT_ID, "0")));
            //user.setState_name(pref.getString(STATE_NAME, null));
            user.setSTATE_MASTER_ID(Integer.parseInt(pref.getString(STATE_ID, "0")));
            user.setCM_DOB(pref.getString(DOB, null));
            user.setCM_GENDER(pref.getString(GENDER, null));
            return user;
        }
        catch (Exception e)
        {
            return  null;
        }
        }

    public boolean islogin() {
        if(getUserDetails().getCM_ID()!=0)
        {
            return  true;
        }
        else
        {
            return  false;
        }

    }
    public void clearSession(){
            editor.remove(CLIENT_ID);
            editor.remove(MOBILE);
            editor.remove(FNAME);
            editor.remove(LNAME);
            editor.remove(MAIL);
            editor.remove(CITY_NAME);
            editor.remove(STATE_NAME);
            editor.remove(STATE_ID);
            editor.remove(CITT_ID);
            editor.remove(KEY_IS_LOGGED_IN);
            editor.remove(BLOCKED_EVENTS);
            editor.remove(ISNOTIFICATIONVIBRATE);
            editor.remove(RingTone);


            editor.apply();
            editor.commit();



    }
    public void setFistLounct(String apikey) {
        editor.putString(SLIDERLAUNCH, apikey);
        editor.commit();

    }
    public String getFistLounct( )  {
        return  pref.getString(SLIDERLAUNCH,null);


    }

    }





