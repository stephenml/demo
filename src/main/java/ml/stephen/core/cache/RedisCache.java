package ml.stephen.core.cache;

import java.util.List;
import java.util.Set;

/**
 * Created by Stephen on 16/9/8.
 * Redis 缓存接口
 */
@SuppressWarnings("rawtypes")
public interface RedisCache {

	/**
	 * 删除给定的一个或多个 key 不存在的 key 会被忽略
	 * @param dbIndex
	 * @param key
	 * @return 被删除 key 的数量
	 * @throws Exception
     */
	Long deleteKeys(Object dbIndex, byte[] key) throws Exception;
	Long deleteKeys(Object dbIndex, String key) throws Exception;
	
	/**
	 * 将字符串值 value 关联到 key 如果 key 已经持有其他值 SET 就覆写旧值 无视类型
	 * 并为给定 key 设置生存时间(s) 当 key 过期时(生存时间为 0) 它会被自动删除
	 * @param dbIndex
	 * @param key
	 * @param value
	 * @param expireSec null为不设置过期时间 -1为立即过期
	 * @return
     * @throws Exception
     */
	Object updateKey(Object dbIndex, byte[] key, byte[] value, Long expireSec) throws Exception;
	Object updateKey(Object dbIndex, String key, Object value, Long expireSec) throws Exception;

	/**
	 * 将字符串值 value 关联到 key 如果 key 已经持有其他值 SETNX SET if Not eXists
	 * 并为给定 key 设置生存时间(s) 当 key 过期时(生存时间为 0) 它会被自动删除
	 * @param dbIndex
	 * @param key
	 * @param value
	 * @param expireSec null为不设置过期时间 -1为立即过期
	 * @return
     * @throws Exception
     */
	Boolean updateKeyNX(Object dbIndex, byte[] key, byte[] value, Long expireSec) throws Exception;
	Boolean updateKeyNX(Object dbIndex, String key, Object value, Long expireSec) throws Exception;
	
	/**
	 * 返回 key 所关联的字符串值
	 * @param dbIndex
	 * @param key
	 * @return 当 key 不存在时 返回 nil 否则返回 key 的值
	 * @throws Exception
     */
	Object getValue(Object dbIndex, byte[] key) throws Exception;
	Object getValue(Object dbIndex, String key) throws Exception;

	/**
	 * 查找所有符合给定模式 pattern 的 key <br />
	 * KEYS * 匹配数据库中所有 key <br />
	 * KEYS h?llo 匹配 hello, hallo 和 hxllo 等 <br />
	 * KEYS h*llo 匹配 hllo 和 heeeeello 等 <br />
	 * KEYS h[ae]llo 匹配 hello 和 hallo, 但不匹配 hillo <br />
	 * @param dbIndex
	 * @param keys
	 * @return 符合给定模式的 key 列表
	 * @throws Exception
     */
	Set getKeys(Object dbIndex, byte[] keys) throws Exception;
	Set getKeys(Object dbIndex, String keys) throws Exception;

	/**
	 * 返回哈希表 key 中的所有域
	 * @param dbIndex
	 * @param key
	 * @return 一个包含哈希表中所有域的表 当 key 不存在时 返回一个空表
	 * @throws Exception
     */
	Set getHashKeys(Object dbIndex, byte[] key) throws Exception;
	Set getHashKeys(Object dbIndex, String key) throws Exception;

	/**
	 * 将哈希表 key 中的域 field 的值设为 value <br />
	 * 如果 key 不存在 一个新的哈希表被创建并进行 HSET 操作 <br />
	 * 如果域 field 已经存在于哈希表中 旧值将被覆盖
	 * @param dbIndex
	 * @param key
	 * @param field
	 * @param value
	 * @return 如果 field 是哈希表中的一个新建域 并且值设置成功 返回 1 <br/>
	 * 		   如果哈希表中域 field 已经存在且旧值已被新值覆盖 返回 0
     * @throws Exception
     */
	Boolean updateHashValue(Object dbIndex, byte[] key, byte[] field, byte[] value) throws Exception;
	Boolean updateHashValue(Object dbIndex, String key, String field, Object value) throws Exception;

	/**
	 * 返回哈希表 key 中给定域 field 的值
	 * @param dbIndex
	 * @param key
	 * @param field
	 * @return 给定域的值 当给定域不存在或是给定 key 不存在时 返回 nil
     * @throws Exception
     */
	Object getHashValue(Object dbIndex, byte[] key, byte[] field) throws Exception;
	Object getHashValue(Object dbIndex, String key, String field) throws Exception;
	
	/**
	 * 删除哈希表 key 中的一个或多个指定域 不存在的域将被忽略
	 * @param dbIndex
	 * @param key
	 * @param field
	 * @return 被成功移除的域的数量 不包括被忽略的域
     * @throws Exception
     */
	Long deleteHashKeys(Object dbIndex, byte[] key, byte[] field) throws Exception;
	Long deleteHashKeys(Object dbIndex, String key, String field) throws Exception;
	
	/**
	 * 返回哈希表 key 中域的数量
	 * @param dbIndex
	 * @param key
	 * @return 哈希表中域的数量 当 key 不存在时 返回 0
	 * @throws Exception
     */
	Long getHashSize(Object dbIndex, byte[] key) throws Exception;
	Long getHashSize(Object dbIndex, String key) throws Exception;

	/**
	 * 返回哈希表 key 中所有域的值
	 * @param dbIndex
	 * @param key
	 * @return 一个包含哈希表中所有值的表 当 key 不存在时 返回一个空表
	 * @throws Exception
     */
	List getHashValues(Object dbIndex, byte[] key) throws Exception;
	List getHashValues(Object dbIndex, String key) throws Exception;
	
	/**
	 * 返回当前数据库的 key 的数量
	 * @param dbIndex
	 * @return 当前数据库的 key 的数量
	 * @throws Exception
     */
	Long getDbSize(Object dbIndex) throws Exception;

	/**
	 * 清空当前数据库
	 * @param dbIndex
	 * @throws Exception
     */
	void flushDb(Object dbIndex) throws Exception;

	/**
	 * 清空所有数据库
	 * @throws Exception
     */
	void flushAll() throws Exception;

	/**
	 * 清空所有数据库 留下cache_session (预留)
	 * @throws Exception
     */
	void flushAllRetainSession() throws Exception;

}
