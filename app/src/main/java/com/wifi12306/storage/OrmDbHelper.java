package com.wifi12306.storage;

import android.content.Context;
import android.util.Log;

import com.alibaba.sqlcrypto.DatabaseErrorHandler;
import com.alibaba.sqlcrypto.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.List;

/**
 * Created by mulei on 2017/9/4.
 */
// 如果需要加密的话，需要继承OrmLiteSqliteOpenHelper
public class OrmDbHelper extends OrmLiteSqliteOpenHelper {

    public final static String name = "perry.db";
    private static final String TAG = "OrmDbHelper";

    public OrmDbHelper(Context context) {
//        this(context, name, null, 1);
        this(context,name,null,1);
//        try {
//            // 数据库密码进行加密，这样更安全
//            String useruser = TaobaoSecurityEncryptor.decrypt(new ContextWrapper(context), "useruser");
//            // 进行数据库整库的加密
//            setPassword(useruser);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public OrmDbHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion) {
        super(context, databaseName, factory, databaseVersion);
    }

    public OrmDbHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int databaseVersion, DatabaseErrorHandler errorHandler) {
        super(context, databaseName, factory, databaseVersion, errorHandler);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
//        try {
////            TableUtils.createTableIfNotExists(connectionSource, User.class);
////            TableUtils.createTableIfNotExists(connectionSource, SysNewCacheBean.class);//应用缓存数据
////            TableUtils.createTableIfNotExists(connectionSource, BookSwiperBean.class);//首页内容模块存储
//            TableUtils.createTableIfNotExists(connectionSource, CacheStationDTO.class);//车站列表需要缓存数据
//        } catch (SQLException e) {
//            e.printStackTrace();
//            log("创建表挂了");
//        }

    }

    /**
     * 在 增 改  的时候需要先调用的方法；
     * 在 删 查 的时候不需要调用此方法；
     * 如果不存在 删 和查就是空的；但 增和改就需要新建表了
     * @param clazz
     * @param <T>
     * @return
     */
    private <T>boolean initTable(Class<T> clazz){
        try {
            int code = TableUtils.createTableIfNotExists(connectionSource, clazz);
            log("initTable code:"+code);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            log("创建表挂了");
            return false;
        }
    }
    private void log(String s) {
        Log.i(TAG,s);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i1) {
      // 如果数据表升级的话，那么需要在这里进行迁移


    }

    private static OrmDbHelper sIns;

    public static OrmDbHelper getInstance(Context context){
        if (sIns==null){
            synchronized (OrmDbHelper.class){
                if (sIns==null){
                    sIns = new OrmDbHelper(context);
                }
            }
        }
        return sIns;
    }

    /**
     * 增或者更新数据
     * @param clazz 插入的数据模型
     * @param cache
     * @param <T>
     * @return 是否成功
     */
    public <T>boolean save(Class<T> clazz,T cache){
        initTable(clazz);
        Dao<T, ?> dao;
        try {
            dao = getDao(clazz);
            Dao.CreateOrUpdateStatus orUpdate = dao.createOrUpdate(cache);
            return orUpdate.isCreated() || orUpdate.isUpdated();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 增或者更新数据
     * @param clazz
     * @param cacheList
     * @param <T>
     * @return
     */
    public <T>boolean save(Class<T> clazz,List<T> cacheList){
        initTable(clazz);
        Dao<T, ?> dao;
        // ORMLite的数据连接封装类
        AndroidDatabaseConnection connection = new AndroidDatabaseConnection(this.getWritableDatabase(), true);
        Savepoint savepoint = null;
        try {
            savepoint = connection.setSavePoint("savepoint");//设置回滚点 名称
            dao = getDao(clazz);
            dao.setAutoCommit(connection,false);
            for(T temp:cacheList) {
                 dao.createOrUpdate(temp);
            }
            connection.commit(savepoint);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback(savepoint); //回滚事物
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return false;
        }
    }

    /**
     * 增或者更新数据
     * 特别耗时；每次都进行提交
     * @param clazz
     * @param cacheList
     * @param <T>
     * @return
     */
    @Deprecated
    public <T>boolean saveAll(Class<T> clazz,List<T> cacheList){
        initTable(clazz);
        Dao<T, ?> dao;
        // ORMLite的数据连接封装类
        try {
            dao = getDao(clazz);
            for(T temp:cacheList) {
                dao.createOrUpdate(temp);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 清除某张表的数据
     * @param clazz
     * @param <T>
     * @return
     */
    public <T>boolean clearTable(Class<T> clazz){
        try {
            int code = TableUtils.clearTable(connectionSource,clazz);
            log("clearTable code:"+code);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public <T>int delete(Class<T> clazz,T cache){
        Dao<T, ?> dao;
        try {
            dao = getDao(clazz);
            return dao.delete(cache);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * 查询
     * @param clazz
     * @param <T>
     * @return
     */
    public <T>List<T> query(Class<T> clazz){
        try {
            Dao<T, ?> dao = getDao(clazz);
            return dao.queryForAll();
//            return dao.queryBuilder().where().ne("generatedId", "-1").query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 获得Dao
     *
     * @return
     */
    public <T>Dao<T, ?> getClassDao(Class<T> clazz) {
        Dao<T,?> dao = null;
        try {
            dao = getDao(clazz);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dao;
    }

    /**
     * 查询单个 bean
     * @param clazz
     * @param queryStr bean中字段
     * @param value    bean中字段的值
     * @param <T>
     * @return
     */
    public <T> T queryForFirst(Class<T> clazz, String queryStr, String value) {
        try {
            Dao<T, ?> dao = getDao(clazz);
            return dao.queryBuilder().where().eq(queryStr,value).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询满足某个条件的所有的数据 bean
     * @param clazz
     * @param queryStr bean中字段
     * @param value    bean中字段的值
     * @param <T>
     * @return
     */
    public <T>List<T>  queryForAll(Class<T> clazz, String queryStr, Object  value) {
        try {
            Dao<T, ?> dao = getDao(clazz);
            return dao.queryBuilder().where().eq(queryStr,value).query();
        } catch (SQLException e) {
//            e.printStackTrace();
            log("queryForAll SQLException:"+e.toString());
        }
        return null;
    }

    /**
     * 查询满足某个条件的所有的数据 bean
     * @param clazz
     * @param queryStr bean中字段
     * @param startTime    bean中字段的值
     *
     * @param <T>
     * @return
     */
    public <T>List<T> queryForAll(Class<T> clazz, String queryStr, long startTime,long endTime) {
        try {
            Dao<T, ?> dao = getDao(clazz);
            return dao.queryBuilder().where().between(queryStr,startTime,endTime).query();
        } catch (SQLException e) {
//            e.printStackTrace();
            log("数据库查询异常:"+e.toString());
        }
        return null;
    }

    /**
     * 删除小于满足某个条件的所有的数据 bean
     * @param clazz
     * @param queryStr bean中字段
     * @param value    bean中字段的值
     * @param <T>
     * @return
     */
    public <T>int  deleteForLe(Class<T> clazz, String queryStr, Object  value) {
        try {
            Dao<T, ?> dao = getDao(clazz);
            DeleteBuilder deleteBuilder = dao.deleteBuilder();
            deleteBuilder.where().le(queryStr,value);//小于
            return deleteBuilder.delete();
//            return dao.deleteBuilder().where().ge(queryStr,value).query();//大于
        } catch (SQLException e) {
//            e.printStackTrace();
            log("数据库删除异常:"+e.toString());
        }
        return 0;
    }

}
