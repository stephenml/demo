package ml.stephen.constant;

/**
 * Created by Stephen on 16/9/8.
 */
public interface ServiceConstants extends Constants {

    /** redis 相关 */
    String INIT_REDIS = "redis";
    String INIT_ALL = "all";

    /*** 表数据缓存 redis索引 */
    Integer CACHE_TABLE_DBINDEX = 1;

    String CACHE_TABLE_MENU = "table_menu";
    String CACHE_TABLE_FRAMEWORK = "table_framework";

}
