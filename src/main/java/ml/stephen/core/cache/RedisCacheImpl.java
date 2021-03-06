package ml.stephen.core.cache;

import ml.stephen.core.utils.SerializeUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;

/**
 * Created by Stephen on 16/9/8.
 * Redis 缓存实现类
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RedisCacheImpl implements RedisCache {

	public RedisCacheImpl() {}

	private RedisTemplate<String, Object> redisTemplate;

	private int globalDbIndex = 0;

	@Override
	public Long deleteKeys(final Object dbIndex, final byte[] key) throws Exception {
		return this.redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				if (dbIndex != null) {
					connection.select((Integer) dbIndex);
				} else {
					connection.select(globalDbIndex);
				}
				return connection.del(key);
			}
		});
	}

	@Override
	public Long deleteKeys(final Object dbIndex, String key) throws Exception {
		return this.deleteKeys(dbIndex, key.getBytes());
	}

	@Override
	public String updateKey(final Object dbIndex, final byte[] key, final byte[] value, final Long expireSec) throws Exception {
		return (String) this.redisTemplate.execute(new RedisCallback<Object>() {
			public String doInRedis(final RedisConnection connection) throws DataAccessException {
				if (dbIndex != null) {
					connection.select((Integer) dbIndex);
				} else {
					connection.select(globalDbIndex);
				}
				connection.set(key, value);
				if (expireSec != null) {
					connection.expire(key, expireSec);
				}
				return new String(key);
			}
		});
	}

	@Override
	public Object updateKey(final Object dbIndex, String key, Object value, Long expireSec) throws Exception {
		return this.updateKey(dbIndex, key.getBytes(), SerializeUtil.serialize(value), expireSec);
	}
	
	@Override
	public Boolean updateKeyNX(final Object dbIndex, final byte[] key, final byte[] value, final Long expireSec) throws Exception {
		return this.redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(final RedisConnection connection) throws DataAccessException {
				if (dbIndex != null) {
					connection.select((Integer) dbIndex);
				} else {
					connection.select(globalDbIndex);
				}
				Boolean b = connection.setNX(key, value);
				if (b && expireSec != null) {
					connection.expire(key, expireSec);
				}
				return b;
			}
		});
	}

	@Override
	public Boolean updateKeyNX(final Object dbIndex, String key, Object value, Long expireSec) throws Exception {
		return this.updateKeyNX(dbIndex, key.getBytes(), SerializeUtil.serialize(value), expireSec);
	}

	@Override
	public Object getValue(final Object dbIndex, final byte[] key) throws Exception {
		return this.redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				if (dbIndex != null) {
					connection.select((Integer) dbIndex);
				} else {
					connection.select(globalDbIndex);
				}
				byte[] bs = connection.get(key);
				return SerializeUtil.unserialize(bs);
			}
		});
	}

	@Override
	public Object getValue(final Object dbIndex, String key) throws Exception {
		return this.getValue(dbIndex, key.getBytes());
	}

	@Override
	public Set getKeys(final Object dbIndex, final byte[] keys) throws Exception {
		return this.redisTemplate.execute(new RedisCallback<Set>() {
			public Set doInRedis(RedisConnection connection) throws DataAccessException {
				if (dbIndex != null) {
					connection.select((Integer) dbIndex);
				} else {
					connection.select(globalDbIndex);
				}
				Set<byte[]> setByte = connection.keys(keys);
				if (setByte == null || setByte.size() < 1) {
					return null;
				}
				Set set = new HashSet();
				for (byte[] key : setByte) {
					set.add(new String(key));
				}
				return set;
			}
		});
	}

	@Override
	public Set getKeys(final Object dbIndex, String keys) throws Exception {
		return this.getKeys(dbIndex, keys.getBytes());
	}

	@Override
	public Set getHashKeys(final Object dbIndex, final byte[] key) throws Exception {
		return this.redisTemplate.execute(new RedisCallback<Set>() {
			public Set doInRedis(RedisConnection connection) throws DataAccessException {
				if (dbIndex != null) {
					connection.select((Integer) dbIndex);
				} else {
					connection.select(globalDbIndex);
				}
				Set<byte[]> hKeys = connection.hKeys(key);
				if (hKeys == null || hKeys.size() > 1) {
					return null;
				}
				Set set = new HashSet();
				for (byte[] bs : hKeys) {
					set.add(SerializeUtil.unserialize(bs));
				}
				return set;
			}
		});
	}

	@Override
	public Set getHashKeys(final Object dbIndex, String key) throws Exception {
		return this.getHashKeys(dbIndex, key.getBytes());
	}

	@Override
	public Boolean updateHashValue(final Object dbIndex, final byte[] key, final byte[] field, final byte[] value) throws Exception {
		return this.redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				if (dbIndex != null) {
					connection.select((Integer) dbIndex);
				} else {
					connection.select(globalDbIndex);
				}
				Boolean hSet = connection.hSet(key, field, value);
				return hSet;
			}
		});
	}

	@Override
	public Boolean updateHashValue(final Object dbIndex, String key, String field, Object value) throws Exception {
		return this.updateHashValue(dbIndex, key.getBytes(), field.getBytes(), SerializeUtil.serialize(value));
	}

	@Override
	public Object getHashValue(final Object dbIndex, final byte[] key, final byte[] field) throws Exception {
		return this.redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				if (dbIndex != null) {
					connection.select((Integer) dbIndex);
				} else {
					connection.select(globalDbIndex);
				}
				byte[] hGet = connection.hGet(key, field);
				return SerializeUtil.unserialize(hGet);
			}
		});
	}

	@Override
	public Object getHashValue(final Object dbIndex, String key, String field) throws Exception {
		return this.getHashValue(dbIndex, key.getBytes(), field.getBytes());
	}

	@Override
	public Long deleteHashKeys(final Object dbIndex, final byte[] key, final byte[] field) throws Exception {
		return this.redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				if (dbIndex != null) {
					connection.select((Integer) dbIndex);
				} else {
					connection.select(globalDbIndex);
				}
				Long hDel = connection.hDel(key, field);
				return hDel;
			}
		});
	}

	@Override
	public Long deleteHashKeys(final Object dbIndex, String key, String field) throws Exception {
		return this.deleteHashKeys(dbIndex, key.getBytes(), field.getBytes());
	}

	@Override
	public Long getHashSize(final Object dbIndex, final byte[] key) throws Exception {
		return this.redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				if (dbIndex != null) {
					connection.select((Integer) dbIndex);
				} else {
					connection.select(globalDbIndex);
				}
				Long len = connection.hLen(key);
				return len;
			}
		});
	}

	@Override
	public Long getHashSize(final Object dbIndex, String key) throws Exception {
		return this.getHashSize(dbIndex, key.getBytes());
	}

	@Override
	public List getHashValues(final Object dbIndex, final byte[] key) throws Exception {
		return this.redisTemplate.execute(new RedisCallback<List>() {
			public List doInRedis(RedisConnection connection) throws DataAccessException {
				if (dbIndex != null) {
					connection.select((Integer) dbIndex);
				} else {
					connection.select(globalDbIndex);
				}
				List<byte[]> hVals = connection.hVals(key);

				if (hVals == null || hVals.size() < 1) {
					return null;
				}
				List list = new ArrayList();

				for (byte[] bs : hVals) {
					list.add(SerializeUtil.unserialize(bs));
				}
				return list;
			}
		});
	}

	@Override
	public List getHashValues(final Object dbIndex, String key) throws Exception {
		return this.getHashValues(dbIndex, key.getBytes());
	}

	@Override
	public Long getDbSize(final Object dbIndex) throws Exception {
		return this.redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				if (dbIndex != null) {
					connection.select((Integer) dbIndex);
				} else {
					connection.select(globalDbIndex);
				}
				Long len = connection.dbSize();
				return len;
			}
		});
	}

	@Override
	public void flushDb(final Object dbIndex) throws Exception {
		this.redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				if (dbIndex != null) {
					connection.select((Integer) dbIndex);
				} else {
					connection.select(globalDbIndex);
				}
				connection.flushDb();
				return null;
			}
		});
	}
	
	@Override
	public void flushAll() throws Exception {
		this.redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				connection.flushAll();
				return null;
			}
		});
	}

	@Override
	public void flushAllRetainSession() throws Exception {
		this.redisTemplate.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) throws DataAccessException {
				connection.select(globalDbIndex);
				Set<byte[]> keys = connection.keys("cache_session_*".getBytes());
				
				Map<byte[], byte[]> cacheMap = new HashMap<byte[], byte[]>();
				for (byte[] key : keys) {
					byte[] value = connection.get(key);
					cacheMap.put(key, value);
				}
				connection.flushAll();
				
				for (Map.Entry<byte[], byte[]> entry : cacheMap.entrySet()) {
					connection.setEx(entry.getKey(), 86400, entry.getValue());
				}
				return null;
			}
		});
	}
	
	public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public int getGlobalDbIndex() {
		return globalDbIndex;
	}

	public void setGlobalDbIndex(int globalDbIndex) {
		this.globalDbIndex = globalDbIndex;
	}

}
