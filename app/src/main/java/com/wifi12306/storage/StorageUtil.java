package com.wifi12306.storage;

import android.content.Context;
import android.content.ContextWrapper;
import android.text.TextUtils;

import com.alipay.android.phone.mobilesdk.storage.encryption.TaobaoSecurityEncryptor;
import com.alipay.android.phone.mobilesdk.storage.sp.APSharedPreferences;
import com.alipay.android.phone.mobilesdk.storage.sp.SharedPreferencesManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by shaojie.c on 2017/9/5.
 */
public class StorageUtil {
    private static final String TAG = "StorageUtil";
    private static final String STORAGE_NAME = "12306data";
    static ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    public static void saveIsFirst(ContextWrapper context, boolean isFirst) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        preferences.putBoolean("isFirst", isFirst);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });

    }

    public static boolean getIsFirst(ContextWrapper contextWrapper){
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        return preferences.getBoolean("isFirst", true);
    }

    /**
     * 判断是否进入过引导页面
     * @param context
     * @param isShowGuide
     */
    public static void saveIsShowGuide(ContextWrapper context, boolean isShowGuide) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        preferences.putBoolean("isShowGuide", isShowGuide);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });

    }

    public static boolean getIsShowGuide(ContextWrapper contextWrapper){
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        return preferences.getBoolean("isShowGuide", true);
    }

    /**
     * 保存常用联系人
     *
     * @param context
     * @param passengerString
     */
    public static void savePassenger(ContextWrapper context, String passengerString) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        preferences.putString("passenger", passengerString);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取常用联系人
     *
     * @param contextWrapper
     * @return
     */
    public static String getPassenger(ContextWrapper contextWrapper) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        return preferences.getString("passenger", "");
    }

    /**
     * 用户唯一标识
     * @param context
     * @return
     */
    public static String getTK(ContextWrapper context) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        return preferences.getString("kLoginUserTokenKey", "");
    }

    public static void saveTK(ContextWrapper context, String tk) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        preferences.putString("kLoginUserTokenKey", tk);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }


    public static boolean getAutoLogin(ContextWrapper contextWrapper){
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        boolean isAutoLogin = preferences.getBoolean("isAutoLogin",false);
        return isAutoLogin;
    }

    public static void saveAutoLogin(ContextWrapper contextWrapper,boolean isAutoLogin){
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        preferences.putBoolean("isAutoLogin", isAutoLogin);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    public static String getLoginInfo(ContextWrapper contextWrapper) {
        try {
            APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
            String userInfoStr = preferences.getString("kLoginSuccessKey", "");
            return userInfoStr;
        } catch (Exception e) {
            return null;
        }
    }
    public static void saveSearchDate2Sp(Context context, String departDate) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        preferences.putString("search_date", departDate);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    public static String getSearchDateFromSp(Context context) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        return preferences.getString("search_date", "");
    }

    public static void saveSearchRecord2Sp(Context context, String record) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        preferences.putString("search_record", record);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    public static String getSearchRecordFromSp(Context context) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        return preferences.getString("search_record", "");
    }

    public static String getUserName(ContextWrapper context) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        return preferences.getString("user_name", "");
    }

    public static void saveUserName(ContextWrapper context, String userName) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        preferences.putString("user_name", userName);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    public static String getPassword(ContextWrapper context) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        return preferences.getString("password", "");
    }

    public static void savePassword(ContextWrapper context, String password) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        preferences.putString("password", password);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    public static void saveHomeTipNoMoreDisplay(Context context) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        preferences.putBoolean("home_tip_display", true);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    public static boolean getHomeTipNoMoreDisplay(Context context) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        return preferences.getBoolean("home_tip_display", false);
    }

    /**
     * 保存车站
     *
     * @param contextWrapper
     * @param stationString
     */
    public static void saveStation(ContextWrapper contextWrapper, String stationString){
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        preferences.putString("station", stationString);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取车站
     *
     * @param contextWrapper
     * @return
     */
    public static String getStation(ContextWrapper contextWrapper) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        return preferences.getString("station", "");
    }

    /**
     * 保存车站版本号
     *
     * @param context
     * @param stationVersion
     */
    public static void saveStationVersion(ContextWrapper context, String stationVersion) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        preferences.putString("station_version_no", stationVersion);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取车站版本号
     *
     * @param context
     * @return
     */
    public static String getStationVersion(ContextWrapper context) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        return preferences.getString("station_version_no", "");
    }


    /**
     * 保存大学
     *
     * @param contextWrapper
     * @param universityString
     */
    public static void saveUniversity(ContextWrapper contextWrapper, String universityString){
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        preferences.putString("university", universityString);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取大学
     *
     * @param contextWrapper
     * @return
     */
    public static String getUniversity(ContextWrapper contextWrapper) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        return preferences.getString("university", "");
    }

    /**
     * 保存大学版本号
     *
     * @param context
     * @param universityVersion
     */
    public static void saveUniversityVersion(ContextWrapper context, String universityVersion) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        preferences.putString("university_version_no", universityVersion);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取大学版本号
     *
     * @param context
     * @return
     */
    public static String getUniversityVersion(ContextWrapper context) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        return preferences.getString("university_version_no", "");
    }


    /**
     * 保存城市
     *
     * @param contextWrapper
     * @param cityString
     */
    public static void saveCity(ContextWrapper contextWrapper, String cityString){
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        preferences.putString("city", cityString);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取城市
     *
     * @param contextWrapper
     * @return
     */
    public static String getCity(ContextWrapper contextWrapper) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        return preferences.getString("city", "");
    }

    /**
     * 保存城市版本号
     *
     * @param context
     * @param cityVersion
     */
    public static void saveCityVersion(ContextWrapper context, String cityVersion) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        preferences.putString("city_version_no", cityVersion);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取城市版本号
     *
     * @param context
     * @return
     */
    public static String getCityVersion(ContextWrapper context) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        return preferences.getString("city_version_no", "");
    }

    /**
     * 保存省份
     *
     * @param contextWrapper
     * @param provinceString
     */
    public static void saveProvince(ContextWrapper contextWrapper, String provinceString){
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        preferences.putString("province", provinceString);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取省份
     *
     * @param contextWrapper
     * @return
     */
    public static String getProvince(ContextWrapper contextWrapper) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        return preferences.getString("province", "");
    }

    /**
     * 保存省份版本号
     *
     * @param context
     * @param provinceVersion
     */
    public static void saveProvinceVersion(ContextWrapper context, String provinceVersion) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        preferences.putString("province_version_no", provinceVersion);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取省份版本号
     *
     * @param context
     * @return
     */
    public static String getProvinceVersion(ContextWrapper context) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        return preferences.getString("province_version_no", "");
    }

    /**
     * 保存国家
     *
     * @param contextWrapper
     * @param countryString
     */
    public static void saveCountry(ContextWrapper contextWrapper, String countryString){
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        preferences.putString("country", countryString);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取国家
     *
     * @param contextWrapper
     * @return
     */
    public static String getCountry(ContextWrapper contextWrapper) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        return preferences.getString("country", "");
    }

    /**
     * 保存国家版本号
     *
     * @param context
     * @param countryVersion
     */
    public static void saveCountryVersion(ContextWrapper context, String countryVersion) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        preferences.putString("country_version_no", countryVersion);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取国家版本号
     *
     * @param context
     * @return
     */
    public static String getCountryVersion(ContextWrapper context) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        return preferences.getString("country_version_no", "");
    }

    /**
     * 保存车站服务
     *
     * @param contextWrapper
     * @param stationServerString
     */
    public static void saveStationServer(ContextWrapper contextWrapper, String stationServerString){
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        preferences.putString("stationServer", stationServerString);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取车站服务
     *
     * @param contextWrapper
     * @return
     */
    public static String getStationServer(ContextWrapper contextWrapper) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        return preferences.getString("stationServer", "");
    }

    /**
     * 保存车站服务版本号
     *
     * @param context
     * @param stationServerVersion
     */
    public static void saveStationServerVersion(ContextWrapper context, String stationServerVersion) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        preferences.putString("station_server_version_no", stationServerVersion);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取车站服务版本号
     *
     * @param context
     * @return
     */
    public static String getStationServerVersion(ContextWrapper context) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        return preferences.getString("station_server_version_no", "");
    }

    /**
     * 保存列车字头对应席别
     *
     * @param contextWrapper
     * @param seatTypeListString
     */
    public static void saveSeatTypeList(ContextWrapper contextWrapper, String seatTypeListString){
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        preferences.putString("seatTypeList", seatTypeListString);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取列车字头对应席别
     *
     * @param contextWrapper
     * @return
     */
    public static String getSeatTypeList(ContextWrapper contextWrapper) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        return preferences.getString("seatTypeList", "");
    }

    /**
     * 保存列车字头对应席别版本号
     *
     * @param context
     * @param seatTypeListVersion
     */
    public static void saveSeatTypeListVersion(ContextWrapper context, String seatTypeListVersion) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        preferences.putString("seat_type_list_version_no", seatTypeListVersion);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取列车字头对应席别版本号
     *
     * @param context
     * @return
     */
    public static String getSeatTypeListVersion(ContextWrapper context) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        return preferences.getString("seat_type_list_version_no", "");
    }

    /**
     * 保存系统维护时间
     *
     * @param contextWrapper
     * @param sysTimeString
     */
    public static void saveSysTime(ContextWrapper contextWrapper, String sysTimeString){
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        preferences.putString("sysTime", sysTimeString);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取系统维护时间
     *
     * @param contextWrapper
     * @return
     */
    public static String getSysTime(ContextWrapper contextWrapper) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        return preferences.getString("sysTime", "");
    }

    /**
     * 保存系统维护时间版本号
     *
     * @param context
     * @param sysTimeVersion
     */
    public static void saveSysTimeVersion(ContextWrapper context, String sysTimeVersion) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        preferences.putString("sys_time_version_no", sysTimeVersion);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取系统维护时间版本号
     *
     * @param context
     * @return
     */
    public static String getSysTimeVersion(ContextWrapper context) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        return preferences.getString("sys_time_version_no", "");
    }

    /**
     * 保存证件类型
     *
     * @param contextWrapper
     * @param cardTypeString
     */
    public static void saveCardType(ContextWrapper contextWrapper, String cardTypeString){
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        preferences.putString("cardType", cardTypeString);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取证件类型
     *
     * @param contextWrapper
     * @return
     */
    public static String getCardType(ContextWrapper contextWrapper) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        return preferences.getString("cardType", "");
    }

    /**
     * 保存证件类型版本号
     *
     * @param context
     * @param cardTypeVersion
     */
    public static void saveCardTypeVersion(ContextWrapper context, String cardTypeVersion) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        preferences.putString("card_type_version_no", cardTypeVersion);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取证件类型版本号
     *
     * @param context
     * @return
     */
    public static String getCardTypeVersion(ContextWrapper context) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        return preferences.getString("card_type_version_no", "");
    }

    /**
     * 保存所有票种
     *
     * @param contextWrapper
     * @param ticketTypeString
     */
    public static void saveTicketType(ContextWrapper contextWrapper, String ticketTypeString){
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        preferences.putString("ticketType", ticketTypeString);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取所有票种
     *
     * @param contextWrapper
     * @return
     */
    public static String getTicketType(ContextWrapper contextWrapper) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        return preferences.getString("ticketType", "");
    }

    /**
     * 保存所有票种版本号
     *
     * @param context
     * @param ticketTypeVersion
     */
    public static void saveTicketTypeVersion(ContextWrapper context, String ticketTypeVersion) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        preferences.putString("ticket_type_version_no", ticketTypeVersion);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取所有票种版本号
     *
     * @param context
     * @return
     */
    public static String getTicketTypeVersion(ContextWrapper context) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        return preferences.getString("ticket_type_version_no", "");
    }

    /**
     * 保存所有席别
     *
     * @param contextWrapper
     * @param seatTypeString
     */
    public static void saveSeatType(ContextWrapper contextWrapper, String seatTypeString){
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        preferences.putString("seatType", seatTypeString);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取所有席别
     *
     * @param contextWrapper
     * @return
     */
    public static String getSeatType(ContextWrapper contextWrapper) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        return preferences.getString("seatType", "");
    }

    /**
     * 保存所有席别版本号
     *
     * @param context
     * @param seatTypeVersion
     */
    public static void saveSeatTypeVersion(ContextWrapper context, String seatTypeVersion) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        preferences.putString("seat_type_version_no", seatTypeVersion);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取所有席别版本号
     *
     * @param context
     * @return
     */
    public static String getSeatTypeVersion(ContextWrapper context) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        return preferences.getString("seat_type_version_no", "");
    }

    /**
     * 保存预售期
     *
     * @param contextWrapper
     * @param reservePeriodString
     */
    public static void saveReservePeriod(ContextWrapper contextWrapper, String reservePeriodString) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        preferences.putString("reservePeriod", reservePeriodString);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取预售期
     *
     * @param contextWrapper
     * @return
     */
    public static String getReservePeriod(ContextWrapper contextWrapper) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        return preferences.getString("reservePeriod", "");
    }

    /**
     * 保存预售期版本号
     *
     * @param context
     * @param reservePeriodVersion
     */
    public static void saveReservePeriodVersion(ContextWrapper context, String reservePeriodVersion) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        preferences.putString("reserve_period_version_no", reservePeriodVersion);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取预售期版本号
     *
     * @param context
     * @return
     */
    public static String getReservePeriodVersion(ContextWrapper context) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        return preferences.getString("reserve_period_version_no", "");
    }

    /**
     * 保存购票参数
     *
     * @param contextWrapper
     * @param orderParaString
     */
    public static void saveOrderPara(ContextWrapper contextWrapper, String orderParaString){
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        preferences.putString("orderPara", orderParaString);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取购票参数
     *
     * @param contextWrapper
     * @return
     */
    public static String getOrderPara(ContextWrapper contextWrapper) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        return preferences.getString("orderPara", "");
    }

    /**
     * 保存购票参数版本号
     *
     * @param context
     * @param orderParaVersion
     */
    public static void saveOrderParaVersion(ContextWrapper context, String orderParaVersion) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        preferences.putString("order_para_version_no", orderParaVersion);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取购票参数版本号
     *
     * @param context
     * @return
     */
    public static String getOrderParaVersion(ContextWrapper context) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        return preferences.getString("order_para_version_no", "");
    }

    /**
     * 保存快递版本号
     *
     * @param context
     * @param expressVersion
     */
    public static void saveExpressVersion(ContextWrapper context, String expressVersion) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        preferences.putString("express_version_no", expressVersion);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取快递版本号
     *
     * @param context
     * @return
     */
    public static String getExpressVersion(ContextWrapper context) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        return preferences.getString("express_version_no", "");
    }

    /**
     * 保存快递所有市信息
     *
     * @param contextWrapper
     * @param expressCityString
     */
    public static void saveAllCity(ContextWrapper contextWrapper, String expressCityString){
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        preferences.putString("expressCity", expressCityString);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取快递所有市信息
     *
     * @param contextWrapper
     * @return
     */
    public static String getAllCity(ContextWrapper contextWrapper) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        return preferences.getString("expressCity", "");
    }

    /**
     * 保存快递所有区县信息
     *
     * @param contextWrapper
     * @param expressCountryString
     */
    public static void saveAllCountry(ContextWrapper contextWrapper, String expressCountryString) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        preferences.putString("expressCountry", expressCountryString);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取快递所有区县信息
     *
     * @param contextWrapper
     * @return
     */
    public static String getAllCountry(ContextWrapper contextWrapper) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        return preferences.getString("expressCountry", "");
    }

    /**
     * 保存快递所有城镇信息
     *
     * @param contextWrapper
     * @param expressTownString
     */
    public static void saveAllTown(ContextWrapper contextWrapper, String expressTownString){
        String expressTownStr = null;
        try {
            expressTownStr = TaobaoSecurityEncryptor.encrypt(contextWrapper, expressTownString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        preferences.putString("expressTwon", expressTownStr);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取快递所有市信息
     *
     * @param contextWrapper
     * @return
     */
    public static String getAllTown(ContextWrapper contextWrapper){
        String decrypt;
        try {
            APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
            String ticketTypeStr = preferences.getString("expressTwon", "");
            decrypt = TaobaoSecurityEncryptor.decrypt(contextWrapper, ticketTypeStr);
            if (TextUtils.isEmpty(decrypt)) {
                return null;
            }
            return decrypt;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 保存快递所有街道信息
     *
     * @param contextWrapper
     * @param expressStreetString
     */
    public static void saveAllStreet(ContextWrapper contextWrapper, String expressStreetString) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        preferences.putString("expressStreet", expressStreetString);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取快递所有街道信息
     *
     * @param contextWrapper
     * @return
     */
    public static String getAllStreet(ContextWrapper contextWrapper) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        return preferences.getString("expressStreet", "");
    }

    /**
     * 保存快递省市、区县、城镇、街道信息
     *
     * @param contextWrapper
     * @param expressKdString
     */
    public static void saveAllKD(ContextWrapper contextWrapper, String expressKdString){
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        preferences.putString("expressKD", expressKdString);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取快递省市、区县、城镇、街道信息
     *
     * @param contextWrapper
     * @return
     */
    public static String getAllKD(ContextWrapper contextWrapper) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        return preferences.getString("expressKD", "");
    }


    /**
     * 保存广告版本号
     *
     * @param context
     * @param adVersion
     */
    public static void saveADVersion(ContextWrapper context, String adVersion) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        preferences.putString("ad_version_no", adVersion);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取广告版本号
     *
     * @param context
     * @return
     */
    public static String getADVersion(ContextWrapper context) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(context, STORAGE_NAME);
        return preferences.getString("ad_version_no", "");
    }


    /**
     * 保存广告信息
     *
     * @param contextWrapper
     * @param ADString
     */
    public static void saveAD(ContextWrapper contextWrapper, String ADString){
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        preferences.putString("AD", ADString);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取广告信息
     *
     * @param contextWrapper
     * @return
     */
    public static String getAD(ContextWrapper contextWrapper) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        return preferences.getString("AD", "");
    }

    /**
     * 保存是否需要推送
     *
     * @param contextWrapper
     * @param isNeedPush
     */
    public static void saveIsPush(ContextWrapper contextWrapper, boolean isNeedPush){
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        preferences.putBoolean("isNeedPush", isNeedPush);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 获取是否需要推送
     *
     * @param contextWrapper
     * @return
     */
    public static boolean getIsPush(ContextWrapper contextWrapper) {
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        return preferences.getBoolean("isNeedPush", true);
    }


    public static void saveBuyTicketInfo(ContextWrapper contextWrapper,String jsonStr) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        preferences.putString("buyTikcetInfo", jsonStr);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }
    public static String getBuyTicketInfo(ContextWrapper contextWrapper){
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        return preferences.getString("buyTikcetInfo","");
    }

    /**
     * 定位信息保存
     * @param contextWrapper
     * @param jsonStr
     */
    public static void saveLocation(ContextWrapper contextWrapper,String jsonStr) {
        final APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        preferences.putString("location", jsonStr);
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                preferences.commit();
            }
        });
    }

    /**
     * 定位信息获取
     * @param contextWrapper
     * @return
     */
    public static String getLocation(ContextWrapper contextWrapper){
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        return preferences.getString("location","");
    }

    /**
     * 通知红点保存
     * @param contextWrapper
     * @param isVisible
     */
    public static void saveIsVisible(ContextWrapper contextWrapper,boolean isVisible) {
        try {
            final APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
            preferences.putBoolean("isVisible", isVisible);
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    preferences.commit();
                }
            });
        }catch (Exception e){
//            e.printStackTrace();
        }
    }

    /**
     * 通知红点获取
     * @param contextWrapper
     * @return
     */
    public static boolean getIsVisible(ContextWrapper contextWrapper){
        APSharedPreferences preferences = SharedPreferencesManager.getInstance(contextWrapper, STORAGE_NAME);
        return preferences.getBoolean("isVisible",false);
    }



}
