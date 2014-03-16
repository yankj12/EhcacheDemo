package EhcacheDemo;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcacheDemo {
	
	private static CacheManager cacheManager = CacheManager.create("src/ehcache.xml");
	//private static CacheManager cacheManager = CacheManager.create(EhcacheDemo.class.getResource("/ehcache.xml")); 
	private static Cache cache = cacheManager.getCache("userCache");
	
	public static void main(String[] args) {
		System.out.println(work(3));//进行计算
		System.out.println(work(2));//进行计算
		System.out.println(work(3));//从缓存中读取
		clearAllCache();
		System.out.println(work(3));//进行计算
		
	}

	/**
	 * 获取cache的名字
	 */
	public static void testGetCacheNames(){
		String[] cacheNames = cacheManager.getCacheNames();
		for(int i=0;i<cacheNames.length;i++){
			System.out.println(cacheNames[i]);
		}
	}
	
	public static int work(int num){
		int result = 0;
		Element element = null;
		String cacheKey = generateKey(new Object[]{"work" , num});
		System.out.println(cacheKey);
		element = cache.get(cacheKey);
		if(element!=null){
			System.out.println("从缓存中读取");
			return (Integer)element.getObjectValue();
		}else{
			System.out.println("进行计算");
			result = num*num;
			cache.put(new Element(cacheKey , result));
			return result;			
		}

	}
	
	public static String generateKey(Object... args){
		if(args==null||args.length<=0){
			return "";
		}
		StringBuilder builder = new StringBuilder();
		for(int i=0;i<args.length;i++){
			if(args[i] != null){
				builder.append(args[i]);
			}else{
				builder.append("");
			}
			builder.append(".");
		}
		return builder.toString();
	}
	
	/**
	 * 清除所有Cache的缓存
	 */
	public static void clearAllCache(){
		String[] cacheNames = cacheManager.getCacheNames();
		for(int i=0;i<cacheNames.length;i++){
			cacheManager.getCache(cacheNames[i]).removeAll();
		}
	}
}
