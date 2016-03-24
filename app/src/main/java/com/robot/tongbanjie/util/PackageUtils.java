package com.robot.tongbanjie.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Log;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 *
 */
public class PackageUtils {
	private static final String TAG = PackageUtils.class.getSimpleName();
	private PackageUtils() {

	}

	/** 根据packageName获取packageInfo */
	public static PackageInfo getPackageInfo(Context context, String packageName) {
		if (null == context) {
			return null;
		}
		if (TextUtils.isEmpty(packageName)) {
			packageName = context.getPackageName();
		}
		PackageInfo info = null;
		PackageManager manager = context.getPackageManager();
		// 根据packageName获取packageInfo
		try {
			info = manager.getPackageInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
		} catch (PackageManager.NameNotFoundException e) {
			Log.e(TAG, e.toString());
		}
		return info;
	}

	/** 获取本应用的VersionCode */
	public static int getVersionCode(Context context) {
		PackageInfo info = getPackageInfo(context, null);
		if (info != null) {
			return info.versionCode;
		} else {
			return -1;
		}
	}

	/** 获取本应用的VersionName */
	public static String getVersionName(Context context) {
		PackageInfo info = getPackageInfo(context, null);
		if (info != null) {
			return info.versionName;
		} else {
			return null;
		}
	}

	/** 判断是否是第三方软件 */
	public static boolean isThirdPartyApp(Context context, String packageName) {
		if (null == context) {
			return false;
		}
		PackageManager pm = context.getPackageManager();
		PackageInfo packageInfo;
		try {
			packageInfo = pm.getPackageInfo(packageName, 0);
			return isThirdPartyApp(packageInfo);
		} catch (PackageManager.NameNotFoundException e) {
			Log.e(TAG, e.toString());
			return false;
		}
	}

	/** 判断是否是第三方软件 */
	public static boolean isThirdPartyApp(PackageInfo packageInfo) {
		if (null == packageInfo || null == packageInfo.applicationInfo) {
			return false;
		}
		return ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) || ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0);
	}

	/** 读取指定路径下APK文件签名 */
	@SuppressWarnings({"unchecked", "resource"})
	public static String getJarSignature(String filePath) throws Exception {
		if (null == filePath) {
			return null;
		}
		String resultSign = "";
		String resultKey = "";
		List<ZipEntry> names = new ArrayList<ZipEntry>();
		ZipFile zf = new ZipFile(filePath);
		Enumeration<ZipEntry> zi = (Enumeration<ZipEntry>) zf.entries();
		while (zi.hasMoreElements()) {
			ZipEntry ze = zi.nextElement();
			String name = ze.getName();
			if (name.startsWith("META-INF/") && (name.endsWith(".RSA") || name.endsWith(".DSA"))) {
				names.add(ze);
			}
		}
		Collections.sort(names, new Comparator<ZipEntry>() {
			@Override
			public int compare(ZipEntry obj1, ZipEntry obj2) {
				if (obj1 != null && obj2 != null) {
					return obj1.getName().compareToIgnoreCase(obj2.getName());
				}
				return 0;
			}
		});
		for (ZipEntry ze : names) {
			InputStream is = zf.getInputStream(ze);
			try {
				CertificateFactory cf = CertificateFactory.getInstance("X.509");
				CertPath cp = cf.generateCertPath(is, "PKCS7");
				List<?> list = cp.getCertificates();
				for (Object obj : list) {
					if (!(obj instanceof X509Certificate)) continue;
					X509Certificate cert = (X509Certificate) obj;
					StringBuilder builder = new StringBuilder();
					builder.setLength(0);
					byte[] key = getPKBytes(cert.getPublicKey());
					for (byte aKey : key) {
						builder.append(String.format("%02X", aKey));
					}
					resultKey += builder.toString();
					builder.setLength(0);
					byte[] signature = cert.getSignature();

					for (byte aSignature : signature) {
						builder.append(String.format("%02X", aSignature));
					}
					resultSign += builder.toString();
				}
			} catch (CertificateException e) {
				Log.e(TAG, e.toString());
			}
			is.close();
		}
		if (!TextUtils.isEmpty(resultKey) && !TextUtils.isEmpty(resultSign)) {
			return hashCode(resultKey) + "," + hashCode(resultSign);
		}
		return null;
	}

	/** 根据公钥获取key */
	private static byte[] getPKBytes(PublicKey pk) {
		if (pk instanceof RSAPublicKey) {
			RSAPublicKey k = (RSAPublicKey) pk;
			return k.getModulus().toByteArray();
		} else if (pk instanceof DSAPublicKey) {
			DSAPublicKey k = (DSAPublicKey) pk;
			return k.getY().toByteArray();
		}
		return null;
	}

	/** 计算签名时的hashcode算法 */
	public static int hashCode(String str) {
		int hash = 0;
		if (str != null) {
			int multiplier = 1;
			int offset = 0;
			int count = str.length();
			char[] value = new char[count];
			str.getChars(offset, count, value, 0);
			for (int i = offset + count - 1; i >= offset; i--) {
				hash += value[i] * multiplier;
				int shifted = multiplier << 5;
				multiplier = shifted - multiplier;
			}
		}
		return hash;
	}

	/** 通过包名读取已安装APP数字签名 */
	public static String getInstalledPackageSignature(Context context, String packageName) {
		if (null == context) {
			return null;
		}
		String signature = null;
		try {
			PackageManager pm = context.getPackageManager();
			ApplicationInfo appInfo = pm.getApplicationInfo(packageName, PackageManager.GET_SIGNATURES);
			String apkPath = appInfo.sourceDir;
			signature = getJarSignature(apkPath);
		} catch (PackageManager.NameNotFoundException e) {
			Log.e(TAG, e.toString());
		} catch (Exception e) {
			Log.e(TAG, e.toString());
		}
		return signature;
	}

	/** 获取指定路径的apk的资源 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	public static Resources getAPKResources(Context context, String apkPath) throws Exception {
		if (null == context) {
			return null;
		}
		String PathAssetManager = "android.content.res.AssetManager";
		Class assetMagCls = Class.forName(PathAssetManager);
		Constructor assetMagCt = assetMagCls.getConstructor((Class[]) null);
		Object assetMag = assetMagCt.newInstance((Object[]) null);
		Class[] typeArgs = new Class[1];
		typeArgs[0] = String.class;
		Method assetMagAddAssetPathMtd = assetMagCls.getDeclaredMethod("addAssetPath", typeArgs);
		Object[] valueArgs = new Object[1];
		valueArgs[0] = apkPath;
		assetMagAddAssetPathMtd.invoke(assetMag, valueArgs);
		Resources res = context.getResources();
		typeArgs = new Class[3];
		typeArgs[0] = assetMag.getClass();
		typeArgs[1] = res.getDisplayMetrics().getClass();
		typeArgs[2] = res.getConfiguration().getClass();
		Constructor resCt = Resources.class.getConstructor(typeArgs);
		valueArgs = new Object[3];
		valueArgs[0] = assetMag;
		valueArgs[1] = res.getDisplayMetrics();
		valueArgs[2] = res.getConfiguration();
		res = (Resources) resCt.newInstance(valueArgs);
		return res;
	}
}
