package com.robot.tongbanjie.util;

/**
 * 隐藏格式工具类 
 */
public final class HiderFormatUtils {
	public static final char DEFAULT_CHAR_HIDER = '*';  /** 默认隐藏符 */
	public static final HidePolicy DEFAULT_HIDE_POLICY = HidePolicy.End;
	
	public static final int FORMAT_NAME_START_REMAIN_CNT = 1;  /** 前面保留1位 */
	public static final int FORMAT_NAME_END_REMAIN_CNT = 1;  /** 后面保留1位 */
	
	public static final int FORMAT_PHONE_START_REMAIN_CNT = 3;  /** 前面保留3位 */
	public static final int FORMAT_PHONE_END_REMAIN_CNT = 4;  /** 后面保留4位 */
	
	public static final int FORMAT_BANKCARD_START_REMAIN_CNT = 4;  /** 前面保留3位 */
	public static final int FORMAT_BANKCARD_END_REMAIN_CNT = 4;  /** 后面保留4位 */
	/**
	 * 隐藏策略，以前面为主或以后面为主
	 */
	public static enum HidePolicy {
		Start, End
	}
	
	private HiderFormatUtils() {
		
	}
	
	public static String getName(final String content) {
		return getName(content, FORMAT_NAME_START_REMAIN_CNT, FORMAT_NAME_END_REMAIN_CNT);
	}
	
	/**
	 * 获取隐藏格式的姓名
	 * @param content
	 * @param startRemainCnt  开头保留多少位
	 * @param endRemainCnt  结尾保留多少位
	 * @return
	 */
	public static String getName(final String content, final int startRemainCnt, final int endRemainCnt) {
		return getFormat(content, startRemainCnt, endRemainCnt);
	}
	
	public static String getPhone(final String content) {
		return getPhone(content, FORMAT_PHONE_START_REMAIN_CNT, FORMAT_PHONE_END_REMAIN_CNT);
	}
	
	/**
	 * 获取隐藏格式的手机号码
	 * @param content
	 * @param startRemainCnt  开头保留多少位
	 * @param endRemainCnt  结尾保留多少位
	 * @return
	 */
	public static String getPhone(final String content, final int startRemainCnt, final int endRemainCnt) {
		return getBankCard(content, startRemainCnt, endRemainCnt);
	}
	
	public static String getBankCard(final String content) {
		return getFormat(content, FORMAT_BANKCARD_START_REMAIN_CNT, FORMAT_BANKCARD_END_REMAIN_CNT);
	}
	
	/**
	 * 获取隐藏格式的银行卡号
	 * @param content
	 * @param startRemainCnt  开头保留多少位
	 * @param endRemainCnt  结尾保留多少位
	 * @return
	 */
	public static String getBankCard(final String content, final int startRemainCnt, final int endRemainCnt) {
		return getFormat(content, startRemainCnt, endRemainCnt);
	}
	
	public static String getFormat(final String content, final int startRemainCnt, final int endRemainCnt) {
		return getFormat(content, startRemainCnt, endRemainCnt, DEFAULT_CHAR_HIDER, DEFAULT_HIDE_POLICY);
	}	
	
	public static String getFormat(final String content, final int startRemainCnt, final int endRemainCnt, final char hiderChar) {
		return getFormat(content, startRemainCnt, endRemainCnt, hiderChar, DEFAULT_HIDE_POLICY);
	}				
	
	/**
	 * 
	 * @param content
	 * @param startRemainCnt  开头位置保留位数
	 * @param endRemainCnt  结尾保留位数
	 * @param hiderChar  隐藏符
	 * @param hidePolicy  隐藏策略
	 * @return
	 */
	public static String getFormat(final String content, final int startRemainCnt, final int endRemainCnt, final char hiderChar, HidePolicy hidePolicy) {
		if (content == null && "".equals(content)) {
			return content;
		}
		
		validateFormat(startRemainCnt, endRemainCnt);
		
		if (startRemainCnt >= content.length() || endRemainCnt >= content.length()) {
			return content;  
		}
		
		StringBuilder ret = new StringBuilder();
		int contentLen = content.length();
		if (contentLen > startRemainCnt + endRemainCnt) {
			for (int i = 0; i < contentLen; i++) {
				if (i < startRemainCnt) {
					ret.append(content.charAt(i));
				} else if (i >= contentLen - endRemainCnt) {
					ret.append(content.charAt(i));
				} else {
					ret.append(hiderChar);
				}
			}
		} else {
			for (int i = 0; i < contentLen; i++) {
				if (hidePolicy == HidePolicy.End) {  // 隐藏时以后面为主，startRemainCnt为1，endRemainCnt为1时，比如姓名：陈明--->*明
					if (i >= contentLen - endRemainCnt) {
						ret.append(content.charAt(i));
					} else {
						ret.append(hiderChar);
					}
				} else {  // 隐藏时以前面为主，startRemainCnt为1，endRemainCnt为1时，比如姓名：陈明--->陈*
					if (i < startRemainCnt) {
						ret.append(content.charAt(i));
					} else {
						ret.append(hiderChar);
					}
				}
			}
		}
		
		return ret.toString();
	}
	
	/**
	 * 验证格式是否正确
	 * @param startRemainCnt
	 * @param endRemainCnt
	 */
	private static void validateFormat(final int startRemainCnt, final int endRemainCnt) {
		if (startRemainCnt < 0 || endRemainCnt < 0) {
			throw new IllegalArgumentException("The startRemainCnt and endRemainCnt must greater or equal 0!");
		}
	}
}