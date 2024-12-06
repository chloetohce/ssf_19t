package sg.edu.nus.iss.ssf_19t.repository;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MapRepository {

	@Autowired
	RedisTemplate<String, String> redisTemplate;

	public void create(String redisKey, String hashKey, String hashValue) {
		redisTemplate.opsForHash().put(redisKey, hashKey, hashValue);
	}

	public Object get(String redisKey, String hashKey) {
		return redisTemplate.opsForHash().get(redisKey, hashKey);
	}

	public long delete(String redisKey, String hashKey) {
		return redisTemplate.opsForHash().delete(redisKey, hashKey);
	}

	public boolean hasKey(String redisKey, String hashKey) {
		return redisTemplate.opsForHash().hasKey(redisKey, hashKey);
	}

	public Map<Object, Object> entries(String redisKey) {
		return redisTemplate.opsForHash().entries(redisKey);
	}

	public Set<Object> getKeys(String redisKey) {
		return redisTemplate.opsForHash().keys(redisKey);
	}

	public List<Object> getValues(String redisKey) {
		return redisTemplate.opsForHash().values(redisKey);
	}

	public long size(String redisKey) {
		return redisTemplate.opsForHash().size(redisKey);
	}

}
