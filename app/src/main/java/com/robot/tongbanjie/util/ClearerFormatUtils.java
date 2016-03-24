package com.robot.tongbanjie.util;

/**
 * 清晰格式工具类 
 */
public final class ClearerFormatUtils {
	public static final char FORMAT_CHAR_HOLDER_PLACE = '#';  /** 格式占位符 */
	public static final char FORMAT_CHAR_SPLITER = '-';  /** 格式分隔符 */
	public static final char DEFAULT_CHAR_SPLITER = ' ';  /** 默认占位符 */
	
	public static final String FORMAT_CLEAR_PHONE = "###-####-####";
	public static final String FORMAT_CLEAR_BANK_CARD = "####-####-####-####-####-####";
	
	private ClearerFormatUtils() {
		
	}
	
	public static String getPhone(final String content) {
		return getPhone(content, FORMAT_CLEAR_PHONE, DEFAULT_CHAR_SPLITER);
	}
	
	public static String getPhone(final String content, final String phoneFormat) {
		return getPhone(content, phoneFormat, DEFAULT_CHAR_SPLITER);
	}
	
	/**
	 * 获取清晰格式的手机号码
	 * @param content
	 * @param phoneFormat  自定义输入格式
	 * @param spliterChar  自定义分隔符
	 * @return
	 */
	public static String getPhone(final String content, final String phoneFormat, final char spliterChar) {
		return getFormat(content, phoneFormat, spliterChar);
	}
	
	public static String getBankCard(final String content) {
		return getBankCard(content, FORMAT_CLEAR_BANK_CARD, DEFAULT_CHAR_SPLITER);
	}
	
	public static String getBankCard(final String content, final String bankCardFormat) {
		return getBankCard(content, bankCardFormat, DEFAULT_CHAR_SPLITER);
	}
	
	/**
	 * 获取清晰格式的银行卡号
	 * @param content
	 * @param bankCardFormat  自定义输入格式
	 * @param spliterChar  自定义分隔符
	 * @return
	 */
	public static String getBankCard(final String content, final String bankCardFormat, final char spliterChar) {
		return getFormat(content, bankCardFormat, spliterChar);
	}
	
	public static String getFormat(final String content, final String format, final char spliterChar) {
		if (content == null && "".equals(content)) {
			return content;
		}
		validateFormat(format);
		
		int formatLen = format.length();
		StringBuilder ret = new StringBuilder(content);
		
		for (int i = 0; i < ret.length(); i++) {
			if (i <= formatLen - 1) {
				char formatCh = format.charAt(i);
				if (formatCh == FORMAT_CHAR_SPLITER) {
					ret.insert(i, spliterChar);
				}
			}
		}
		return ret.toString();
	}
	
	/**
	 * 验证格式是否正确
	 * @param formatStr
	 */
	private static void validateFormat(final String formatStr) {
		if (formatStr == null && "".equals(formatStr)) {
			throw new IllegalArgumentException("The formatStr must be not empty!");
		}
		
		for (int i = 0; i < formatStr.length(); i++) {
			if (formatStr.charAt(i) != FORMAT_CHAR_HOLDER_PLACE && formatStr.charAt(i) != FORMAT_CHAR_SPLITER) {
				throw new IllegalArgumentException("The string must include only '#' or '-' char!");
			}
		}
	}
}