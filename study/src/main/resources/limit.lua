local key = KEYS[1] -- 限流key 如一s一个
local limit = tonumber(ARGV[1]) -- 限流大小
local current = tonumber(redis.call("get", key) or "0")
if current + 1 > limit then
    return 0
else   -- 请求数+1， 设置2S过期时间
    redis.call('INCRBY', key,'1')
    redis.call('EXPIRE', key, '100')
    return 1
end
