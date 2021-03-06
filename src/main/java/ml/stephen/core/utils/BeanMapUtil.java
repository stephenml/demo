package ml.stephen.core.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Stephen on 16/9/13.
 */
public class BeanMapUtil {

	public static final char UNDERLINE = '_';
	private static Logger logger = LoggerFactory.getLogger(BeanMapUtil.class);

	/**
	 * 将一个 JavaBean 对象转化为一个 Map 并把Date类型字段format成字符串
	 * @param bean
	 * @param sdf
     * @return
     */
	public static Map<String, Object> toMap(Object bean, SimpleDateFormat sdf) {
		Map<String, Object> rtn = new HashMap<String, Object>();
		if (bean == null) {
			return rtn;
		}
		Field[] fields = bean.getClass().getDeclaredFields();
		for(Field field : fields) {
			field.setAccessible(true);
			String key = field.getName();
			Object value = null;
			try {
				value = field.get(bean);
			} catch (Exception e) {
				logger.warn(key + "属性未能正确取到值");
				e.printStackTrace();
			}
			if (value instanceof Date && sdf != null) {
				value = sdf.format(value);
			}
			rtn.put(camelToUnderline(key), value);
		}
		return rtn;
	}

	/**
	 * 将 javabean 转为 map 内部调用toMap
	 * @param bean
	 * @return
     */
	public static Map<String, Object> toMap(Object bean) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return toMap(bean, sdf);
	}

	/**
	 * 将 map 转为 javabean
	 * @param map 驼峰命名key的map
	 * @param cls javabean
	 * @param sdf 日期格式
	 * @param writeNullNumberAsZero 是否将 数字类型null转为0
	 * @param <T>
     * @return
     */
	public static <T> T toBean(Map<String, Object> map, Class<T> cls, final SimpleDateFormat sdf, boolean writeNullNumberAsZero) {
		T bean = null;
		try {
			bean = cls.newInstance();
		} catch (Exception e) {
			logger.error("创建实例失败");
			throw new RuntimeException(e);
		}

		if (map == null) {
			return bean;
		}

		try {
			ConvertRegister(sdf, writeNullNumberAsZero);
			BeanUtils.populate(bean, map);
		} catch (Exception e) {
			logger.error("toBean Error 转换失败 " + e);
			throw new RuntimeException(e);
		}
		return bean;
	}

	/**
	 * 将 map 转为 javabean 内部调用toBean
	 * @param map
	 * @param cls
	 * @param <T>
     * @return
     */
	public static <T> T toBean(Map<String, Object> map, Class<T> cls) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return toBean(map, cls, sdf, false);
	}

	private static void ConvertRegister(final SimpleDateFormat sdf, boolean writeNullNumberAsZero) {
		ConvertUtils.deregister();
		if (sdf != null) {
			Converter converter = new Converter() {
				@Override
				public Object convert(Class type, Object value) {
					if (value == null)
						return null;
					if (value instanceof Long) {
						try {
							return new Date((Long) value);
						} catch (Exception e) {
							throw new RuntimeException(e); /** 异常链不能断 */
						}
					} else if (value instanceof String) {
						try {
							return sdf.parse((String) value);
						} catch (ParseException e) {
							return null;
//							throw new RuntimeException(e); /** 异常链不能断 */
						}
					} else if (value instanceof Date) {
						return value;
					} else {
						logger.error("不是日期类型");
						throw new ConversionException("不是日期类型");
					}
				}
			};
			ConvertUtils.register(converter, Date.class);
		}
		;
		if (!writeNullNumberAsZero) {
			Converter converter = new Converter() {
				@Override
				public Object convert(Class type, Object value) {
					if (value == null || "".equals(value))
						return null;
					try {
						value = String.valueOf(value);
						if (type.equals(Integer.class)
								|| type.equals(Long.class)
								|| type.equals(Short.class)
								|| type.equals(Byte.class)
								) {
							value = ((String)value).replaceFirst("\\.\\d*", "");
						}
						value = type.getConstructor(String.class).newInstance(value);
					} catch (Exception e) {
						logger.error("不是数字类型");
						throw new ConversionException("不是数字类型" + " type:" + type + " value" + value);
					}
					return value;
				}
			};
			ConvertUtils.register(converter, Integer.class);
			ConvertUtils.register(converter, Long.class);
			ConvertUtils.register(converter, Short.class);
			ConvertUtils.register(converter, Byte.class);
			ConvertUtils.register(converter, Float.class);
			ConvertUtils.register(converter, Double.class);
			ConvertUtils.register(converter, BigDecimal.class);
		}
		;
	}

	/**
	 * 驼峰命名 to 下划线命名
	 * @param param
	 * @return
     */
	public static String camelToUnderline(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(UNDERLINE);
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	/**
	 * 下划线命名 to 驼峰命名
	 * @param param
	 * @return
     */
	public static String underlineToCamel(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		StringBuilder sb = new StringBuilder(param);
		Matcher mc = Pattern.compile("_").matcher(param);
		int i = 0;
		while (mc.find()) {
			int position = mc.end() - (i++);
			sb.replace(position - 1, position + 1, sb.substring(position, position + 1).toUpperCase());
		}
		return sb.toString();
	}

}
